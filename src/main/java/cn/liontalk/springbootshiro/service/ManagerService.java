package cn.liontalk.springbootshiro.service;

import cn.liontalk.springbootshiro.entity.ManagerEntity;

import java.util.List;

public interface ManagerService {


    /**
     * 查找所有的管理员
     * @return
     */
    List<ManagerEntity> queryAllManager();

}
