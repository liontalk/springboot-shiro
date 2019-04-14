package cn.liontalk.springbootshiro.dao;

import cn.liontalk.springbootshiro.entity.ManagerEntity;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MenuDao {


    /**
     * 查询出用户的所有权限
     * @param userId 管理员id
     * @return List<String>
     */
    List<String> queryUserPerms(@Param("userId") Integer userId);


}
