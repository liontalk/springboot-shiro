package cn.liontalk.springbootshiro.controller;

import cn.liontalk.springbootshiro.common.result.AjaxResult;
import cn.liontalk.springbootshiro.common.result.CodeMsg;
import cn.liontalk.springbootshiro.entity.ManagerEntity;
import cn.liontalk.springbootshiro.entity.RoleEntity;
import cn.liontalk.springbootshiro.service.RoleService;
import cn.liontalk.springbootshiro.util.PageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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



    @ApiOperation(value = "角色管理页面", notes = "角色管理页面")
    @GetMapping(value = "/page")
    public String  toManagerPage() {
        logger.info("跳转到角色管理....");
        return PREFIX + "user";
    }



    @ApiOperation(value = "角色页面列表", notes = "角色页面列表")
    @GetMapping(value = "/list")
    @ResponseBody
    public PageUtils queryAllManager() {
        List<RoleEntity> list = roleService.queryAllRoles();
        PageUtils pageUtil = new PageUtils(list, list.size());
        return pageUtil;
    }



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
