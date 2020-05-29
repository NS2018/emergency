package com.emergency.service.safetyknowledge;

import com.emergency.module.entity.SafetyKnowledge;

import java.util.List;

/**
 * 安全知识
 */
public interface SafetyKnowledgeService {

    SafetyKnowledge get(String id);

    List<SafetyKnowledge> listSafetyKnowledge(int pageNum, int pageSize);
}
