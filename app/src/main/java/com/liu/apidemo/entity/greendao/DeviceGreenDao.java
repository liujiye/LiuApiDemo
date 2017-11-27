package com.liu.apidemo.entity.greendao;

import com.liu.apidemo.entity.GsonObj;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by liujiye-pc on 2017/7/16.
 */
@Entity(nameInDb="Device")
public class DeviceGreenDao extends GsonObj<DeviceGreenDao>
{
    //不能用int
    @Id(autoincrement = true)
    public long id;
    /** id */
    @Property(nameInDb = "id")
    public String deviceId;
    /** 名字 */
    @Property(nameInDb = "name")
    public String name;
    /** 状态 */
    @Property(nameInDb = "status")
    public String status;
    /** 待处理问题个数 */
    @Property(nameInDb = "errorCount")
    public int errorCount;
    /**  */
    @Property(nameInDb = "sessionSecret")
    public String sessionSecret;
    @Generated(hash = 1525871054)
    public DeviceGreenDao(long id, String deviceId, String name, String status,
            int errorCount, String sessionSecret) {
        this.id = id;
        this.deviceId = deviceId;
        this.name = name;
        this.status = status;
        this.errorCount = errorCount;
        this.sessionSecret = sessionSecret;
    }
    @Generated(hash = 386915134)
    public DeviceGreenDao() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getDeviceId() {
        return this.deviceId;
    }
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getStatus() {
        return this.status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public int getErrorCount() {
        return this.errorCount;
    }
    public void setErrorCount(int errorCount) {
        this.errorCount = errorCount;
    }
    public String getSessionSecret() {
        return this.sessionSecret;
    }
    public void setSessionSecret(String sessionSecret) {
        this.sessionSecret = sessionSecret;
    }
}
