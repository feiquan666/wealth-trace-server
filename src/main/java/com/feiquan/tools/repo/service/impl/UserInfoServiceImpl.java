package com.feiquan.tools.repo.service.impl;

import com.feiquan.tools.repo.entity.UserInfoPO;
import com.feiquan.tools.repo.mapper.IUserInfoMapper;
import com.feiquan.tools.repo.service.IUserInfoService;
import org.springframework.stereotype.Repository;

@Repository
public class UserInfoServiceImpl extends BaseServiceImpl<IUserInfoMapper, UserInfoPO> implements IUserInfoService {
}
