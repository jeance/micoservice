package com.te.micoservice.model;


public class TepayJobConfig extends BaseModel {
    private Long pjcId;

    private String pjcJobClassName;

    private String pjcJobMethodName;

    private String pjcRegcron;

    private int pjcRowStatus=0;

    private String pjcNickName;

    private String pjcTriggerName;

    private String pjcRequestUrl;

    private int pjcIsParallel=-1;

    public int getPjcIsParallel() {
        return pjcIsParallel;
    }

    public void setPjcIsParallel(int pjcIsParallel) {
        this.pjcIsParallel = pjcIsParallel;
    }

    public String getPjcRequestUrl() {
        return pjcRequestUrl;
    }

    public void setPjcRequestUrl(String pjcRequestUrl) {
        this.pjcRequestUrl = pjcRequestUrl;
    }

    public String getPjcTriggerName() {
        return pjcTriggerName;
    }

    public void setPjcTriggerName(String pjcTriggerName) {
        this.pjcTriggerName = pjcTriggerName;
    }

    public Long getPjcId() {
        return pjcId;
    }

    public void setPjcId(Long pjcId) {
        this.pjcId = pjcId;
    }

    public String getPjcJobClassName() {
        return pjcJobClassName;
    }

    public void setPjcJobClassName(String pjcJobClassName) {
        this.pjcJobClassName = pjcJobClassName;
    }

    public String getPjcJobMethodName() {
        return pjcJobMethodName;
    }

    public void setPjcJobMethodName(String pjcJobMethodName) {
        this.pjcJobMethodName = pjcJobMethodName;
    }

    public String getPjcRegcron() {
        return pjcRegcron;
    }

    public void setPjcRegcron(String pjcRegcron) {
        this.pjcRegcron = pjcRegcron;
    }

    public Integer getPjcRowStatus() {
        return pjcRowStatus;
    }

    public void setPjcRowStatus(int pjcRowStatus) {
        this.pjcRowStatus = pjcRowStatus;
    }

    public String getPjcNickName() {
        return pjcNickName;
    }

    public void setPjcNickName(String pjcNickName) {
        this.pjcNickName = pjcNickName;
    }
}