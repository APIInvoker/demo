package com.example.springunity.controller.biz;

import com.example.page.Page;
import com.example.page.PageInfo;
import com.example.springunity.controller.UnityController;
import com.example.springunity.controller.vo.UserInfoVO;
import com.example.springunity.mapper.condition.UserInfoSelectCondition;
import com.example.springunity.mapper.entity.UserInfo;
import com.example.springunity.service.impl.UserInfoServiceImpl;
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
        long a = System.currentTimeMillis();
        UserInfoSelectCondition selectCondition = UserInfoSelectCondition.buildUserInfoCondition(condition);
        Page<UserInfo> userInfoPage = userInfoService.pageQuery(selectCondition, pageInfo);
        List<UserInfoVO> voList = new ArrayList<>();
        for (UserInfo userInfo : userInfoPage.getListData()) {
            UserInfoVO vo = UserInfoVO.build(userInfo);
            voList.add(vo);
        }
        System.out.println("查询耗时：" + (System.currentTimeMillis() - a) / 1000 + "秒，" + "查到" + voList.size() +  "条数据" );
        return pageInfo.buildPage(voList, userInfoPage.getPageInfo());
    }

    public int queryUserInfoCount(UnityController.UserInfoCondition userInfoCondition)
    {
        return userInfoService.count();
    }
}
