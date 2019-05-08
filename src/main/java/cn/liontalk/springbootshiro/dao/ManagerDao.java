package cn.liontalk.springbootshiro.dao;

import cn.liontalk.springbootshiro.entity.ManagerEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ManagerDao {


    /**
     * 查找所有的管理员信息
     *
     * @return List<ManagerEntity>
     */
    List<ManagerEntity> queryAllManager();


    /**
     * 根据名称查找管理员
     *
     * @param username 管理员名称
     * @return 受影响的行数
     */
    ManagerEntity findManagerByName(@Param("username") String username);


    /**
     * 更新管理员的信息
     *
     * @param managerEntity
     * @return 受影响的行数
     */
    int insertManager(ManagerEntity managerEntity);


    /**
     * 删除管理员信息
     *
     * @param list
     * @return 受影响的行数
     */
    int deleteManager(List<Integer> list);


    /**
     * 更新管理员信息
     *
     * @param managerEntity 管理员实体信息
     */
    void updateManager(ManagerEntity managerEntity);


    /**
     * 查找管理员的信息
     *
     * @param userId
     * @return MenuEntity
     */
    ManagerEntity queryManagerById(@Param("userId") int userId);

}
