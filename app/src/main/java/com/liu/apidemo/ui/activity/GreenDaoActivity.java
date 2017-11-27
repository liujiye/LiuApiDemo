package com.liu.apidemo.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.liu.apidemo.R;
import com.liu.apidemo.entity.greendao.ShopGreenDao;
import com.liu.apidemo.theApp;
import com.liu.apidemo.util.GreenDaoUtil;
import com.liu.apidemo.util.RandomUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试greenDao
 */
public class GreenDaoActivity extends Activity
{
    private Button mBtnInsert;
    private Button mBtnInsertBatch;
    private Button mBtnRadomRead;
    private TextView mTxtResult;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);

        mBtnInsert = (Button) findViewById(R.id.btn_insert);
        mBtnInsertBatch = (Button) findViewById(R.id.btn_insert_batch);
        mBtnRadomRead = (Button) findViewById(R.id.btn_random_read);
        mTxtResult = (TextView) findViewById(R.id.txt_result);

        View.OnClickListener clickListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                switch (view.getId())
                {
                case R.id.btn_insert:
                    Runnable runnable = new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            testInsert();
                        }
                    };
                    new Thread(runnable).start();
                    break;
                case R.id.btn_insert_batch:
                    Runnable runnableBatch = new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            testInsertBatch();
                        }
                    };
                    new Thread(runnableBatch).start();
                    break;
                case R.id.btn_random_read:
                    testRandomRead();
                    break;
                }
            }
        };

        mBtnInsert.setOnClickListener(clickListener);
        mBtnInsertBatch.setOnClickListener(clickListener);
        mBtnRadomRead.setOnClickListener(clickListener);
    }

    private void testRandomRead()
    {
        int id = RandomUtil.getRandom(0, 100000);
        long start = System.currentTimeMillis();
        ShopGreenDao shopGreenDao = theApp.getDaoSession().getShopGreenDaoDao().load((long) id);
        if (shopGreenDao != null)
        {
            long duration = System.currentTimeMillis() - start;
            String str = mTxtResult.getText().toString();
            str += "\n" + shopGreenDao.toJsonString();
            str += " 耗时：" + duration;
            mTxtResult.setText(str);
        }
        else
        {
            String str = mTxtResult.getText().toString();
            str += "\n" + "没有获取到 " + id;
            mTxtResult.setText(str);
        }
    }

    private void testInsertBatch()
    {
        final StringBuilder sb = new StringBuilder();
        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                mTxtResult.setText("开始...");
                mBtnInsert.setEnabled(false);
            }
        });

        int count = 100000;
        ShopGreenDao shopGreenDao;
        long start = System.currentTimeMillis();
        List<ShopGreenDao> list = new ArrayList<>();
        for (int i = 0; i < count; i++)
        {
            shopGreenDao = new ShopGreenDao();
            shopGreenDao.setType(ShopGreenDao.TYPE_CART);
            shopGreenDao.setAddress("广东深圳");
            shopGreenDao.setImage_url("https://img.alicdn.com/bao/uploaded/i2/TB1N4V2PXXXXXa.XFXXXXXXXXXX_!!0-item_pic.jpg_640x640q50.jpg");
            shopGreenDao.setPrice("19.40");
            shopGreenDao.setSell_num(i);
            shopGreenDao.setName("正宗梅菜扣肉 聪厨梅干菜扣肉 家宴常备方便菜虎皮红烧肉 2盒包邮" + i);
            list.add(shopGreenDao);
            //GreenDaoUtil.insertShop(shop);
        }

        theApp.getDaoSession().getShopGreenDaoDao().insertInTx(list);

        countTime(count, start, sb);
    }

    private void testInsert()
    {
        final StringBuilder sb = new StringBuilder();
        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                mTxtResult.setText("开始...");
                mBtnInsert.setEnabled(false);
            }
        });
        int count = 100000;
        ShopGreenDao shopGreenDao;
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++)
        {
            shopGreenDao = new ShopGreenDao();
            shopGreenDao.setType(ShopGreenDao.TYPE_CART);
            shopGreenDao.setAddress("广东深圳");
            shopGreenDao.setImage_url("https://img.alicdn.com/bao/uploaded/i2/TB1N4V2PXXXXXa.XFXXXXXXXXXX_!!0-item_pic.jpg_640x640q50.jpg");
            shopGreenDao.setPrice("19.40");
            shopGreenDao.setSell_num(i);
            shopGreenDao.setName("正宗梅菜扣肉 聪厨梅干菜扣肉 家宴常备方便菜虎皮红烧肉 2盒包邮" + i);
            GreenDaoUtil.insertShop(shopGreenDao);

            if (i % 1000 == 0)
            {
                countTime(i, start, sb);
            }
        }

        countTime(count, start, sb);
    }

    private void countTime(int runCount, long start, final StringBuilder sb)
    {
        long duration = System.currentTimeMillis() - start;
        sb.append("\n" + runCount + " 条费时：" + duration);
        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                mTxtResult.setText(sb.toString());
                mBtnInsert.setEnabled(true);
            }
        });
    }
}
