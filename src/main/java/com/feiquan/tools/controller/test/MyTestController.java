package com.feiquan.tools.controller.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feiquan.tools.constant.WtIdPrefix;
import com.feiquan.tools.dto.api.WtResponse;
import com.feiquan.tools.repo.entity.BaseEntity;
import com.feiquan.tools.repo.entity.UserInfoPO;
import com.feiquan.tools.repo.service.IUserInfoService;
import com.feiquan.tools.util.IdFactor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class MyTestController {
    private final IUserInfoService iUserInfoService;

    @PostMapping("/user/list")
    public WtResponse<List<UserInfoPO>> allUser(){
        log.info("list");
        List<UserInfoPO> list = iUserInfoService.list(new QueryWrapper<UserInfoPO>().lambda()
                .orderByDesc(BaseEntity::getCreatedAt).orderByDesc(BaseEntity::getId));
        return WtResponse.success(list);
    }

    @PostMapping("/user/create")
    public WtResponse<UserInfoPO> createUser(){
        UserInfoPO userInfoPO = UserInfoPO.builder()
                .userId(IdFactor.nextId(WtIdPrefix.U))
                .userCustomizeId(IdFactor.nextId(WtIdPrefix.CU))
                .nickName("飞拳666")
                .registerTime(OffsetDateTime.now())
                .build();
        iUserInfoService.save(userInfoPO);
        return WtResponse.success(userInfoPO);
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
