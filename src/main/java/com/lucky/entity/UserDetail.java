/**
 * @Description TODO
 * @Author zhenxing.dong@luckincoffee.com
 * @Date 2019/8/2 10:58
 */
package com.lucky.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "t_user_detail")
public class UserDetail {
    /**
     * 用户id
     */
    private int id;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户电话号码
     */
    private String phoneNumber;
    /**
     * 用户性别
     */
    private int sex;
    /**
     * 用户生日
     */
    private String birthday;
    /**
     * 用户邮政编码
     */
    private String postNumber;
    /**
     * 用户地址
     */
    private String address;
    /**
     * 用户注册时间
     */
    private String registerTime;
    /**
     * 用户积分
     */
    private int score;

    @Id
    @GenericGenerator(name = "generator", strategy = "assigned")
    @GeneratedValue(generator = "generator")

    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(name = "sex")
    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    @Column(name = "birthday")
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Column(name = "post_number")
    public String getPostNumber() {
        return postNumber;
    }

    public void setPostNumber(String postNumber) {
        this.postNumber = postNumber;
    }

    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "register_time")
    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    @Column(name = "score")
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
