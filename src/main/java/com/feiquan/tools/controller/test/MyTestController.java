package com.feiquan.tools.controller.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.feiquan.tools.repo.entity.BaseEntity;
import com.feiquan.tools.repo.entity.UserInfoPO;
import com.feiquan.tools.repo.service.IUserInfoService;
import com.feiquan.tools.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class MyTestController {
    private final IUserInfoService iUserInfoService;

    @PostMapping("/user/list")
    public String allUser(){
        List<UserInfoPO> list = iUserInfoService.list(new QueryWrapper<UserInfoPO>().lambda()
                .orderByDesc(BaseEntity::getCreatedAt).orderByDesc(BaseEntity::getId));
        return JsonUtil.toJson(list);
    }
}
