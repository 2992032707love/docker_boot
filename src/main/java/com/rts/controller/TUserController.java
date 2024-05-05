package com.rts.controller;


import cn.hutool.core.util.IdUtil;
import com.rts.entity.TUser;
import com.rts.service.TUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Random;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author rts
 * @since 2024-05-04
 */
@Slf4j
@Api(tags = "用户TUser接口")
@RestController
@RequestMapping("/tUser")
public class TUserController {

    @Resource
    private TUserService tUserService;

    @ApiOperation("数据库新增3条记录")
    @PostMapping("/add")
    public void addTUser(){
        for (int i = 1; i < 4; i++) {
            TUser tUser = new TUser("RTS" + i, IdUtil.simpleUUID().substring(0,6),(byte) new Random().nextInt(2));
            tUserService.addTUser(tUser);
        }
    }

    @ApiOperation("查询1条记录")
    @GetMapping("/find")
    public TUser findTUserById(Integer id) {
        return tUserService.findTUserById(id);
    }

}
