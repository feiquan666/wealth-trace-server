package com.feiquan.tools.repo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.feiquan.tools.repo.entity.UserInfoPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IUserInfoMapper extends IBaseMapper<UserInfoPO>, BaseMapper<UserInfoPO> {

}
