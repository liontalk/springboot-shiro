package cn.liontalk.springbootshiro.service;

import cn.liontalk.springbootshiro.entity.DepartEntity;
import cn.liontalk.springbootshiro.vo.DepartmentVO;

import java.util.List;

/**
 * @author Administrator
 * @projectName springboot-shiro
 * @description:
 * @date 2019/4/23 14:08
 */
public interface DepartmentService {

    /**
     * 插入部门信息
     * @param departEntity
     * @return
     */
    int insert(DepartEntity departEntity);


    /**
     * 删除部门信息
     * @param id
     * @return
     */
    int delete(int  id);


    /**
     * 更新部门信息
     * @param departEntity
     * @return
     */
    int update(DepartEntity departEntity);


    /**
     * 查询部门列表
     * @return
     */
    List<DepartEntity> queryAllDepartment();


    /**
     * 根据部门id查找部门信息
     * @param deptId
     * @return
     */
    DepartmentVO queryDeptById(int deptId);
}
