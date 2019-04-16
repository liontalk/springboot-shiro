package cn.liontalk.springbootshiro.controller;

import cn.liontalk.springbootshiro.common.result.AjaxResult;
import cn.liontalk.springbootshiro.entity.ManagerEntity;
import cn.liontalk.springbootshiro.service.ManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/manager")
@Api(value = "管理员模块")
public class ManagerController {

    private static final Logger logger = LoggerFactory.getLogger(ManagerController.class);

    @Autowired
    private ManagerService managerService;


    @ApiOperation(value = "管理员查询", notes = "管理员列表展示")
    @GetMapping(value = "/list")
    public AjaxResult<List<ManagerEntity>> queryAllManager() {
        List<ManagerEntity> list = managerService.queryAllManager();
        return AjaxResult.success(list);
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
