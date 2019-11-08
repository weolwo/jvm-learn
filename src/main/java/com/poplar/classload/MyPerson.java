package com.poplar.classload;

/**
 * Created By poplar on 2019/11/8
 */
public class MyPerson {

    private MyPerson person;

    public void setMyPerson(Object object) {
        this.person = (MyPerson) object;
    }
}
