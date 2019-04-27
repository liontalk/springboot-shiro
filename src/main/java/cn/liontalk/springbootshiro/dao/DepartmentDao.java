package cn.liontalk.springbootshiro.dao;

import cn.liontalk.springbootshiro.entity.DepartEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @projectName springboot-shiro
 * @description:
 * @date 2019/4/23 14:06
 */
@Mapper
public interface DepartmentDao {


    /**
     * 查询所有的部门列表
     * @return
     */
    List<DepartEntity> queryAllDepartment();


    /**
     * 删除部门
     * @param deptId
     * @return 删除的行数
     */
    int delete(@Param("deptId") Integer deptId);


    /**
     * 新增部门信息
     * @param departEntity
     */
   int insert(DepartEntity departEntity);


    /**
     * 更新部门信息
     * @param departEntity
     * @return
     */
   int update(DepartEntity departEntity);


    /**
     * 根据id查询部门
     * @param deptId
     * @return
     */
   DepartEntity queryDeptById(@Param("deptId") Integer deptId);


    /**
     * 获取部门菜单树
     * @param map
     * @return List<DepartEntity>
     */
    List<DepartEntity> list(Map<String, Object> map);

}
