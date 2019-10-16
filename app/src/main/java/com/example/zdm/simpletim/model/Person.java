package com.example.zdm.simpletim.model;

import com.example.zdm.simpletim.utils.PinYinUtils;

/**
 * User: ZDM
 * DateTime: 2019/10/14
 * Description:好友实体类
 */
public class Person {

    private String name;       // 名字
    private String pinyin;     // 拼音
    private String headerChar; // 拼音首字母

    private String username;   // 用户名
    private String password;   // 密码

    public Person(){};

    public Person(String name) {
        this.name = name;
        this.password="";
        this.username="";
        this.pinyin = PinYinUtils.getPinyin(name);
        headerChar = pinyin.substring(0, 1);
    }

    public Person(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.pinyin = PinYinUtils.getPinyin(name);
        headerChar = pinyin.substring(0, 1);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public String getHeaderChar() {
        return headerChar;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
