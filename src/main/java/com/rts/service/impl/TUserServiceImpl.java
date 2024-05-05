package com.rts.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.rts.entity.TUser;
import com.rts.mapper.TUserMapper;
import com.rts.service.TUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author rts
 * @since 2024-05-04
 */
@Service
@Slf4j
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements TUserService {

    public static final String CACHE_KEY_TUSER = "user:";

    @Resource
    private TUserMapper tUserMapper;
    @Resource
    private RedisTemplate redisTemplate;

    /**
     * addTUser
     * @param user
     */
    @Override
    public void addTUser(TUser user) {

        boolean save = this.save(user);

        if (save) {
            user = tUserMapper.selectById(user.getId());
            String key = CACHE_KEY_TUSER + user.getId();
            redisTemplate.opsForValue().set(key,user);
        }

    }

    @Override
    public TUser findTUserById(Integer id) {

        TUser user = null;
        String key = CACHE_KEY_TUSER + id;

        Object o = redisTemplate.opsForValue().get(key);

        if (null == o) {
            user = tUserMapper.selectById(id);
            if (user == null) {
                return user;
            } else {
                redisTemplate.opsForValue().set(key,user);
            }
        }
        user = JSONObject.parseObject(o.toString(),TUser.class);
        return user;
    }
}
