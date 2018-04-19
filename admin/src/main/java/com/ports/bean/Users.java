package com.ports.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Users implements Serializable {


    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String password;
    private String sex;
    private Date insertDate;
    private Date birthday;
    private String message;
    private byte[] photo;

    public Users() {
    }

    public Users(String id, String name, String password, String sex,
                 Date birthday, Date insertDate, String message) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.birthday = birthday;
        this.insertDate = insertDate;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }


}