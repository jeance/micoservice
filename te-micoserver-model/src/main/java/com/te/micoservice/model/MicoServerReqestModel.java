package com.te.micoservice.model;

/**
 * Created by cxj4842 on 2018/7/5.
 */
public class MicoServerReqestModel {
    private String ServiceName="";
    private String ServiceVersion="";
    private String ReuqestBody="";

    public String getServiceName() {
        return ServiceName;
    }

    public void setServiceName(String serviceName) {
        ServiceName = serviceName;
    }

    public String getServiceVersion() {
        return ServiceVersion;
    }

    public void setServiceVersion(String serviceVersion) {
        ServiceVersion = serviceVersion;
    }

    public String getReuqestBody() {
        return ReuqestBody;
    }

    public void setReuqestBody(String reuqestBody) {
        ReuqestBody = reuqestBody;
    }
}
