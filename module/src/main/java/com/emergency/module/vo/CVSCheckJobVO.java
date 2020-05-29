package com.emergency.module.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CVSCheckJobVO implements Serializable {

    private String name;
    private int policyId;
    private int scanMethodId = 0;
}
