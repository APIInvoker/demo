package com.example.springunity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springunity.mapper.entity.Area;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zx
 * @description 针对表【area(行政区划)】的数据库操作Mapper
 * @createDate 2024-05-05 11:40:15
 * @Entity com.example.springunity.entity.Area
 */
@Mapper
public interface AreaMapper extends BaseMapper<Area>
{
    List<Area> selectByLevel(int level);

    List<Area> selectByParentIdList(List<Integer> parentIdList);
}
