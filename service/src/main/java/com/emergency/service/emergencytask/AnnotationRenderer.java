/*
 * Copyright (c) 2019. 启明星辰信息技术集团股份有限公司 版权所有
 * 本源代码受法律保护，侵权必究！
 *
 */

package com.emergency.service.emergencytask;

/**
 * @author gengyuanbo
 * 2019/01/03
 */
import com.itextpdf.kernel.pdf.PdfArray;
import com.itextpdf.kernel.pdf.annot.PdfAnnotation;
import com.itextpdf.layout.layout.LayoutContext;
import com.itextpdf.layout.layout.LayoutResult;
import com.itextpdf.layout.renderer.AbstractRenderer;
import com.itextpdf.layout.renderer.DrawContext;
import com.itextpdf.layout.renderer.IRenderer;

public class AnnotationRenderer extends AbstractRenderer implements IRenderer {

    private PdfAnnotation annotation;

    public AnnotationRenderer(PdfAnnotation annotation) {
        this.annotation = annotation;
    }

    public float getAscent(){
        return annotation.getRectangle().toRectangle().getHeight();
    }

    public float getDescent(){
        return 0;
    }

    @Override
    public LayoutResult layout(LayoutContext layoutContext) {
        occupiedArea = layoutContext.getArea().clone();

        float myHeight = annotation.getRectangle().toRectangle().getHeight();
        float myWidth = annotation.getRectangle().toRectangle().getWidth();
        occupiedArea.getBBox().moveUp(occupiedArea.getBBox().getHeight() - myHeight).setHeight(myHeight);
        occupiedArea.getBBox().setWidth(myWidth);

        return new LayoutResult(LayoutResult.FULL, occupiedArea, null, null);
    }

    @Override
    public IRenderer getNextRenderer() {
        return new AnnotationRenderer(annotation);
    }

    @Override
    public void draw(DrawContext drawContext) {
        super.draw(drawContext);
        annotation.setRectangle(new PdfArray(occupiedArea.getBBox()));
        drawContext.getDocument().getPage(occupiedArea.getPageNumber()).addAnnotation(annotation);
    }
}

