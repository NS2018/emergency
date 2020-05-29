package com.emergency.service.impl;

import com.emergency.dao.mybatis.mapper.common.BoxToolCommandMapper;
import com.emergency.dao.mybatis.mapper.common.DictionaryMapper;
import com.emergency.dao.mybatis.mapper.common.ToolMapper;
import com.emergency.module.dto.ToolDTO;
import com.emergency.module.entity.BoxToolCommand;
import com.emergency.module.entity.Dictionary;
import com.emergency.module.entity.Tool;
import com.emergency.service.ToolService;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ToolServiceImpl implements ToolService {

    @Resource
    private ToolMapper toolMapper;

    @Resource
    private DictionaryMapper dictionaryMapper;

    @Resource
    private BoxToolCommandMapper boxToolCommandMapper;

    @Override
    public ToolDTO get(String id) {
        Tool tool = toolMapper.getById(new Tool(id));
        ToolDTO toolDTO = new ToolDTO();
        toolDTO.setToolData(tool);
        String toolTags = tool.getToolTags(); //以逗号分割的工具标签Id
        String[] idArray = toolTags.split(",");

        String toolTag = StringUtils.strip(idArray.toString(),"[]");
        List<Dictionary> dictionaries = dictionaryMapper.listByIds(toolTag);
        List<BoxToolCommand> boxToolCommands = boxToolCommandMapper.listByToolId(id);

        toolDTO.setClassify(dictionaries);
        toolDTO.setToolList(boxToolCommands);

        return toolDTO;
    }

    @Override
    public List<ToolDTO> listTool(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Tool> tools = toolMapper.listTools();
        List<ToolDTO> toolDTOS = new ArrayList<>(tools.size());
        for (Tool tool : tools) {
            String[] idArray = tool.getToolTags().split(",");
            String toolTag = StringUtils.strip(idArray.toString(),"[]");
            List<Dictionary> dictionaries = dictionaryMapper.listByIds(toolTag);
            List<BoxToolCommand> boxToolCommands = boxToolCommandMapper.listByToolId(tool.getId());
            ToolDTO toolDTO = new ToolDTO();
            toolDTO.setClassify(dictionaries);
            toolDTO.setToolList(boxToolCommands);
            toolDTO.setToolData(tool);
            toolDTOS.add(toolDTO);
        }
        return toolDTOS;
    }

    @Override
    public BoxToolCommand getBoxToolCommand(String toolCommandId) {
        return boxToolCommandMapper.getById(new BoxToolCommand(toolCommandId));
    }
}
