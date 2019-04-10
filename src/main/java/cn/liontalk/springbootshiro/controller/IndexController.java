package cn.liontalk.springbootshiro.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/index"})
@Api(value = "首页跳转")
public class IndexController {


    @ApiOperation(value = "跳转到登录页面",notes = "跳转到登录页面")
    @GetMapping(value = "/login")
    public String indexPage(){
        return "login";
    }

}
