package com.test.test.dagger.model;

import android.util.Log;

import java.io.Serializable;

public class User implements Serializable {
    public User() {
        Log.d("*dagger","User.User");
    }
    private int id;
    private String userName;
    private String pwd;
}
