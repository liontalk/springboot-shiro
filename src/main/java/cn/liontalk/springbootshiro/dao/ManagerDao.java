package cn.liontalk.springbootshiro.dao;

import cn.liontalk.springbootshiro.entity.ManagerEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ManagerDao {


    /**
     * 查找所有的管理员信息
     * @return
     */
    List<ManagerEntity> queryAllManager();


    /**
     * 根据名称查找管理员
     */
    ManagerEntity findManagerByName(@Param("name") String name);

}
