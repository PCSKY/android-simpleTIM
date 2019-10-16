package com.example.zdm.simpletim.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * User: ZDM
 * DateTime: 2019/10/14
 * Description: SQLiteOpenHelper是包装了SQLite数据库的创建，打开和更新的抽象类，
 *              通过实现和使用SQLiteOpenHelper，可以隐去在数据库打开之前需要判断数据库是否需要创建或更新的逻辑。
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private final static int DATABASE_VERSION = 1;                // 数据库版本号
    private final static String DATABASE_NAME = "simpletim.db";   // 数据库名
    private static Context context;   // Context：是一个访问application环境全局信息的接口，通过它可以访问application的资源和相关的类

    // 带全部参数的构造函数，此构造函数必不可少
    public DatabaseHelper() {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // 获取context对象函数
    public static Context getContext() {
        return context;
    }

    // 获取一个只读的数据库
    public SQLiteDatabase getConnect() {
        SQLiteDatabase db = getWritableDatabase();
        return db;
    }

    // 设置context
    public static void setContext(Context context) {
        DatabaseHelper.context = context;
    }

    // 构建数据表
    @Override
    public void onCreate(SQLiteDatabase db) {
        // tb_person表 用户名 密码 真实姓名
        String sql_person = "create table tb_person(" +
                "username varchar(30) not null," +
                "password varchar(30) not null," +
                "name  varchar(30) not null);";
        db.execSQL(sql_person);

        // tb_mood表 内容 时间 发布者姓名
        String sql_mood = "create table tb_mood(" +
                "content varchar(30) not null," +
                "time varchar(30) not null," +
                "person  varchar(30) not null);";
        db.execSQL(sql_mood);
    }

    // 数据库升级
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // 关闭数据库
    public void close(SQLiteDatabase db) {
        db.close();
    }
}
