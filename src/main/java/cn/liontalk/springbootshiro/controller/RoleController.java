package cn.liontalk.springbootshiro.controller;

import cn.liontalk.springbootshiro.common.result.AjaxResult;
import cn.liontalk.springbootshiro.common.result.CodeMsg;
import cn.liontalk.springbootshiro.constant.SysConstant;
import cn.liontalk.springbootshiro.entity.ManagerEntity;
import cn.liontalk.springbootshiro.entity.RoleEntity;
import cn.liontalk.springbootshiro.service.RoleService;
import cn.liontalk.springbootshiro.util.PageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Administrator
 * @projectName springboot-shiro
 * @description:
 * @date 2019/4/21 14:39
 */
@Api(value = "角色管理")
@Controller
@RequestMapping(value = "/role")
public class RoleController {

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    /**
     * 静态文件路径
     */
    public static final String PREFIX = "system/role/";



    @RequiresPermissions("sys:role:role")
    @ApiOperation(value = "角色管理页面", notes = "角色管理页面")
    @GetMapping(value = "/page")
    public String  toManagerPage() {
        logger.info("跳转到角色管理....");
        return PREFIX + "role";
    }


    @RequiresPermissions("sys:role:add")
    @ApiOperation(value = "角色增加页面", notes = "角色增加页面")
    @GetMapping(value = "/add")
    public String  toRoleAddPage() {
        logger.info("跳转到角色管理....");
        return PREFIX + "add";
    }


    @RequiresPermissions("sys:role:edit")
    @ApiOperation(value = "角色更新页面", notes = "角色更新页面")
    @GetMapping(value = "/edit/{roleId}")
    public String  toRoleUpdatePage(@PathVariable("roleId") Integer roleId,ModelMap modelMap) {
        logger.info("跳转到角色更新....");
        RoleEntity roleEntity = roleService.queryRoleAndMenuById(roleId);
        if(null!=roleEntity){
            modelMap.put("role",roleEntity);
        }
        return PREFIX + "edit";
    }


    @RequiresPermissions("sys:role:add")
    @ApiOperation(value = "角色增加页面", notes = "角色增加页面")
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult insertRoleInfo(RoleEntity entity, HttpSession session) {
        ManagerEntity managerEntity = (ManagerEntity) session.getAttribute(SysConstant.MANAGER);
        if(null!=managerEntity){
            entity.setUserIdCreate(managerEntity.getUserId().longValue());
        }
        roleService.insertRoleInfo(entity);
        return AjaxResult.success(null);
    }



    @RequiresPermissions("sys:role:edit")
    @ApiOperation(value = "角色更新功能", notes = "角色更新功能")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult updateRoleInfo(RoleEntity entity) {
        roleService.updateRoleInfo(entity);
        return AjaxResult.success(null);
    }


    @ApiOperation(value = "角色页面列表", notes = "角色页面列表")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public PageUtils queryAllManager() {
        List<RoleEntity> list = roleService.queryAllRoles();
        PageUtils pageUtil = new PageUtils(list, list.size());
        return pageUtil;
    }



    @RequiresPermissions("sys:role:remove")
    @ApiOperation(value = "角色删除", notes = "角色删除")
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult deleteRoleInfo(@RequestParam("id") Integer id) {
        List<Integer> list = new ArrayList<>();
        list.add(id);
        Integer result =  roleService.deleteRoleInfo(list);
        logger.info("delete role result is " + result);
        if(result>0){
            return AjaxResult.success(result);
        }else{
            return AjaxResult.error(CodeMsg.DELETE_ERROR);
        }

    }



    @RequiresPermissions("sys:role:batchRemove")
    @ApiOperation(value = "批量删除角色", notes = "批量删除角色")
    @RequestMapping(value = "/batch/delete",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult batchDeleteManagerInfo(@RequestParam(value = "ids[]") Integer[] ids) {
        if(ids==null || ids.length==0){
            return AjaxResult.error(CodeMsg.DELETE_DATA_EMPTY);
        }
        List<Integer> list = Arrays.asList(ids);
        Integer result =  roleService.deleteRoleInfo(list);
        logger.info("batch delete role result is " + result);
        if(result>0){
            return AjaxResult.success(result);
        }else{
            return AjaxResult.error(CodeMsg.DELETE_ERROR);
        }
    }
}
