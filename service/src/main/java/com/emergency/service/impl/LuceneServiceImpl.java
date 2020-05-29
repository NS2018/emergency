package com.emergency.service.impl;

import com.emergency.service.LuceneService;
import org.springframework.stereotype.Service;

@Service
public class LuceneServiceImpl implements LuceneService {
    @Override
    public boolean deleteIndexById(String id) {
        return false;
    }
}
