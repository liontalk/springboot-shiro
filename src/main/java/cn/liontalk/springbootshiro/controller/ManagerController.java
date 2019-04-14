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


    @ApiOperation(value = "管理员登录",notes = "管理员登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name="username",value="登录账号",required=true),
            @ApiImplicitParam(name="password",value="用户密码",required=true)
    })
    @PostMapping(value = "/login")
    @ResponseBody
    public String managerLogin(@RequestParam("username") String username,
                               @RequestParam("password") String password){

        password = MD5Utils.encrypt(username,password);
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        Subject subject =SecurityUtils.getSubject();
        try{
            subject.login(token);
        }catch (Exception e){
            logger.error("登录失败! " + e.getMessage());
        }
        return "Login Successful!";
    }


    @ApiOperation(value = "管理员查询",notes = "管理员列表展示")
    @GetMapping(value = "/list")
    public List<ManagerEntity> queryAllManager(){
        return managerService.queryAllManager();
    }

}
