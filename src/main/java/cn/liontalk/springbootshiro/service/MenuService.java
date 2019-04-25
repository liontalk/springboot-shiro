package cn.liontalk.springbootshiro.service;


import cn.liontalk.springbootshiro.common.domain.Tree;
import cn.liontalk.springbootshiro.entity.MenuEntity;
import cn.liontalk.springbootshiro.vo.MenuVO;
import org.springframework.web.bind.annotation.PathVariable;

import java.awt.*;
import java.util.List;
import java.util.Set;

public interface MenuService {


    /**
     * 获取所有的权限
     * @return
     *
     */
    Set<String> listPerms(Integer id);


    /**
     *获取管理员所有的菜单权限
     */
    List<Tree<MenuEntity>> listMenuTree(int userId);


    /**
     * 获取所有的菜单
     * @return
     */
    List<MenuEntity> queryAllMenus();


    /**
     * 获得菜单树
     * @return
     */
    Tree<MenuEntity> getTree();


    /**
     * 根据角色id获取菜单树
     * @param roleId
     * @return
     */
    Tree<MenuEntity> getTreeByRoleId(int roleId);


    /**
     * 删除角色
     * @param list 菜单id
     * @return 删除行数
     */
    int menuDelete(List<Integer> list);


    /**
     * 子菜单id查找父菜单名称
     * @param menuId 子菜单id
     * @return MenuEntity
     */
    MenuEntity queryMenuParentNameById(int menuId);


    /**
     * 增加菜单数据到数据库
     * @param menuEntity
     * @return 受影响的行数
     */
    int  insert(MenuEntity menuEntity);


    /**
     *  查找菜单实体
     * @param menuId
     * @return MenuVO
     */
    MenuVO queryMenuVoById(int menuId);


}
