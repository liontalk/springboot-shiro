package cn.liontalk.springbootshiro.service;

import cn.liontalk.springbootshiro.entity.ManagerEntity;

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
    void insertManager(ManagerEntity managerEntity);


    /**
     * 删除管理员信息
     * @param list
     */
    void deleteManagerInfo(List<Integer> list);


    /**
     * 更新管理员信息
     * @param managerEntity
     */
    void updateManager(ManagerEntity  managerEntity);
}
