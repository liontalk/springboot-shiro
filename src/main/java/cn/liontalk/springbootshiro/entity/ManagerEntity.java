package cn.liontalk.springbootshiro.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagerEntity implements Serializable {

    /**
     * 管理员id
     */
    private Integer userId;

    /**
     * 管理员名称
     */
    private String username;

    /**
     * 管理员名称
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 部门id
     */
    private Long deptId;

    /**
     * email
     */
    private String email;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 创建用户的id
     */
    private Long userIdCreate;


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


    /**
     * 管理员角色id
     */
    private List<Long> roleEntityList;


}
