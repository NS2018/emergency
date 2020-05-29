/*
 * Copyright (c) 2019. 启明星辰信息技术集团股份有限公司 版权所有
 * 本源代码受法律保护，侵权必究！
 *
 */

package com.emergency.module.constant;

/**
 * 基线核查常量定义
 *
 * @author gengyuanbo
 * 2019/01/07
 */
public class BaseLineConstants {

    public interface IsManual{
        /**
         * 人工
         */
        byte MANUAL = 1;
        /**
         * 自动
         */
        byte AUTO   = 0;
    }
    public interface IsCheck{
        /**
         * 失败
         */
        byte FAIL = 0;
        /**
         * 检查合规
         */
        byte COMPLIANCE = 1;
        /**
         * 检查不合规
         */
        byte UNCOMPLIANCE = 2;
        /**
         * 需要人工确认
         */
        byte MANUAL = 4;
        byte ALL = -1;
    }
    /**
     * @author BinLiu
     * 作业进度
     */
    public interface Progress{
        /**
         * 已终止
         */
        byte END = 0;
        /**
         * 调度中 作业建立到导入之前。
         */
        byte DISPATCHING = 1;
        /**
         * 已完成 结果导入之后。
         */
        byte COMPLETE = 2;
    }
}
