package com.test.test;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Index;

import org.greenrobot.greendao.annotation.Generated;

/**
 * Copyright 2013-2018 duolabao.com All right reserved. This software is the
 * confidential and proprietary information of duolabao.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with duolabao.com.
 * <p>
 * Created by DanYue on 2018/12/28 15:56.
 */
@Entity
public class User {
    @Index(name = "User_name", unique = true)
    private String name;
    private int age;
    private boolean sex;
    public boolean getSex() {
        return this.sex;
    }
    public void setSex(boolean sex) {
        this.sex = sex;
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Generated(hash = 1940183486)
    public User(String name, int age, boolean sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }
    @Generated(hash = 586692638)
    public User() {
    }
}
