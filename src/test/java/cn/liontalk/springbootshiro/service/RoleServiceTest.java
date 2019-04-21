package cn.liontalk.springbootshiro.service;

import cn.liontalk.springbootshiro.SpringbootShiroApplicationTests;
import cn.liontalk.springbootshiro.entity.RoleEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class RoleServiceTest  extends SpringbootShiroApplicationTests {


    @Autowired
    RoleService roleService;


    @Test
    public void insertRoleEntity(){
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRemark("周哲");
        roleEntity.setRoleName("zhouzhe");
        roleEntity.setUserIdCreate(0L);
        roleEntity.setRoleSign("admin");
        List<Long> longList = new ArrayList<>();
        longList.add(1L);
        longList.add(32L);
        longList.add(33L);
        longList.add(34l);
        roleEntity.setMenuEntityList(longList);

        roleService.insertRoleInfo(roleEntity);
    }

}