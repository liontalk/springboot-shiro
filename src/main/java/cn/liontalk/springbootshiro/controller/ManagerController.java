package cn.liontalk.springbootshiro.controller;

import cn.liontalk.springbootshiro.common.result.AjaxResult;
import cn.liontalk.springbootshiro.common.result.CodeMsg;
import cn.liontalk.springbootshiro.entity.ManagerEntity;
import cn.liontalk.springbootshiro.service.ManagerService;
import cn.liontalk.springbootshiro.util.MD5Utils;
import cn.liontalk.springbootshiro.util.PageUtils;
import cn.liontalk.springbootshiro.util.ShiroUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.rmi.MarshalledObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "/manager")
@Api(value = "管理员模块")
public class ManagerController {

    public static final String PREFIX = "system/user/";

    private static final Logger logger = LoggerFactory.getLogger(ManagerController.class);

    @Autowired
    private ManagerService managerService;



    @ApiOperation(value = "管理员页面", notes = "管理员页面")
    @GetMapping(value = "/page")
    public String  toManagerPage() {
        logger.info("跳转到管理员页面....");
        return PREFIX + "user";
    }


    @ApiOperation(value = "管理员查询", notes = "管理员列表展示")
    @GetMapping(value = "/list")
    @ResponseBody
    public PageUtils queryAllManager() {
        List<ManagerEntity> list = managerService.queryAllManager();
         PageUtils pageUtil = new PageUtils(list, list.size());
        return pageUtil;
    }


    @ApiOperation(value = "跳转到更新管理信息页面", notes = "跳转到更新管理信息页面")
    @GetMapping(value = "/update")
    public AjaxResult<List<ManagerEntity>> toMangerEditPage() {
        List<ManagerEntity> list = managerService.queryAllManager();
        return AjaxResult.success(list);
    }

    @ApiOperation(value = "更新数据库中管理员信息", notes = "更新数据库中管理员信息")
    @GetMapping(value = "/edit")
    public AjaxResult<List<ManagerEntity>> updateManagerInfo() {
        List<ManagerEntity> list = managerService.queryAllManager();
        return AjaxResult.success(list);
    }

    @ApiOperation(value = "管理员查询", notes = "管理员列表展示")
    @GetMapping(value = "/add")
    public AjaxResult<List<ManagerEntity>> toMangerAddPage() {
        List<ManagerEntity> list = managerService.queryAllManager();
        return AjaxResult.success(list);
    }

    @ApiOperation(value = "管理员删除", notes = "管理员删除")
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult deleteManagerInfo(@RequestParam("id") Integer id) {
        List<Integer> list = new ArrayList<>();
        list.add(id);
        Integer result =  managerService.deleteManagerInfo(list);
        logger.info("delete manager result is " + result);
        if(result>0){
            return AjaxResult.success(result);
        }else{
            return AjaxResult.error(CodeMsg.DELETE_ERROR);
        }

    }


    @ApiOperation(value = "批量删除管理员删除", notes = "批量删除管理员删除")
    @RequestMapping(value = "/batch/delete",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult batchDeleteManagerInfo(@RequestParam(value = "ids[]") Integer[] ids) {
        if(ids==null || ids.length==0){
            return AjaxResult.error(CodeMsg.DELETE_DATA_EMPTY);
        }
        List<Integer> list = Arrays.asList(ids);
        Integer result =  managerService.deleteManagerInfo(list);
        logger.info("batch delete manager result is " + result);
        if(result>0){
            return AjaxResult.success(result);
        }else{
            return AjaxResult.error(CodeMsg.DELETE_ERROR);
        }
    }


    /**
     * 管理员重置密码
     * @param userId
     * @return  String
     */
    @ApiOperation(value = "管理员重置密码", notes = "管理员重置密码")
    @RequestMapping(value = "/resetPwd/{id}")
    public String toResetPassWord(@PathVariable("id") String userId, ModelMap modelMap){
        modelMap.put("userId",userId);
        return PREFIX + "reset_pwd";
    }


    /**
     * 管理员重置密码
     * @param userId
     * @return  String
     */
    @ApiOperation(value = "管理员重置密码", notes = "管理员重置密码")
    @RequestMapping(value = "/resetPwd/{id}",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult resetManagerPassword(@PathVariable("id") int userId,@RequestParam("oldPassword") String oldPassword,
                                       @RequestParam("surePassword") String surePassword){
        if(StringUtils.isEmpty(oldPassword) || StringUtils.isEmpty(surePassword)){
            return AjaxResult.error(CodeMsg.PARAM_EMPTY);
        }
        ManagerEntity managerEntity =  managerService.queryManagerById(userId);
        if(oldPassword.equals(surePassword)){
            String finalResult = MD5Utils.encrypt(managerEntity.getUsername(),surePassword);
            managerService.updatePassword(userId,finalResult);
        }
        return AjaxResult.success(null);
    }


}
