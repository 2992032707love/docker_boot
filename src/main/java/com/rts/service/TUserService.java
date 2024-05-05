package com.rts.service;

import com.rts.entity.TUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author rts
 * @since 2024-05-04
 */
public interface TUserService extends IService<TUser> {

    void addTUser(TUser user);

    TUser findTUserById(Integer id);

}
