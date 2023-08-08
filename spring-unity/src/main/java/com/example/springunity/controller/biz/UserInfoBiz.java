package com.example.springunity.controller.biz;

import com.example.springunity.controller.UnityController;
import com.example.springunity.controller.vo.UserInfoVO;
import com.example.springunity.mapper.entity.wrapper.UserInfoWrapper;
import com.example.springunity.service.impl.UserInfoServiceImpl;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zx
 * @since 2023/8/7
 */
@Service("userInfoBiz")
public class UserInfoBiz {
    @Resource
    private UserInfoServiceImpl userInfoService;

    public PageInfo<UserInfoVO> queryPageInfo(UnityController.UserInfoCondition condition) {
        PageInfo<UserInfoWrapper> pageInfo = userInfoService.pageQuery(condition);
        List<UserInfoVO> voList = new ArrayList<>();
        for (UserInfoWrapper wrapper : pageInfo.getList()) {
            UserInfoVO vo = UserInfoVO.build(wrapper);
            voList.add(vo);
        }
        return new PageInfo<>(voList);
    }
}
