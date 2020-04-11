package com.example.riderb.bean;

import cn.bmob.v3.BmobUser;

public class User extends BmobUser {
    private String name;
    private int score;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
