package cn.liontalk.springbootshiro.util;

import cn.liontalk.springbootshiro.entity.ManagerEntity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * @author: 周哲
 * @package: cn.liontalk.springbootshiro.util
 * @description:
 * @date: 2019/4/14 11:20
 * @version: V1.0
 */
public class ShiroUtils {


    /**
     *
     * 获得主题
     * @return Subject
     */
    public static Subject getSubject(){
        return SecurityUtils.getSubject();
    }


    /**
     * 获得管理员信息
     * @return
     */
    public static ManagerEntity getManagerInfo(){
        Object object = getSubject().getPrincipal();
        return (ManagerEntity) object;
    }


    /**
     * 获得管理员id
     * @return
     */
    public static Integer getManagerId(){
        return getManagerInfo().getUserId();
    }

    /**
     * 登录出系统
     */
    public static void logout() {
        getSubject().logout();
    }
}
