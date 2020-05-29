package com.emergency.service;

import com.emergency.module.dto.ToolDTO;
import com.emergency.module.entity.BoxToolCommand;

import java.util.List;

/**
 * 应急工具箱工具
 */
public interface ToolService {

    ToolDTO get(String id);

    List<ToolDTO> listTool(int pageNum, int pageSize);

    BoxToolCommand getBoxToolCommand(String toolCommandId);
}
