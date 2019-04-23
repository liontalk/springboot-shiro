package cn.liontalk.springbootshiro.service;


import cn.liontalk.springbootshiro.common.domain.Tree;
import cn.liontalk.springbootshiro.entity.MenuEntity;

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
}
