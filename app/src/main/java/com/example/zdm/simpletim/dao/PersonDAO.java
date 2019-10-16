package com.example.zdm.simpletim.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.zdm.simpletim.db.DatabaseHelper;
import com.example.zdm.simpletim.model.Person;

import java.util.ArrayList;

/**
 * User: ZDM
 * DateTime: 2019/10/14
 * Description: 联系人数据访问对象
 */
public class PersonDAO {

    private static ArrayList<Person> personList = null;   // 保存联系人数据

    // 获取所有联系人
    public static ArrayList<Person> getPersonList() {
        if(null == personList) {

            // 给PersonDAO类进行加锁，则每个PersonDAO类都进行同步
            synchronized (PersonDAO.class) {
                if(null==personList){
                    personList=new ArrayList<Person>();
                }
            }

            // 把数据从数据库中拿出来(Android 查询数据是通过Cursor 类来实现)
            DatabaseHelper databaseHelper = new DatabaseHelper();   // 创建数据库
            SQLiteDatabase db = databaseHelper.getConnect();        // 获得连接
            Cursor cursor = db.query("tb_person", null, null, null, null, null, null);   // 使用SQLiteDatabase.query()方法时，就会得到Cursor对象， Cursor所指向的就是每一条数据

            // 一行行读数据
            while(cursor.moveToNext()) {
                // 返回指定列的名称
                int namenum=cursor.getColumnIndex("name");
                int usernamenum=cursor.getColumnIndex("username");
                int passwordnum=cursor.getColumnIndex("password");
                // 根据名称取数据
                String name=cursor.getString(namenum);
                String username=cursor.getString(usernamenum);
                String password=cursor.getString(passwordnum);
                // 把联系人放入列表
                Person person=new Person(name,username,password);
                personList.add(person);
                //cursor.moveToNext();
            }
        }

        return personList;
    }

    // 根据用户名查找用户真实姓名
    public static String findNameByUsername(String username){
        if(null == personList){
            getPersonList();
        }
        for ( int i = 0; i < personList.size(); i++ ) {
            Person person = personList.get(i);
            if(username.equals(person.getUsername())){
                return person.getName();
            }
        }
        return "";
    }

    // 查找用户是否存在
    public boolean checkUsername(String username){
        if(null == personList){
            getPersonList();
        }
        for ( int i = 0; i < personList.size(); i++ ) {
            Person book = personList.get(i);
            if(username.equals(book.getUsername())){
                return true;
            }
        }
        return false;
    }

    // 插入联系人
    public boolean insert(Person person){

        if(checkUsername(person.getUsername())){  //如果用户名存在则直接返回失败
            return false;
        }
        try {
            personList.add(person);
            DatabaseHelper conn = new DatabaseHelper();
            SQLiteDatabase db = conn.getConnect();
            String sql="insert into tb_person(name,username,password) values('"
                    +person.getName()+"','"+person.getUsername()+"','"+person.getPassword()+"')";
            db.execSQL(sql);
            db.close();
            return true;
        }catch ( Exception e ){
            return false;
        }

    }

    // 检查登录
    public boolean chechLogin(String username,String password){
        if(null==personList){
            getPersonList();
        }
        for ( int i = 0; i < personList.size(); i++ ) {
            Person book=personList.get(i);
            if(username.equals(book.getUsername()) && password.equals(book.getPassword())){
                return true;
            }
        }
        return false;
    }
}

/**
 * Cursor 是每行的集合。
 * 使用 moveToFirst() 定位第一行。
 * 你必须知道每一列的名称。
 * 你必须知道每一列的数据类型。
 * Cursor 是一个随机的数据源。
 * 所有的数据都是通过下标取得
 */