package com.dujiang.myapplication.vo;

import java.io.Serializable;

/**
 * Created by Dujiang0311 on 2017/2/13.
 */

public class User implements Serializable{
    private int userid;
    private String phone;
    private String card;
    private String pwd;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setId(int id) {
        this.userid = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
