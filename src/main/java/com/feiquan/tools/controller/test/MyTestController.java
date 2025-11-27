package com.feiquan.tools.controller.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feiquan.tools.dto.api.WtResponse;
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
    public WtResponse<List<UserInfoPO>> allUser(){
        List<UserInfoPO> list = iUserInfoService.list(new QueryWrapper<UserInfoPO>().lambda()
                .orderByDesc(BaseEntity::getCreatedAt).orderByDesc(BaseEntity::getId));
        return WtResponse.success(list);
    }

    @PostMapping("/user/page")
    public WtResponse<List<UserInfoPO>> userPage(){
        Page<UserInfoPO> objectPage = new Page<>();
        objectPage.setCurrent(3);
        objectPage.setSize(5);
        Page<UserInfoPO> page = iUserInfoService.page(objectPage, new QueryWrapper<UserInfoPO>().lambda()
                .orderByDesc(BaseEntity::getCreatedAt).orderByDesc(BaseEntity::getId));
        return WtResponse.successPage(page);
    }
}
