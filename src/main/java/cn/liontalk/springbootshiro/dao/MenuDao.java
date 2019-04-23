package cn.liontalk.springbootshiro.dao;

import cn.liontalk.springbootshiro.entity.ManagerEntity;
import cn.liontalk.springbootshiro.entity.MenuEntity;
import cn.liontalk.springbootshiro.entity.RoleEntity;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MenuDao {


    /**
     * 查询出用户的所有权限
     * @param userId 管理员id
     * @return List<String>
     */
    List<String> queryUserPerms(@Param("userId") Integer userId);


    /**
     * 查询用户所有的菜单
     * @param userId 用户id
     * @return List<MenuEntity>
     */
    List<MenuEntity> queryMenuByUserId(@Param("userId") Integer userId);


    /**
     * 插入角色和管理员之前的关联关系
     * @return
     */
    int insertRoleAndMenuRel(@Param("id") Long id,List<Long> list);


    /**
     * 查询所有的菜单
     * @return
     */
    List<MenuEntity> queryAllMenus();


    /**
     * 查找管理员的所有权限菜单
     * @param userId
     * @return List<MenuEntity> 权限菜单
     */
    List<MenuEntity> queryManagerAndRoleMenuByUserId(@Param("userId") int userId);


    /**
     * 获取菜单树
     * @param map
     * @return  List<MenuEntity>
     */
    List<MenuEntity> list(Map<String, Object> map);


    /**
     * 获取角色对应的菜单权限
     * @param roleId
     * @return
     */
    List<Long> listMenuIdByRoleId(@Param("roleId")int  roleId);

}
