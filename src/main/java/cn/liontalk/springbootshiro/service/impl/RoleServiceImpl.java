package cn.liontalk.springbootshiro.service.impl;

import cn.liontalk.springbootshiro.dao.MenuDao;
import cn.liontalk.springbootshiro.dao.RoleDao;
import cn.liontalk.springbootshiro.entity.MenuEntity;
import cn.liontalk.springbootshiro.entity.RoleEntity;
import cn.liontalk.springbootshiro.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @projectName springboot-shiro
 * @description:
 * @date 2019/4/21 14:48
 */
@Service
public class RoleServiceImpl implements RoleService {


    @Autowired
    private RoleDao roleDao;


    @Autowired
    private MenuDao menuDao;

    @Override
    public List<RoleEntity> queryAllRoles() {
        return roleDao.queryAllRoles();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteRoleInfo(List<Integer> list) {
        int result = roleDao.deleteRoleInfo(list);
        if(result>0){
            deleteRoleAndMenuRel(list);
            deleteRoleAndManagerRel(list);
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertRoleInfo(RoleEntity entity) {
        int result =  roleDao.insertRoleInfo(entity);
        if(result>0){
            long id = entity.getRoleId();
            List<Long> list = entity.getMenuEntityList();
            menuDao.insertRoleAndMenuRel(id,list);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateRoleInfo(RoleEntity roleEntity) {
        List<Integer> roleList = new ArrayList<>();
        int result = roleDao.updateRoleInfo(roleEntity);
        if(result>0){
            long id = roleEntity.getRoleId();
            roleList.add((int)id);
            List<Long> list = roleEntity.getMenuEntityList();
            deleteRoleAndMenuRel(roleList);
            menuDao.insertRoleAndMenuRel(id,list);
        }
        return result;
    }

    @Override
    public List<RoleEntity> queryManagerRoleById(Integer userId) {
        List<RoleEntity> list = roleDao.queryManagerRoleById(userId);
        return list;
    }

    @Override
    public List<MenuEntity> queryManagerAndRoleMenuByUserId(int userId) {
        return menuDao.queryManagerAndRoleMenuByUserId(userId);
    }

    @Override
    public RoleEntity queryRoleAndMenuById(int roleId) {
        return roleDao.queryRoleAndMenuById(roleId);
    }


    /**
     * 删除角色和菜单之间的关联
     * @param list
     */
    private void deleteRoleAndMenuRel(List<Integer> list){
        roleDao.deleteRoleAndMenuRel(list);
    }


    /**
     * 删除角色和管理员之间的管理
     * @param list
     */
    private void deleteRoleAndManagerRel(List<Integer> list){
        roleDao.deleteRoleAndManagerRel(list);
    }
}
