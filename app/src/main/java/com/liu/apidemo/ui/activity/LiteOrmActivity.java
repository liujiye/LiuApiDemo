package com.liu.apidemo.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.liu.apidemo.R;
import com.liu.apidemo.entity.greendao.ShopGreenDao;
import com.liu.apidemo.entity.liteorm.ShopLiteOrm;
import com.liu.apidemo.theApp;
import com.liu.apidemo.util.RandomUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试LiteOrm
 * <p>
 *
 * @Table：用于类，默认使用类名，也可以自定义。如：@Table(“tb_user”)<p>
 * @PrimaryKey：主键，通过AssignType枚举来设置值，如：@PrimaryKey(AssignType.AUTO_INCREMENT) BY_MYSELF,<p>
 * AUTO_INCREMENT;//自增<p>
 * @Unique：唯一字段（行）<p>
 * @UniqueCombine：唯一（列中字段值唯一）<p>
 * @NotNull：字段部位none<p>
 * @Ingore：使用该注解表示字段不存入数据库<p>
 * @Mapping：表示关联关系，通过枚举Relation设置值，如： @Mapping(Relation.OneToOne)<p>
 * ManyToMany,//多对多<p>
 * OneToMany,//一对多<p>
 * ManyToOne,//多对一<p>
 * OneToOne;//一对一<p>
 * @Column：自定义字段名(列名)。如：@Column(“name”)<p>
 * @Conflict：数据库冲突策略，通过枚举Strategy设置值，可用于表，也可用于字段上 ROLLBACK(” ROLLBACK “),//回滚<p>
 * ABORT(” ABORT “),//中断<p>
 * FAIL(” FAIL “),//失败<p>
 * IGNORE(” IGNORE “),//忽略<p>
 * REPLACE(” REPLACE “);//替换
 */
public class LiteOrmActivity extends Activity
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
        ShopLiteOrm shopLiteOrm = theApp.getLiteOrm().queryById(id, ShopLiteOrm.class);
        if (shopLiteOrm != null)
        {
            long duration = System.currentTimeMillis() - start;
            String str = mTxtResult.getText().toString();
            str += "\n" + shopLiteOrm.toJsonString();
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
        ShopLiteOrm shopLiteOrm;
        long start = System.currentTimeMillis();
        List<ShopLiteOrm> list = new ArrayList<>();
        for (int i = 0; i < count; i++)
        {
            shopLiteOrm = new ShopLiteOrm();
            shopLiteOrm.setType(ShopGreenDao.TYPE_CART);
            shopLiteOrm.setAddress("广东深圳");
            shopLiteOrm.setImage_url("https://img.alicdn.com/bao/uploaded/i2/TB1N4V2PXXXXXa.XFXXXXXXXXXX_!!0-item_pic.jpg_640x640q50.jpg");
            shopLiteOrm.setPrice("19.40");
            shopLiteOrm.setSell_num(i);
            shopLiteOrm.setName("正宗梅菜扣肉 聪厨梅干菜扣肉 家宴常备方便菜虎皮红烧肉 2盒包邮" + i);
            list.add(shopLiteOrm);
            //GreenDaoUtil.insertShop(shop);
        }

        theApp.getLiteOrm().save(list);

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
        ShopLiteOrm shopLiteOrm;
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++)
        {
            shopLiteOrm = new ShopLiteOrm();
            shopLiteOrm.setType(ShopGreenDao.TYPE_CART);
            shopLiteOrm.setAddress("广东深圳");
            shopLiteOrm.setImage_url("https://img.alicdn.com/bao/uploaded/i2/TB1N4V2PXXXXXa.XFXXXXXXXXXX_!!0-item_pic.jpg_640x640q50.jpg");
            shopLiteOrm.setPrice("19.40");
            shopLiteOrm.setSell_num(i);
            shopLiteOrm.setName("正宗梅菜扣肉 聪厨梅干菜扣肉 家宴常备方便菜虎皮红烧肉 2盒包邮" + i);
            theApp.getLiteOrm().save(shopLiteOrm);

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
