package com.emergency.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FileUtils {
    private static Logger logger = LoggerFactory.getLogger(FileUtils.class);

    /**
     * 确保文件夹存在
     * 当文件夹不存在时，创建
     * @param path 文件夹路径
     */
    public static void ensureDirExist(String path){
        if(StringUtils.isEmpty(path)){
            throw new IllegalArgumentException("path 参数不能为空！");
        }

        if(!isDir(path)){
            deleteFile(path);
            createDir(path);
        }

    }

    public static boolean isExist(String path){
        File file = new File(path);
        if(file.exists())
            return true;
        return false;
    }

    public static boolean isDir(String path){
        if(StringUtils.isEmpty(path)){
            throw new IllegalArgumentException("path 参数不能为空！");
        }
        if(isExist(path)){
            File file = new File(path);
            if(file.isDirectory()){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    public static void createDir(String path){
        if(StringUtils.isEmpty(path)){
            throw new IllegalArgumentException("path 参数不能为空！");
        }
        File file = new File(path);
        file.mkdirs();
    }

    public static boolean deleteFile(String path){
        if(StringUtils.isEmpty(path)){
            return false;
        }
        File file = new File(path);
        return deleteFile(file);
    }

    public static boolean deleteFile(File file){
        if(file == null)
            return false;
        if(!file.exists()){
            logger.warn(String.format("%s 不存在，删除文件失败！",file.getAbsolutePath()));
            return false;
        }
        if(file.isDirectory()){
            logger.warn(String.format("%s 是一个目录，无法删除！",file.getAbsolutePath()));
            return false;
        }

        return file.delete();
    }

    /**
     * 递归删除文件
     * @param root 文件路径
     * @return boolean 删除成功true，否则false
     */
    public static boolean deleteRecursively(@Nullable File root) {
        if (root == null) {
            return false;
        } else {
            try {
                return deleteRecursively(root.toPath());
            } catch (IOException var2) {
                return false;
            }
        }
    }

    public static boolean deleteRecursively(@Nullable Path root) throws IOException {
        if (root == null) {
            return false;
        } else if (!Files.exists(root, new LinkOption[0])) {
            return false;
        } else {
            Files.walkFileTree(root, new SimpleFileVisitor<Path>() {
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.delete(dir);
                    return FileVisitResult.CONTINUE;
                }
            });
            return true;
        }
    }

    /**
     * 递归拷贝文件
     * @param src 源文件夹
     * @param dest 目标文件夹
     * @throws IOException
     */
    public static void copyRecursively(File src, File dest) throws IOException {
        Assert.notNull(src, "Source File must not be null");
        Assert.notNull(dest, "Destination File must not be null");
        copyRecursively(src.toPath(), dest.toPath());
    }

    public static void copyRecursively(final Path src, final Path dest) throws IOException {
        Assert.notNull(src, "Source Path must not be null");
        Assert.notNull(dest, "Destination Path must not be null");
        BasicFileAttributes srcAttr = Files.readAttributes(src, BasicFileAttributes.class);
        if (srcAttr.isDirectory()) {
            Files.walkFileTree(src, new SimpleFileVisitor<Path>() {
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    Files.createDirectories(dest.resolve(src.relativize(dir)));
                    return FileVisitResult.CONTINUE;
                }

                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.copy(file, dest.resolve(src.relativize(file)), StandardCopyOption.REPLACE_EXISTING);
                    return FileVisitResult.CONTINUE;
                }
            });
        } else {
            if (!srcAttr.isRegularFile()) {
                throw new IllegalArgumentException("Source File must denote a directory or file");
            }

            Files.copy(src, dest);
        }

    }

    /**
     * 判断一个文件是否是图片
     *
     * @param imageFile 图片文件
     * @return boolean
     * @throws IOException
     */
    public static boolean isImage(File imageFile) throws IOException {
        if (!imageFile.exists()) {
            return false;
        }
        java.awt.Image img = null;
        try {
            img = ImageIO.read(imageFile);
            if (img == null || img.getWidth(null) <= 0 || img.getHeight(null) <= 0) {
                return false;
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            img = null;
        }
        return false;
    }

    public static void downFileStreamCopy(InputStream is, ServletOutputStream os) throws IOException{
        int n;
        byte[] buff = new byte[512];

        while((n = is.read(buff)) != -1){
            os.write(buff,0,n);
        }
        is.close();
    }

    public static void copy(){

    }

}
