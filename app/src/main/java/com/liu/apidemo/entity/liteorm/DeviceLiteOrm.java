package com.liu.apidemo.entity.liteorm;

import com.litesuits.orm.db.annotation.Table;
import com.liu.apidemo.entity.GsonObj;

/**
 * Created by liujiye-pc on 2017/7/16.
 */

@Table("Device")
public class DeviceLiteOrm extends GsonObj<DeviceLiteOrm>
{
    public long id;
    /** id */
    public String deviceId;
    /** 名字 */
    public String name;
    /** 状态 */
    public String status;
    /** 待处理问题个数 */
    public int errorCount;
    /**  */
    public String sessionSecret;
}
