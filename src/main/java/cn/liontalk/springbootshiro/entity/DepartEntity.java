package cn.liontalk.springbootshiro.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author Administrator
 * @projectName springboot-shiro
 * @description: 部门实体
 * @date 2019/4/23 13:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartEntity {

    /**
     * 部门id
     */
    private long deptId;

    /**
     * 部门的上一级部门id
     */
    private long parentId;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 部门排序
     */
    private int orderNum;

    /**
     * 部门状态
     */
    private int delFlag;


    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
