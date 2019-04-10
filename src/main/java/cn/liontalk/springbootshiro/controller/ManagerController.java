package cn.liontalk.springbootshiro.controller;

import cn.liontalk.springbootshiro.entity.ManagerEntity;
import cn.liontalk.springbootshiro.service.ManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/manager")
@Api(value = "管理员模块")
public class ManagerController {

    @Autowired
    private ManagerService managerService;


    @ApiOperation(value = "管理员登录",notes = "管理员登录")
    @PostMapping(value = "/login")
    public String managerLogin(){


        return null;
    }


    @ApiOperation(value = "管理员查询",notes = "管理员列表展示")
    @GetMapping(value = "/list")
    public List<ManagerEntity> queryAllManager(){
        return managerService.queryAllManager();
    }

}
