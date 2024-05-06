package com.example.springunity.controller;

import com.example.springunity.controller.vo.AreaVO;
import com.example.springunity.service.impl.AreaServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController("/area")
public class AreaController {
    @Resource
    private AreaServiceImpl areaServiceImpl;

    /**
     * 指定ID,查询其下整个树形结构
     *
     * @param areaId 父区划ID
     */
    @GetMapping("/tree/{areaId}")
    public List<AreaVO> queryCityTree(@PathVariable("areaId") Integer areaId) {
        return areaServiceImpl.queryAreaTreeById(areaId);
    }

    /**
     * 查询某一层级的目录
     *
     * @param areaId 父区划ID
     * @param level  行政区划级别
     */
    @GetMapping("/category")
    public List<AreaVO> queryAreaTree(@RequestParam(value = "areaId", required = false) Integer areaId,
                                      @RequestParam(value = "level", required = false) Integer level) {
        return areaServiceImpl.queryCategory(areaId, level);
    }
}
