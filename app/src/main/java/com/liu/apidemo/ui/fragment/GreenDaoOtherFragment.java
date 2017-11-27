package com.liu.apidemo.ui.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.liu.apidemo.R;
import com.liu.apidemo.entity.greendao.DeviceGreenDao;
import com.liu.apidemo.util.RandomUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.liu.apidemo.theApp.getDaoSessionOther;

/**
 * 简单的RecyclerView演示
 */
public class GreenDaoOtherFragment extends BaseFragment
{
    @BindView(R.id.btn_insert) Button mBtnInsert;
    @BindView(R.id.btn_insert_batch) Button mBtnInsertBatch;
    @BindView(R.id.btn_random_read) Button mBtnRadomRead;
    @BindView(R.id.txt_result) TextView mTxtResult;

    public GreenDaoOtherFragment()
    {
    }

    @Override
    public int getContentViewId()
    {
        return R.layout.activity_green_dao;
    }

    @OnClick({R.id.btn_insert, R.id.btn_insert_batch,
              R.id.btn_random_read, R.id.btn_read_all})
    public void onViewClick(View view)
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

    private void testReadALl()
    {
        try
        {
            long start = System.currentTimeMillis();
            List<DeviceGreenDao> deviceGreenDaos = getDaoSessionOther().getDeviceGreenDaoDao().loadAll();
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
        getActivity().runOnUiThread(new Runnable()
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
        getActivity().runOnUiThread(new Runnable()
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
        getActivity().runOnUiThread(new Runnable()
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
