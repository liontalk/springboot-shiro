package cn.liontalk.springbootshiro.service.impl;

import cn.liontalk.springbootshiro.dao.MenuDao;
import cn.liontalk.springbootshiro.service.MenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: 周哲
 * @package: cn.liontalk.springbootshiro.service.impl
 * @description:
 * @date: 2019/4/14 11:30
 * @version: V1.0
 */
@Service
public class MenuServiceImpl implements MenuService {


    @Autowired
    private MenuDao menuDao;



    /**
     * 获取所有的权限
     *
     * @return
     */
    @Override
    public Set<String> listPerms(Integer id) {
        List<String> perms = menuDao.queryUserPerms(id);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StringUtils.isNotBlank(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }
}
