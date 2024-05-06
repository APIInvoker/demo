package com.example.springunity.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springunity.controller.vo.AreaVO;
import com.example.springunity.mapper.AreaMapper;
import com.example.springunity.mapper.entity.Area;
import com.example.springunity.service.AreaService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zx
 * @description 针对表【area】的数据库操作Service实现
 * @createDate 2024-05-05 20:50:44
 */
@Service
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area> implements AreaService {
    @Resource
    private AreaMapper areaMapper;

    /**
     * 指定ID,查询其下整个树形结构
     *
     * @param areaId 父区域ID
     */
    @Override
    public List<AreaVO> queryAreaTreeById(Integer areaId) {
        Area area = areaMapper.selectById(areaId);
        List<AreaVO> areaVOList = new ArrayList<>();
        AreaVO areaVO = new AreaVO();
        areaVO.setAreaId(area.getAreaId());
        areaVO.setAreaName(area.getName());
        areaVO.setLevel(area.getLevel());
        areaVOList.add(areaVO);
        setChildArea(areaVOList);
        return areaVOList;
    }

    private void setChildArea(List<AreaVO> areaVOList) {
        List<Integer> parentIdList = areaVOList.stream().map(AreaVO::getAreaId).toList();
        List<Area> childAreaList = areaMapper.selectByParentIdList(parentIdList);
        List<AreaVO> childAreaVOList;
        if (!CollectionUtils.isEmpty(childAreaList)) {
            Map<Integer, List<Area>> areaGroup = childAreaList.stream().collect(Collectors.groupingBy(Area::getParentAreaId));
            for (AreaVO areaVO : areaVOList) {
                List<Area> children = areaGroup.get(areaVO.getAreaId());
                childAreaVOList = new ArrayList<>();
                if (!CollectionUtils.isEmpty(children)) {
                    for (Area area : children) {
                        AreaVO childAreaVO = new AreaVO();
                        childAreaVO.setAreaId(area.getAreaId());
                        childAreaVO.setAreaName(area.getName());
                        childAreaVO.setLevel(area.getLevel());
                        childAreaVOList.add(childAreaVO);
                    }
                    areaVO.setChildVOList(childAreaVOList);
                    setChildArea(childAreaVOList);
                }
            }
        }
    }

    /**
     * 查询某一级行政区划目录
     *
     * @param areaId    该级的父级ID
     * @param level 行政区划等级(1:省;2:市;3:区县;4:街道镇;)
     */
    @Override
    public List<AreaVO> queryCategory(Integer areaId, Integer level) {
        List<Area> areaList;
        if (areaId == null) {
            areaList = areaMapper.selectByLevel(1);
        } else {
            areaList = areaMapper.selectByParentIdList(List.of(areaId));
        }
        List<AreaVO> areaVOList = new ArrayList<>();
        for (Area area : areaList) {
            AreaVO areaVO = new AreaVO();
            areaVO.setAreaId(area.getAreaId());
            areaVO.setAreaName(area.getName());
            areaVO.setLevel(area.getLevel());
            areaVOList.add(areaVO);
        }
        return areaVOList;
    }
}





