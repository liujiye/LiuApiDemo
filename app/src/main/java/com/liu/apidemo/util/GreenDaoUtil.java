package com.liu.apidemo.util;

import com.liu.apidemo.entity.greendao.ShopGreenDao;
import com.liu.apidemo.entity.greendao.ShopGreenDaoDao;
import com.liu.apidemo.theApp;

import java.util.List;

/**
 * GreenDao工具类
 * <p>
 * 增加单个数据
 * getShopDao().insert(shop);
 * getShopDao().insertOrReplace(shop);
 * 增加多个数据
 * getShopDao().insertInTx(shopList);
 * getShopDao().insertOrReplaceInTx(shopList);
 * 查询全部
 * List< Shop> list = getShopDao().loadAll();
 * List< Shop> list = getShopDao().queryBuilder().list();
 * 查询附加单个条件
 * .where()
 * .whereOr()
 * 查询附加多个条件
 * .where(, , ,)
 * .whereOr(, , ,)
 * 查询附加排序
 * .orderDesc()
 * .orderAsc()
 * 查询限制当页个数
 * .limit()
 * 查询总个数
 * .count()
 * 修改单个数据
 * getShopDao().update(shop);
 * 修改多个数据
 * getShopDao().updateInTx(shopList);
 * 删除单个数据
 * getTABUserDao().delete(user);
 * 删除多个数据
 * getUserDao().deleteInTx(userList);
 * 删除数据ByKey
 * getTABUserDao().deleteByKey();
 */
public class GreenDaoUtil
{
    /**
     * 添加数据，如果有重复则覆盖
     *
     * @param shopGreenDao
     */
    public static void insertShop(ShopGreenDao shopGreenDao)
    {
        theApp.getDaoSession().getShopGreenDaoDao().insertOrReplace(shopGreenDao);
    }

    /**
     * 删除数据
     *
     * @param id
     */
    public static void deleteShop(long id)
    {
        theApp.getDaoSession().getShopGreenDaoDao().deleteByKey(id);
    }

    /**
     * 更新数据
     *
     * @param shopGreenDao
     */
    public static void updateShop(ShopGreenDao shopGreenDao)
    {
        theApp.getDaoSession().getShopGreenDaoDao().update(shopGreenDao);
    }

    /**
     * 查询条件为Type=TYPE_LOVE的数据
     *
     * @return
     */
    public static List<ShopGreenDao> queryShop()
    {
        return theApp.getDaoSession().getShopGreenDaoDao().queryBuilder().where(ShopGreenDaoDao.Properties.Type.eq(ShopGreenDao.TYPE_LOVE)).list();
    }

    /**
     * 查询全部数据
     */
    public static List<ShopGreenDao> queryAll()
    {
        return theApp.getDaoSession().getShopGreenDaoDao().loadAll();
    }
}
