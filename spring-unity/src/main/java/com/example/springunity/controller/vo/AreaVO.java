package com.example.springunity.controller.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AreaVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer areaId;
    private Integer level;
    private String areaName;

    private List<AreaVO> childVOList;
}
