package com.liu.apidemo.entity.liteorm;

import com.litesuits.orm.db.annotation.Default;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;
import com.liu.apidemo.entity.GsonObj;

import org.greenrobot.greendao.annotation.Unique;

/**
 */
@Table("shop_lite_orm")
public class ShopLiteOrm extends GsonObj<ShopLiteOrm>
{
    //表示为购物车列表
    public static final int TYPE_CART = 0x01;
    //表示为收藏列表
    public static final int TYPE_LOVE = 0x02;

    //不能用int
    // 指定自增，每个对象需要有一个主键
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private Long id;
    //商品名称
    @Unique
    private String name;
    //商品价格
    private String price;
    //已售数量
    private int sell_num;
    //图标url
    private String image_url;
    //商家地址
    private String address;
    // 打折信息
    // 数据库从1->2,增加了ratio字段，测试数据库的升级能力
    private float ratio;
    @Default("1.0")
    private float testDefault;
    //商品列表类型
    private int type;


    public ShopLiteOrm(Long id, String name, String price, int sell_num,
                       String image_url, String address, int type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.sell_num = sell_num;
        this.image_url = image_url;
        this.address = address;
        this.type = type;
    }

    public ShopLiteOrm() {
    }
    

    public Long getId()
    {
        return this.id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPrice()
    {
        return this.price;
    }

    public void setPrice(String price)
    {
        this.price = price;
    }

    public int getSell_num()
    {
        return this.sell_num;
    }

    public void setSell_num(int sell_num)
    {
        this.sell_num = sell_num;
    }

    public String getImage_url()
    {
        return this.image_url;
    }

    public void setImage_url(String image_url)
    {
        this.image_url = image_url;
    }

    public String getAddress()
    {
        return this.address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public int getType()
    {
        return this.type;
    }

    public void setType(int type)
    {
        this.type = type;
    }

    public float getRatio()
    {
        return ratio;
    }

    public void setRatio(float ratio)
    {
        this.ratio = ratio;
    }

    public float getTestDefault()
    {
        return testDefault;
    }

    public void setTestDefault(float testDefault)
    {
        this.testDefault = testDefault;
    }
}
