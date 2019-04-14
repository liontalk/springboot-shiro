package cn.liontalk.springbootshiro.controller;

import cn.liontalk.springbootshiro.util.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Api(value = "首页跳转")
public class IndexController {


    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @ApiOperation(value = "跳转到登录页面",notes = "跳转到登录页面")
    @GetMapping({"/", ""})
    public String welcome(Model model) {
        logger.info("1111111");
        return "redirect:/login";
    }


    @GetMapping("/login")
    public String login() {
        logger.info("222222222");
        return "login";
    }

    @ApiOperation(value = "管理员登录",notes = "管理员登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name="username",value="登录账号",required=true),
            @ApiImplicitParam(name="password",value="用户密码",required=true)
    })
    @PostMapping(value = "/login")
    public String managerLogin(@RequestParam("username") String username,
                               @RequestParam("password") String password){

        password = MD5Utils.encrypt(username,password);
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        try{
            subject.login(token);
        }catch (Exception e){
            logger.error("登录失败! " + e.getMessage());
        }
        return "Login Successful!";
    }
}
