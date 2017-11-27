package com.liu.apidemo.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.liu.apidemo.R;
import com.liu.apidemo.entity.greendao.DeviceGreenDao;
import com.liu.apidemo.theApp;
import com.liu.apidemo.util.RandomUtil;

import java.util.ArrayList;
import java.util.List;

import static com.liu.apidemo.theApp.getDaoSessionOther;

/**
 * 测试greenDao
 */
public class GreenDaoOtherActivity extends Activity
{
    private Button mBtnInsert;
    private Button mBtnInsertBatch;
    private Button mBtnRadomRead;
    private Button mBtnReadAll;
    private TextView mTxtResult;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);

        mBtnInsert = (Button) findViewById(R.id.btn_insert);
        mBtnInsertBatch = (Button) findViewById(R.id.btn_insert_batch);
        mBtnRadomRead = (Button) findViewById(R.id.btn_random_read);
        mBtnReadAll = (Button) findViewById(R.id.btn_read_all);
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
                case R.id.btn_read_all:
                    testReadALl();
                    break;
                }
            }
        };

        mBtnInsert.setOnClickListener(clickListener);
        mBtnInsertBatch.setOnClickListener(clickListener);
        mBtnReadAll.setOnClickListener(clickListener);
        mBtnRadomRead.setOnClickListener(clickListener);
    }

    private void testReadALl()
    {
        try
        {
            long start = System.currentTimeMillis();
            List<DeviceGreenDao> deviceGreenDaos = theApp.getDaoSessionOther().getDeviceGreenDaoDao().loadAll();
            StringBuffer sb = new StringBuffer();
            if (deviceGreenDaos != null && deviceGreenDaos.size() > 0)
            {
                for (DeviceGreenDao deviceGreenDao : deviceGreenDaos)
                {
                    sb.append("\n" + deviceGreenDao.toJsonString());
                }
            }
            else
            {
                sb.append("\n读取失败");
            }
            long duration = System.currentTimeMillis() - start;
            sb.append("\n耗时：" + duration);

            mTxtResult.setText(sb.toString());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void testRandomRead()
    {
        long id = RandomUtil.getRandom(0, 100000);
        long start = System.currentTimeMillis();
        DeviceGreenDao deviceGreenDao = getDaoSessionOther().getDeviceGreenDaoDao().load(id);
        if (deviceGreenDao != null)
        {
            long duration = System.currentTimeMillis() - start;
            String str = mTxtResult.getText().toString();
            str += "\n" + deviceGreenDao.toJsonString();
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
        DeviceGreenDao shopGreenDao;
        long start = System.currentTimeMillis();
        List<DeviceGreenDao> list = new ArrayList<>();
        for (int i = 0; i < count; i++)
        {
            shopGreenDao = createDeviceDao(i);
            list.add(shopGreenDao);
            //GreenDaoUtil.insertShop(shop);
        }

        getDaoSessionOther().getDeviceGreenDaoDao().insertInTx(list);

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
        DeviceGreenDao deviceGreenDao;
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++)
        {
            deviceGreenDao = createDeviceDao(i);
            getDaoSessionOther().getDeviceGreenDaoDao().insert(deviceGreenDao);

            if (i % 1000 == 0)
            {
                countTime(i, start, sb);
            }
        }

        countTime(count, start, sb);
    }

    private DeviceGreenDao createDeviceDao(int i)
    {
        DeviceGreenDao deviceGreenDao = new DeviceGreenDao();
        deviceGreenDao.deviceId = String.valueOf(i);
        deviceGreenDao.errorCount = i;
        deviceGreenDao.name = "Device " + i;
        deviceGreenDao.sessionSecret = "session_secret_" + i;
        deviceGreenDao.status = "status_" + i;

        return deviceGreenDao;
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
