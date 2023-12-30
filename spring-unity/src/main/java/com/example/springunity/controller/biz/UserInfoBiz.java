package com.example.springunity.controller.biz;

import com.example.page.Page;
import com.example.page.PageInfo;
import com.example.springunity.controller.UnityController;
import com.example.springunity.controller.vo.UserInfoVO;
import com.example.springunity.mapper.condition.UserInfoSelectCondition;
import com.example.springunity.service.impl.UserInfoServiceImpl;
import com.example.springunity.service.wrapper.UserInfoWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zx
 * @since 2023/8/7
 */
@Service("userInfoBiz")
public class UserInfoBiz
{
    @Resource
    private UserInfoServiceImpl userInfoService;

    public Page<UserInfoVO> queryUserInfoPage(UnityController.UserInfoCondition condition, PageInfo pageInfo)
    {
        UserInfoSelectCondition selectCondition = UserInfoSelectCondition.buildUserInfoCondition(condition);
        Page<UserInfoWrapper> wrapperPage = userInfoService.pageQuery(selectCondition, pageInfo);
        List<UserInfoVO> voList = new ArrayList<>();
        for (UserInfoWrapper wrapper : wrapperPage.getListData()) {
            UserInfoVO vo = UserInfoVO.build(wrapper);
            voList.add(vo);
        }
        return pageInfo.buildPage(voList, wrapperPage.getPageInfo());
    }
}
