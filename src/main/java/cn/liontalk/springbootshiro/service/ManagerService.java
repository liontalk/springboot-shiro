package cn.liontalk.springbootshiro.service;

import cn.liontalk.springbootshiro.entity.ManagerEntity;
import org.apache.catalina.Manager;

import java.util.List;

public interface ManagerService {


    /**
     * 查找所有的管理员
     *
     * @return List<ManagerEntity> 管理员列表
     */
    List<ManagerEntity> queryAllManager();


    /**
     * 增加管理员信息
     *
     * @param managerEntity
     */
    int insertManager(ManagerEntity managerEntity);


    /**
     * 删除管理员信息
     * @param list
     */
    int  deleteManagerInfo(List<Integer> list);


    /**
     * 更新管理员信息
     * @param managerEntity
     */
    void updateManager(ManagerEntity  managerEntity);

    /**
     * 管理员id
     * @param id
     * @return ManagerEntity
     */
    ManagerEntity queryManagerById(int id);


    /**
     * 重置管理员密码
     * @param userId 管理员id
     * @param password 管理员密码
     */
    void updatePassword(int userId,String password);



}
