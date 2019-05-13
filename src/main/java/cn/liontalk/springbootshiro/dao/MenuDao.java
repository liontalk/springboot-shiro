package cn.liontalk.springbootshiro.dao;

import cn.liontalk.springbootshiro.entity.MenuEntity;
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
     * 插入角色和菜单之间关联关系
     * @param id 角色id
     * @param list 菜单list
     * @return 受影响的行数
     */
    int insertRoleAndMenuRel(@Param("id") Long id,@Param("list") List<Long> list);


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


    /**
     * 删除菜单
     * @param list 菜单id
     * @return 删除行数
     */
    int delete(List<Integer> list);


    /**
     * 根据子菜单的id查找父菜单名称
     * @param menuId
     * @return
     */
    MenuEntity queryMenuParentNameById(@Param("menuId") int menuId);


    /**
     * 插入数据库
     * @param menuEntity
     * @return
     */
    int insert(MenuEntity menuEntity);


    /**
     * 更新菜单数据
     * @param menuEntity
     * @return 受影响的行数
     */
    int update(MenuEntity menuEntity);


    /**
     * 查找菜单实体
     * @param menuId
     * @return MenuEntity
     */
    MenuEntity queryMenuById(@Param("menuId") Integer menuId);
}
