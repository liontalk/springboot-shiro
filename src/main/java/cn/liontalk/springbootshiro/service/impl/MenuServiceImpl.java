package cn.liontalk.springbootshiro.service.impl;

import cn.liontalk.springbootshiro.common.domain.Tree;
import cn.liontalk.springbootshiro.dao.MenuDao;
import cn.liontalk.springbootshiro.entity.MenuEntity;
import cn.liontalk.springbootshiro.service.MenuService;
import cn.liontalk.springbootshiro.util.BuildTreeUtils;
import org.apache.commons.lang3.StringUtils;
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
}
