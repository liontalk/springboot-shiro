package cn.liontalk.springbootshiro.service;

import cn.liontalk.springbootshiro.entity.MenuEntity;
import cn.liontalk.springbootshiro.entity.RoleEntity;

import java.util.List;

public interface RoleService {


    /**
     * 查找所有的角色
     * @return  List<RoleEntity>
     */
    List<RoleEntity> queryAllRoles();


    /**
     * 删除角色
     * @param list
     * @return
     */
    int deleteRoleInfo(List<Integer> list);


    /**
     * 增加角色的信息
     * @param entity
     * @return
     */
    void insertRoleInfo(RoleEntity entity);


    /**
     * 更新角色的信息
     * @param roleEntity
     * @return
     */
    int updateRoleInfo(RoleEntity roleEntity);


    /**
     * 根据管理员的id查找
     * @param userId
     * @return
     */
    List<RoleEntity> queryManagerRoleById(int userId);

    /**
     *  根据用户的ID查找管理员的权限菜单
     * @param userId
     * @return
     */
    List<MenuEntity> queryManagerAndRoleMenuByUserId(int userId);

}
