package cn.liontalk.springbootshiro.dao;

import cn.liontalk.springbootshiro.entity.ManagerEntity;
import cn.liontalk.springbootshiro.entity.RoleEntity;
import org.apache.ibatis.annotations.Mapper;

import javax.management.relation.Role;
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

}
