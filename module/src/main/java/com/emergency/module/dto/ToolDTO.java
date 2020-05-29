/*
 * Copyright (c) 2019. 启明星辰信息技术集团股份有限公司 版权所有
 * 本源代码受法律保护，侵权必究！
 *
 */

package com.emergency.module.dto;

import com.emergency.module.entity.BoxToolCommand;
import com.emergency.module.entity.Dictionary;
import com.emergency.module.entity.Tool;

import java.util.List;

/**
 * @author gengyuanbo
 * 2019/01/23
 */
public class ToolDTO extends Tool {
    private List<Dictionary> classify; //工具类型
    private List<BoxToolCommand> toolList; //该工具下包含的细分工具列表，比如在文档修复工具下包含rar文档修复和zip文档修复。

    public List<Dictionary> getClassify() {
        return classify;
    }

    public void setClassify(List<Dictionary> classify) {
        this.classify = classify;
    }

    public List<BoxToolCommand> getToolList() {
        return toolList;
    }

    public void setToolList(List<BoxToolCommand> toolList) {
        this.toolList = toolList;
    }

    public void setToolData(Tool tool){
        this.setId(tool.getId());

        this.setToolName(tool.getToolName());

        this.setToolPath(tool.getToolPath());

        this.setVideoPath(tool.getVideoPath());

        this.setToolImgPath(tool.getToolImgPath());

        this.setMemo(tool.getMemo());

        this.setToolTags(tool.getToolTags());

        this.setToolCmd(tool.getToolCmd());

        this.setToolType(tool.getToolType());

        this.setToolStyle(tool.getToolStyle());

        this.setToolSummary(tool.getToolSummary());

        this.setToolUserGuide(tool.getToolUserGuide());

        this.setToolCreateTime(tool.getToolCreateTime());

        this.setToolCreator(tool.getToolCreator());

        this.setReserveData1(tool.getReserveData1());

        this.setReserveData2(tool.getReserveData2());
    }
}
