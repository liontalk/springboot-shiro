package cn.liontalk.springbootshiro.controller;

import cn.liontalk.springbootshiro.common.result.AjaxResult;
import cn.liontalk.springbootshiro.entity.ManagerEntity;
import cn.liontalk.springbootshiro.service.ManagerService;
import cn.liontalk.springbootshiro.util.PageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    @GetMapping(value = "/delete")
    public AjaxResult<List<ManagerEntity>> deleteManagerInfo() {
        List<Integer> list = new ArrayList<>();
        managerService.deleteManagerInfo(list);
        return AjaxResult.success(null);
    }


}
