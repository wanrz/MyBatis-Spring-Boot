package tk.mybatis.springboot.model;


import lombok.Data;

import javax.persistence.Transient;
@Data
public class User{
    private String id;
    private String name;
    private String loginName;
    private String passWord;
    private String createUser;
    private long createTime;
    private String updateUser;
    private long updateTime;
    @Transient
    private Integer page = 1;

    @Transient
    private Integer rows = 10;

}
