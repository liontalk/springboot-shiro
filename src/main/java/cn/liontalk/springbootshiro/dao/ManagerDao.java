package cn.liontalk.springbootshiro.dao;

import cn.liontalk.springbootshiro.entity.ManagerEntity;
import cn.liontalk.springbootshiro.entity.MenuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.awt.*;
import java.util.List;

@Mapper
public interface ManagerDao {


    /**
     * 查找所有的管理员信息
     *
     * @return
     */
    List<ManagerEntity> queryAllManager();


    /**
     * 根据名称查找管理员
     */
    ManagerEntity findManagerByName(@Param("username") String username);


    /**
     * 增加管理员信息
     * @param managerEntity
     */
    void insertManager(ManagerEntity managerEntity);


    /**
     * 删除管理员信息
     * @param list
     */
    int deleteManager(List<Integer> list);


    /**
     * 更新管理员信息
     * @param managerEntity
     */
    void updateManager(ManagerEntity managerEntity);


    /**
     * 查找管理员的信息
     * @param userId
     * @return MenuEntity
     */
    ManagerEntity queryManagerById(@Param("userId") int userId);

}
