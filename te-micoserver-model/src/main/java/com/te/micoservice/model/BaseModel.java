package com.te.micoservice.model;

import java.util.Date;

/**
 * Created by cxj4842 on 2018/5/10.
 */
public class BaseModel {
    private String createdby = "";
    private Date createdOn = new Date();
    ;
    private String updatedBy = "";
    private Date updatedOn = new Date();
    ;
    private String remark = "";

    private short is_valid=0;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public short getIs_valid() {

        return is_valid;
    }

    public void setIs_valid(short is_valid) {
        this.is_valid = is_valid;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public String getCreatedby() {
        return this.createdby;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getCreatedOn() {
        return this.createdOn;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }
}
