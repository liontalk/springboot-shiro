package cn.liontalk.springbootshiro.service.impl;

import cn.liontalk.springbootshiro.common.domain.Tree;
import cn.liontalk.springbootshiro.dao.MenuDao;
import cn.liontalk.springbootshiro.entity.MenuEntity;
import cn.liontalk.springbootshiro.service.MenuService;
import cn.liontalk.springbootshiro.util.BuildTreeUtils;
import cn.liontalk.springbootshiro.vo.MenuVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author: 周哲
 * @package: cn.liontalk.springbootshiro.service.impl
 * @description:
 * @date: 2019/4/14 11:30
 * @version: V1.0
 */
@Service
public class MenuServiceImpl implements MenuService {



    @Autowired
    private MenuDao menuDao;

    /**
     * 获取所有的权限
     *
     * @return
     */
    @Override
    public Set<String> listPerms(Integer id) {
        List<String> perms = menuDao.queryUserPerms(id);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StringUtils.isNotBlank(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    @Override
    public List<Tree<MenuEntity>> listMenuTree(int userId) {
        List<Tree<MenuEntity>> trees = new ArrayList<Tree<MenuEntity>>();
        List<MenuEntity> menuDOs = menuDao.queryMenuByUserId(userId);
        for (MenuEntity sysMenuDO : menuDOs) {
            Tree<MenuEntity> tree = new Tree<MenuEntity>();
            tree.setId(sysMenuDO.getMenuId().toString());
            tree.setParentId(sysMenuDO.getParentId().toString());
            tree.setText(sysMenuDO.getName());
            Map<String, Object> attributes = new HashMap<>(16);
            attributes.put("url", sysMenuDO.getUrl());
            attributes.put("icon", sysMenuDO.getIcon());
            tree.setAttributes(attributes);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        List<Tree<MenuEntity>> list = BuildTreeUtils.buildList(trees, "0");
        return list;
    }

    @Override
    public List<MenuEntity> queryAllMenus() {
        return menuDao.queryAllMenus();
    }

    @Override
    public Tree<MenuEntity> getTree() {
        List<Tree<MenuEntity>> trees = new ArrayList<Tree<MenuEntity>>();
        List<MenuEntity> menuDOs = menuDao.list(new HashMap<>(16));
        for (MenuEntity sysMenuDO : menuDOs) {
            Tree<MenuEntity> tree = new Tree<MenuEntity>();
            tree.setId(sysMenuDO.getMenuId().toString());
            tree.setParentId(sysMenuDO.getParentId().toString());
            tree.setText(sysMenuDO.getName());
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<MenuEntity> tree = BuildTreeUtils.build(trees);
        return tree;
    }

    @Override
    public Tree<MenuEntity> getTreeByRoleId(int roleId) {
        // 根据roleId查询权限
        List<MenuEntity> menus = menuDao.list(new HashMap<String, Object>(16));
        List<Long> menuIds = menuDao.listMenuIdByRoleId(roleId);
        List<Long> temp = menuIds;
        for (MenuEntity menu : menus) {
            if (temp.contains(menu.getParentId())) {
                menuIds.remove(menu.getParentId());
            }
        }
        List<Tree<MenuEntity>> trees = new ArrayList<Tree<MenuEntity>>();
        List<MenuEntity> menuDOs = menuDao.list(new HashMap<String, Object>(16));
        for (MenuEntity sysMenuDO : menuDOs) {
            Tree<MenuEntity> tree = new Tree<MenuEntity>();
            tree.setId(sysMenuDO.getMenuId().toString());
            tree.setParentId(sysMenuDO.getParentId().toString());
            tree.setText(sysMenuDO.getName());
            Map<String, Object> state = new HashMap<>(16);
            Long menuId = sysMenuDO.getMenuId();
            if (menuIds.contains(menuId)) {
                state.put("selected", true);
            } else {
                state.put("selected", false);
            }
            tree.setState(state);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<MenuEntity> tree = BuildTreeUtils.build(trees);
        return tree;
    }

    @Override
    public int menuDelete(List<Integer> list) {
        return menuDao.delete(list);
    }

    @Override
    public MenuEntity queryMenuParentNameById(int menuId) {
        return menuDao.queryMenuParentNameById(menuId);
    }

    @Override
    public int insert(MenuEntity menuEntity) {
        return menuDao.insert(menuEntity);
    }

    @Override
    public MenuVO queryMenuVoById(int menuId) {
        MenuVO menuVO = new MenuVO();
        MenuEntity menuEntity =  menuDao.queryMenuParentNameById(menuId);
        if(menuEntity==null){
            MenuEntity menu =  menuDao.queryMenuById(menuId);
            if(null!=menu){
                menuVO.setMenuParentName(menu.getName());
                BeanUtils.copyProperties(menu,menuVO);
            }
        }else{
            menuVO.setMenuParentName(menuEntity.getName());
            BeanUtils.copyProperties(menuEntity,menuVO);
        }
        return menuVO;
    }
}
