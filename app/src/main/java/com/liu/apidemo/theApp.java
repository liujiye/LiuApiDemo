package com.liu.apidemo;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.widget.Toast;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBaseConfig;
import com.liu.apidemo.db.greenDao.DbUpgradeHelper;
import com.liu.apidemo.entity.greendao.DaoMaster;
import com.liu.apidemo.entity.greendao.DaoSession;

/**
 * Created by liujiye-pc on 2017/7/13.
 */

public class theApp extends Application
{
    public static Context context = null;
    public static Handler sm_handler = new Handler();

    /**
     * A flag to show how easily you can switch from standard SQLite to the encrypted SQLCipher.
     */
    public static final boolean ENCRYPTED = true;

    private static DaoSession daoSession;
    private static DaoSession daoSessionOther;

    private static LiteOrm liteOrm;
    private static LiteOrm liteOrmOther;

    @Override
    public void onCreate()
    {
        super.onCreate();

        context = this;

        //setupGreenDaoDatabase();
        setupGreenDaoOtherDatabase();

        initLiteOrm();
        initLiteOrmOther();
    }

    /**
     * 配置数据库
     */
    private void setupGreenDaoDatabase()
    {
        //创建数据库shop.db"
        //DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "shop.db", null);
        DaoMaster.OpenHelper helper = new DbUpgradeHelper(this, "liu_api_green_dao.db", null);
        //获取可写数据库
        SQLiteDatabase db = helper.getWritableDatabase();
        //获取数据库对象
        DaoMaster daoMaster = new DaoMaster(db);
        //获取Dao对象管理者
        daoSession = daoMaster.newSession();
    }

    /**
     * 配置数据库
     */
    private void setupGreenDaoOtherDatabase()
    {
        try
        {
            //创建数据库shop.db"
            //DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "toilet.db3", null);
            DaoMaster.OpenHelper helper = new DbUpgradeHelper(this, "toilet.db3", null);
            //获取可写数据库
            SQLiteDatabase db = helper.getWritableDatabase();
            //获取数据库对象
            DaoMaster daoMaster = new DaoMaster(db);
            //获取Dao对象管理者
            daoSessionOther = daoMaster.newSession();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void initLiteOrm()
    {
        if (liteOrm == null)
        {
            liteOrm = LiteOrm.newSingleInstance(this, "liu_api_lite_orm.db");
        }
        liteOrm.setDebugged(true); // open the log
    }

    private void initLiteOrmOther()
    {
        try
        {
            if (liteOrmOther == null)
            {
                DataBaseConfig config = new DataBaseConfig(this);
                config.dbVersion = 2;
                config.dbName = "toilet.db3";
                liteOrmOther = LiteOrm.newSingleInstance(config);
                //liteOrmOther = LiteOrm.newSingleInstance(this, "toilet.db3");
            }
            liteOrmOther.setDebugged(true); // open the log
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static DaoSession getDaoSession()
    {
        return daoSession;
    }

    public static DaoSession getDaoSessionOther()
    {
        return daoSessionOther;
    }

    public static LiteOrm getLiteOrm()
    {
        return liteOrm;
    }

    public static LiteOrm getLiteOrmOther()
    {
        return liteOrmOther;
    }

    public static void showToast(final String str)
    {
        Runnable runnable = new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        };

        sm_handler.post(runnable);
    }
}
