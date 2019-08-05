package com.lucky.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @Description 用户主体信息实体类
 * @Author zhenxing.dong@luckincoffee.com
 * @Date 2019/8/1 15:24
 */
@Entity
@Table(name = "t_user_main")
public class User {
    /**
     * 用户id
     */
    private int id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 用户角色
     */
    private int role;
    /**
     * 用户积分
     */
    private int score;

    //定义主键自增
    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")

    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "nick_name")
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Column(name = "role")
    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Column(name = "score")
    public int getScore() { return score; }

    public void setScore(int score) { this.score = score; }
}
