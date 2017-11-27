package com.liu.apidemo.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.liu.apidemo.R;
import com.liu.apidemo.entity.liteorm.DeviceLiteOrm;
import com.liu.apidemo.theApp;
import com.liu.apidemo.util.RandomUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试LiteOrm 打开已经存在的sqlite数据库
 * <p>
 * 常用操作<br/>
 * 直接操作对象即可，LiteOrm会为你完成探测、建表等工作。<br/>
 * 保存（插入or更新）<br/>
 * School school = new School("hello");<br/>
 * liteOrm.save(school);<br/>
 * 插入<br/>
 * Book book = new Book("good");<br/>
 * liteOrm.insert(book, ConflictAlgorithm.Abort);<br/>
 * 更新<br/>
 * book.setIndex(1988);<br/>
 * book.setAuthor("hehe");<br/>
 * liteOrm.update(book);<br/>
 * 更新指定列<br/>
 * // 把所有书的author强制批量改为liter<br/>
 * HashMap<String, Object> bookIdMap = new HashMap<String, Object>();<br/>
 * bookIdMap.put(Book.COL_AUTHOR, "liter");<br/>
 * liteOrm.update(bookList, new ColumnsValue(bookIdMap), ConflictAlgorithm.Fail);<br/>
 * // 仅 author 这一列更新为该对象的最新值。<br/>
 * //liteOrm.update(bookList, new ColumnsValue(new String[]{Book.COL_AUTHOR}, null), ConflictAlgorithm.Fail);<br/>
 * 查询<br/>
 * List list = liteOrm.query(Book.class);<br/>
 * OrmLog.i(TAG, list);<br/>
 * 查找 使用WhereBuilder<br/>
 * List<Student> list = liteOrm.query(new QueryBuilder<Student>(Student.class)<br/>
 * .where(Person.COL_NAME + " LIKE ?", new String[]{"%0"})<br/>
 * .whereAppendAnd()<br/>
 * .whereAppend(Person.COL_NAME + " LIKE ?", new String[]{"%s%"}));<br/>
 * OrmLog.i(TAG, list);<br/>
 * 查询 根据ID<br/>
 * Student student = liteOrm.queryById(student1.getId(), Student.class);<br/>
 * OrmLog.i(TAG, student);<br/>
 * 查询 任意<br/>
 * List<Book> books = liteOrm.query(new QueryBuilder<Book>(Book.class)<br/>
 * .columns(new String[]{"id", "author", Book.COL_INDEX})<br/>
 * .distinct(true)<br/>
 * .whereGreaterThan("id", 0)<br/>
 * .whereAppendAnd()<br/>
 * .whereLessThan("id", 10000)<br/>
 * .limit(6, 9)<br/>
 * .appendOrderAscBy(Book.COL_INDEX));<br/>
 * OrmLog.i(TAG, books);<br/>
 * 删除 实体<br/>
 * // 删除 student-0<br/>
 * liteOrm.delete(student0);<br/>
 * 删除 指定数量<br/>
 * // 按id升序，删除[2, size-1]，结果：仅保留第一个和最后一个<br/>
 * // 最后一个参数可为null，默认按 id 升序排列<br/>
 * liteOrm.delete(Book.class, 2, bookList.size() - 1, "id");<br/>
 * 删除 使用WhereBuilder<br/>
 * // 删除 student-1<br/>
 * liteOrm.delete(new WhereBuilder(Student.class)<br/>
 * .where(Person.COL_NAME + " LIKE ?", new String[]{"%1%"})<br/>
 * .and()<br/>
 * .greaterThan("id", 0)<br/>
 * .and()<br/>
 * .lessThan("id", 10000));<br/>
 * 删除全部<br/>
 * // 连同其关联的classes，classes关联的其他对象一带删除<br/>
 * liteOrm.deleteAll(School.class);<br/>
 * liteOrm.deleteAll(Book.class);<br/>
 * <br/>
 * // 顺带测试：连库文件一起删掉<br/>
 * liteOrm.deleteDatabase();<br/>
 * // 顺带测试：然后重建一个新库<br/>
 * liteOrm.openOrCreateDatabase();<br/>
 * // 满血复活
 */
public class LiteOrmOtherActivity extends Activity
{
    private Button mBtnInsert;
    private Button mBtnInsertBatch;
    private Button mBtnRadomRead;
    private TextView mTxtResult;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liete_orm);

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
                    testReadAll();
                    break;
                }
            }
        };

        mBtnInsert.setOnClickListener(clickListener);
        mBtnInsertBatch.setOnClickListener(clickListener);
        mBtnRadomRead.setOnClickListener(clickListener);
    }
    
    private void testReadAll()
    {
        long start = System.currentTimeMillis();
        List<DeviceLiteOrm> deviceLiteOrms = theApp.getLiteOrmOther().query(DeviceLiteOrm.class);
        StringBuffer sb = new StringBuffer();
        if (deviceLiteOrms != null && deviceLiteOrms.size() > 0)
        {
            for (DeviceLiteOrm deviceLiteOrm : deviceLiteOrms)
            {
                sb.append("\n" + deviceLiteOrm.toJsonString());
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

    private void testRandomRead()
    {
        int id = RandomUtil.getRandom(0, 100000);
        long start = System.currentTimeMillis();
        DeviceLiteOrm deviceLiteOrm = theApp.getLiteOrmOther().queryById(id, DeviceLiteOrm.class);
        if (deviceLiteOrm != null)
        {
            long duration = System.currentTimeMillis() - start;
            String str = mTxtResult.getText().toString();
            str += "\n" + deviceLiteOrm.toJsonString();
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
        DeviceLiteOrm shopLiteOrm;
        long start = System.currentTimeMillis();
        List<DeviceLiteOrm> list = new ArrayList<>();
        for (int i = 0; i < count; i++)
        {
            shopLiteOrm = createDeviceLiteOrm(i);
            list.add(shopLiteOrm);
        }

        theApp.getLiteOrmOther().save(list);

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
        DeviceLiteOrm shopLiteOrm;
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++)
        {
            shopLiteOrm = createDeviceLiteOrm(i);
            theApp.getLiteOrmOther().save(shopLiteOrm);

            if (i % 1000 == 0)
            {
                countTime(i, start, sb);
            }
        }

        countTime(count, start, sb);
    }

    private DeviceLiteOrm createDeviceLiteOrm(int i)
    {
        DeviceLiteOrm deviceLiteOrm = new DeviceLiteOrm();
        deviceLiteOrm.deviceId = String.valueOf(i);
        deviceLiteOrm.errorCount = i;
        deviceLiteOrm.name = "Device " + i;
        deviceLiteOrm.sessionSecret = "session_secret_" + i;
        deviceLiteOrm.status = (i % 2 == 0 ? "online" : "offline");

        return deviceLiteOrm;
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
