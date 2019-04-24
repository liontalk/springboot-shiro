package cn.liontalk.springbootshiro.vo;

import cn.liontalk.springbootshiro.entity.DepartEntity;
import lombok.Data;
import lombok.ToString;

/**
 * @author Administrator
 * @projectName springboot-shiro
 * @description:
 * @date 2019/4/24 9:27
 */
@Data
@ToString
public class DepartmentVO extends DepartEntity {


    /**
     * 上一级部门的id
     */
    private String parentName;


}
