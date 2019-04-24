package cn.liontalk.springbootshiro.controller;

import cn.liontalk.springbootshiro.common.result.AjaxResult;
import cn.liontalk.springbootshiro.entity.DepartEntity;
import cn.liontalk.springbootshiro.service.DepartmentService;
import cn.liontalk.springbootshiro.vo.DepartmentVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Administrator
 * @projectName springboot-shiro
 * @description: 部门controller
 * @date 2019/4/23 13:53
 */
@Controller
@RequestMapping(value = "/department")
@Api(value = "部门信息")
public class DepartmentController {

    private static final String PREFIX = "system/dept/";

    public static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentService departmentService;


    @RequestMapping(value = "/page")
    @ApiOperation(value = "跳转到部门信息页面", notes = "跳转到部门信息页面")
    public String toDepartmentPage() {
        return PREFIX + "dept";
    }

    @ApiOperation(value = "获取部门信息列表", notes = "获取部门信息列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<DepartEntity> getAllDepartList() {
        List<DepartEntity> list = departmentService.queryAllDepartment();
        // return AjaxResult.success(list);
        return list;
    }


    @ApiOperation(value = "跳转到增加页面", notes = "跳转到增加的页面")
    @RequestMapping(value = "/add/{pId}", method = RequestMethod.GET)
    public String toDepartmentAddPage(@PathVariable("pId") Integer pId, ModelMap modelMap) {
        logger.info("获取的pId:" + pId);
        if (null == pId || 0 == pId) {
            modelMap.put("pName", "无");
        } else {
            DepartEntity departEntity = departmentService.queryDeptById(pId);
            modelMap.put("pName", departEntity.getName());
        }
        return PREFIX + "/add";
    }

    @ApiOperation(value = "增加部门数据到数据库", notes = "增加部门信息到数据库")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult insert(DepartEntity departEntity) {
        int result = departmentService.insert(departEntity);
        return AjaxResult.success(result);
    }

    @ApiOperation(value = "跳转到更新页面", notes = "跳转到更新的页面")
    @RequestMapping(value = "/edit/{deptId}", method = RequestMethod.GET)
    public String toDepartmentUpdatePage(@PathVariable("deptId") Integer deptId, ModelMap modelMap) {
        DepartmentVO department = departmentService.queryDeptById(deptId);
        modelMap.put("sysDept", department);
        return PREFIX + "/edit";
    }

    @ApiOperation(value = "增加部门数据到数据库", notes = "增加部门信息到数据库")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult update(DepartEntity departEntity) {
        int result = departmentService.update(departEntity);
        return AjaxResult.success(result);
    }


    @ApiOperation(value = "删除部门数据", notes = "删除部门数据")
    @RequestMapping(value = "/delete/{deptId}", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult delete(@PathVariable("deptId") Integer deptId) {
        int result = departmentService.delete(deptId);
        return AjaxResult.success(result);
    }
}
