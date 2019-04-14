package cn.liontalk.springbootshiro.controller;

import cn.liontalk.springbootshiro.entity.ManagerEntity;
import cn.liontalk.springbootshiro.service.ManagerService;
import cn.liontalk.springbootshiro.util.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.apache.shiro.subject.Subject;
import java.util.List;

@RestController
@RequestMapping(value = "/manager")
@Api(value = "管理员模块")
public class ManagerController {

    private static final Logger logger = LoggerFactory.getLogger(ManagerController.class);

    @Autowired
    private ManagerService managerService;





    @ApiOperation(value = "管理员查询",notes = "管理员列表展示")
    @GetMapping(value = "/list")
    public List<ManagerEntity> queryAllManager(){
        return managerService.queryAllManager();
    }

}
