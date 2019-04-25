package cn.liontalk.springbootshiro.vo;

import cn.liontalk.springbootshiro.entity.MenuEntity;
import lombok.Data;

/**
 * @author Administrator
 * @projectName springboot-shiro
 * @description:
 * @date 2019/4/25 22:23
 */
@Data
public class MenuVO extends MenuEntity {

    private String menuParentName;


}
