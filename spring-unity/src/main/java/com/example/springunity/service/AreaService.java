package com.example.springunity.service;

import com.example.springunity.controller.vo.AreaVO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springunity.mapper.entity.Area;

import java.util.List;

/**
* @author zx
* @description 针对表【area】的数据库操作Service
* @createDate 2024-05-05 20:50:44
*/
public interface AreaService extends IService<Area> {
    /**
     * 指定ID,查询其下整个树形结构
     *
     * @param areaId 父区域ID
     */
    public abstract List<AreaVO> queryAreaTreeById(Integer areaId);

    /**
     * 查询某一级行政区划目录
     *
     * @param id 该级的父级ID
     * @param level 行政区划等级(1:省;2:市;3:区县;4:街道镇;)
     */
    public abstract List<AreaVO> queryCategory(Integer id, Integer level);
}
