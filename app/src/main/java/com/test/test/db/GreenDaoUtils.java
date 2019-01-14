package com.test.test.db;

import android.content.Context;

/**
 * Copyright 2013-2018 duolabao.com All right reserved. This software is the
 * confidential and proprietary information of duolabao.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with duolabao.com.
 * <p>
 * Created by DanYue on 2018/12/28 16:10.
 */
public class GreenDaoUtils {
    public static final String DB_NAME = "ivcvc.db";

    public static GreenDaoUtils mInstance = new GreenDaoUtils();

    public static GreenDaoUtils getInstance() {
        return mInstance;
    }

    //第一次发布app不需要升级数据库正常使用DevOpenHelper获取daosession
    public DaoSession getSession(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDb());
        return daoMaster.newSession();
    }

    //再次app需要升级数据库时 获取daosession
    public DaoSession getUpdateSession(Context context) {
        DBHelper dbHelper = new DBHelper(context, DB_NAME, null);
        DaoMaster daoMaster = new DaoMaster(dbHelper.getWritableDb());
        return daoMaster.newSession();
    }
}
