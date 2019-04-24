package cn.liontalk.springbootshiro.controller;

import cn.liontalk.springbootshiro.common.domain.Tree;
import cn.liontalk.springbootshiro.common.result.AjaxResult;
import cn.liontalk.springbootshiro.common.result.CodeMsg;
import cn.liontalk.springbootshiro.entity.MenuEntity;
import cn.liontalk.springbootshiro.entity.RoleEntity;
import cn.liontalk.springbootshiro.service.MenuService;
import cn.liontalk.springbootshiro.util.PageUtils;
import com.sun.tools.javac.jvm.Code;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
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
        logger.info("跳转到菜单页面....");
        return PREFIX + "/menu";
    }


    @ApiOperation(value = "角色页面列表", notes = "角色页面列表")
    @GetMapping(value = "/list")
    @ResponseBody
    public List<MenuEntity> queryAllManager() {
        List<MenuEntity> list = menuService.queryAllMenus();
        return list;
    }


    @ApiOperation(value = "菜单删除", notes = "菜单删除")
    @RequestMapping(value = "/delete/{menuId}",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult menuDelete(@PathVariable("menuId") Integer menuId) {
        if(null==menuId){
            return AjaxResult.error(CodeMsg.PARAM_EMPTY);
        }
        List<Integer> list = new ArrayList<>();
        list.add(menuId);
        int result =  menuService.menuDelete(list);
        return AjaxResult.success(result);
    }



    @ApiOperation(value = "批量菜单删除", notes = "批量菜单删除")
    @RequestMapping(value = "/batch/delete",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult batchMenuDelete(@RequestParam("menuIds[]") Integer[] menuIds) {
        if(null==menuIds || menuIds.length==0){
            return AjaxResult.error(CodeMsg.PARAM_EMPTY);
        }
        List<Integer> list = Arrays.asList(menuIds);
        int result =  menuService.menuDelete(list);
        return AjaxResult.success(result);
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
