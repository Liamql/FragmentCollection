package com.example.administrator.izhihucollection.app.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/8/4 0004.
 */
public class DBOpenHelper extends SQLiteOpenHelper {
    /**
     * 查看SQLiteOpenHelper api文档的，它的构造方法
     * public SQLiteOpenHelper (Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
     * 创建一个帮助类的对象来创建，打开，或者管理一个数据库，这个方法总是会快速的返回，
     * 这个数据库实际上是没有被创建或者打开的，直到getWritableDatabase() 或者 getReadableDatabase() 方法中的一个被调用时才会进行创建或者打开
     * 参数：
     *   context ： 上下文对象，用来打开或者创建数据库
     *   name ： 数据库文件的名称，如果是创建内存中则位null ，
     *   factory ： 用来创建游标对象，默认的是为null
     *   version ： 数据库的版本号(以版本数字号1开始)，如果数据库比较旧，就会用 onUpgrade(SQLiteDatabase, int, int) 方法来更新数据库，
     *             如果数据库比较新，就使用 onDowngrade(SQLiteDatabase, int, int)  方法来 回退数据库
     * 【注意】 : 我们声明完这个构造方法之后，包括初始化它的名称 和 版本之后，实际上它还是没有马上被创建起来的。
     */
    private static String name = "mydb.db"; //表示数据库的名称
    //private static int version = 1; //表示数据库的版本号
    private static int version = 2; //更新数据库的版本号，此时会执行 onUpgrade()方法
    public DBOpenHelper(Context context) {
        super(context, name, null, version);
        // TODO Auto-generated constructor stub
    }

    //当数据库创建的时候，是第一次被执行，完成对数据库的表的创建
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        //SQLite 数据创建支持的数据类型： 整型数据，字符串类型，日期类型，二进制的数据类型
        //数据库这边有一个特点，就是SQLite数据库中文本类型没有过多的约束，也就是可以把布尔类型的数据存储到文本类型中，这样也是可以的          String sql = "create table person(id integer primary key autoincrement,name varchar(64),address varchar(64))";
        String sql = "create table if not exists "
                + "collectionlist"
                + " (url text primary key, content text)";

        db.execSQL(sql); //完成数据库的创建
    }
    /**
     * onUpgrade() 方法是在什么时候被执行呢？
     * 查看API文档中 onUpgrade()介绍
     *   当数据库需要升级时调用这个方法[在开发过程中涉及到数据库的设计存在缺陷的时候进行升级，不会损坏原来的数据]，这种实现方式会使用方法来减少表，或者增加表，或者做版本更新的需求。
     * 在这里就可以执行 SQLite Alter语句了，你可以使用 ALTER TABLE 来增加新的列插入到一张表中，你可以使用 ALTER TABLE 语句来重命名列或者移除列，或者重命名旧的表。
     * 你也可以创建新的表然后将旧表的内容填充到新表中。
     *   此方法会将事务之内的事件一起执行，如果有异常抛出，任何改变都会自动回滚操作。
     *   参数：
     *     db ： 数据库
     *     oldVersion ： 旧版本数据库
     *     newVersion ： 新版本数据库
     * 【注意】：这里的删除等操作必须要保证新的版本必须要比旧版本的版本号要大才行。[即 Version 2.0 > Version 1.0 ] 所以这边我们不需要对其进行操作。
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
    }

}