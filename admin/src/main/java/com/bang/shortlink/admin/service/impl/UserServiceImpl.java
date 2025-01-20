package com.bang.shortlink.admin.service.impl;

import com.bang.shortlink.admin.dao.entity.UserDO;
import com.bang.shortlink.admin.dao.mapper.UserMapper;
import com.bang.shortlink.admin.dto.resp.UserRespDTO;
import com.bang.shortlink.admin.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


/**
 * 用户接口实现类
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    @Override
    public UserRespDTO getUserInfoByUsername(String username) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class).eq(UserDO::getUsername, username);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        UserRespDTO result = new UserRespDTO();
        if(userDO == null){
            return null;
        }
        BeanUtils.copyProperties(userDO, result);
        return result;
    }

}
