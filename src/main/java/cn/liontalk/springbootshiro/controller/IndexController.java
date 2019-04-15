package cn.liontalk.springbootshiro.controller;

import cn.liontalk.springbootshiro.common.domain.Tree;
import cn.liontalk.springbootshiro.common.result.AjaxResult;
import cn.liontalk.springbootshiro.common.result.CodeMsg;
import cn.liontalk.springbootshiro.entity.ManagerEntity;
import cn.liontalk.springbootshiro.entity.MenuEntity;
import cn.liontalk.springbootshiro.service.MenuService;
import cn.liontalk.springbootshiro.util.MD5Utils;
import cn.liontalk.springbootshiro.util.ShiroUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(value = "首页跳转")
public class IndexController {


    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);


    @Autowired
    MenuService menuService;

    @ApiOperation(value = "跳转到登录页面", notes = "跳转到登录页面")
    @GetMapping({"/", ""})
    public String welcome(Model model) {
        return "redirect:/login";
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @ApiOperation(value = "管理员登录", notes = "管理员登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "登录账号", required = true),
            @ApiImplicitParam(name = "password", value = "用户密码", required = true)
    })
    @PostMapping(value = "/login")
    @ResponseBody
    public AjaxResult managerLogin(@RequestParam("username") String username,
                                   @RequestParam("password") String password) {

        password = MD5Utils.encrypt(username, password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (Exception e) {
            logger.error("登录失败! " + e.getMessage());
            return AjaxResult.success(CodeMsg.BIND_ERROR);
        }
        return AjaxResult.success(CodeMsg.SUCCESS);
    }


    @GetMapping(value = "/index")
    @ApiOperation(value = "跳转到首页", notes = "跳转到首页")
    public String index(Model model) {
        ManagerEntity user = ShiroUtils.getManagerInfo();
        List<Tree<MenuEntity>> menus = menuService.listMenuTree(user.getUserId());
        model.addAttribute("menus", menus);
        model.addAttribute("name", user.getName());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("picUrl", "/img/photo_s.jpg");
//        FileDO fileDO = fileService.get(getUser().getPicId());
//        if (fileDO != null && fileDO.getUrl() != null) {
//            if (fileService.isExist(fileDO.getUrl())) {
//                model.addAttribute("picUrl", fileDO.getUrl());
//            } else {
//                model.addAttribute("picUrl", "/img/photo_s.jpg");
//            }
//        } else {
//            model.addAttribute("picUrl", "/img/photo_s.jpg");
//        }
        return "index_v1";
    }
}
