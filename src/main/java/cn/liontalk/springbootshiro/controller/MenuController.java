package cn.liontalk.springbootshiro.controller;

import cn.liontalk.springbootshiro.common.domain.Tree;
import cn.liontalk.springbootshiro.entity.MenuEntity;
import cn.liontalk.springbootshiro.entity.RoleEntity;
import cn.liontalk.springbootshiro.service.MenuService;
import cn.liontalk.springbootshiro.util.PageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Administrator
 * @projectName springboot-shiro
 * @description:
 * @date 2019/4/21 16:28
 */
@Api(value = "权限菜单")
@Controller
@RequestMapping(value = "/menu")
public class MenuController {



    public static final String PREFIX = "system/menu/";

    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    MenuService menuService;

    @ApiOperation(value = "菜单页面", notes = "菜单页面")
    @GetMapping(value = "/page")
    public String  toMenuPage() {
        logger.info("跳转到管理员页面....");
        return PREFIX + "menu";
    }


    @ApiOperation(value = "角色页面列表", notes = "角色页面列表")
    @GetMapping(value = "/list")
    @ResponseBody
    public PageUtils queryAllManager() {
        List<MenuEntity> list = menuService.queryAllMenus();
        PageUtils pageUtil = new PageUtils(list, list.size());
        return pageUtil;
    }



    @ApiOperation(value = "获取整个菜单树", notes = "获取整个菜单树")
    @GetMapping("/tree")
    @ResponseBody
    public Tree<MenuEntity> tree() {
        logger.info("get menu tree start!");
       return  menuService.getTree();
    }


    @ApiOperation(value = "根据角色获取角色拥有的菜单", notes = "根据角色获取角色拥有的菜单")
    @GetMapping("/tree/{roleId}")
    @ResponseBody
    public Tree<MenuEntity> tree(@PathVariable("roleId") Integer roleId) {
        logger.info("get menu tree start!");
        return  menuService.getTreeByRoleId(roleId);
    }



}
