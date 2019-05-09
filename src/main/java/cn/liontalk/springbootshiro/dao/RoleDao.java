package cn.liontalk.springbootshiro.dao;

import cn.liontalk.springbootshiro.entity.RoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleDao {


    /**
     * 查找所有的角色
     * @return
     */
    List<RoleEntity> queryAllRoles();


    /**
     * 批量删除角色信息
     * @param list
     * @return
     */
    int deleteRoleInfo(List<Integer> list);


    /**
     * 删除角色和菜单的关联
     */
    int deleteRoleAndMenuRel(List<Integer> list);


    /**
     * 删除管理员和角色之间的关联
     * @param list
     * @return
     */
    int deleteRoleAndManagerRel(List<Integer> list);


    /**
     * 新增角色信息
     * @param roleEntity
     * @return  int
     */
    int insertRoleInfo(RoleEntity roleEntity);


    /**
     * 更新角色信息
     * @param roleEntity
     * @return int
     */
    int updateRoleInfo(RoleEntity roleEntity);


    /**
     * 查询管理员的角色
     * @param roleId
     * @return
     */
    List<RoleEntity> queryManagerRoleById(@Param("roleId") Integer roleId);


    /**
     * 查找角色详情
     * @param roleId 角色id
     * @return RoleEntity
     */
    RoleEntity queryRoleAndMenuById(@Param("roleId") int roleId);


    /**
     * 插入管理员和角色之间的关系
     * @param userId
     * @param list
     */
    void insertManagerAndRole(@Param("userId") int userId,@Param("list") List<Long> list);
}
