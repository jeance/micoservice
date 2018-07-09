package com.te.micoservice.model;

import java.util.Date;

public class Micoserviceregistry {
    private Integer msrid;

    private String msrservicename;

    private String msrserviceversion;

    private String msrserviceip;

    private Date msrregistrytime;

    private String msrserviceinfo;

    private Date createdtime;

    private Date updatedtime;

    private String remark;

    public Integer getMsrid() {
        return msrid;
    }

    public void setMsrid(Integer msrid) {
        this.msrid = msrid;
    }

    public String getMsrservicename() {
        return msrservicename;
    }

    public void setMsrservicename(String msrservicename) {
        this.msrservicename = msrservicename;
    }

    public String getMsrserviceversion() {
        return msrserviceversion;
    }

    public void setMsrserviceversion(String msrserviceversion) {
        this.msrserviceversion = msrserviceversion;
    }

    public String getMsrserviceip() {
        return msrserviceip;
    }

    public void setMsrserviceip(String msrserviceip) {
        this.msrserviceip = msrserviceip;
    }

    public Date getMsrregistrytime() {
        return msrregistrytime;
    }

    public void setMsrregistrytime(Date msrregistrytime) {
        this.msrregistrytime = msrregistrytime;
    }

    public String getMsrserviceinfo() {
        return msrserviceinfo;
    }

    public void setMsrserviceinfo(String msrserviceinfo) {
        this.msrserviceinfo = msrserviceinfo;
    }

    public Date getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    public Date getUpdatedtime() {
        return updatedtime;
    }

    public void setUpdatedtime(Date updatedtime) {
        this.updatedtime = updatedtime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}