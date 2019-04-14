package cn.liontalk.springbootshiro.service;


import java.util.Set;

public interface MenuService {


    /**
     * 获取所有的权限
     * @return
     */
    Set<String> listPerms(Integer id);

}
