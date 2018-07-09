package com.te.micoservice.serviceagent.job;

import com.te.micoservice.serviceagent.MicoSerInit;

import java.util.Map;

/**
 * Created by cxj4842 on 2018/7/7.
 */

public class MicoSerInitJob {
  //  @Autowired
  //  private MicoSerInit micoSerInit;
    public void initTask()
    {
        printService(MicoSerInit.serviceName);
       // System.out.println("InitTask");
    }
    private void printService( Map<String, Map<String, Object>> serviceName) {
        for (String key : serviceName.keySet()) {
            System.out.println("服务名：" + key);
            for (Map.Entry<String, Object> temp : serviceName.get(key).entrySet()) {
                System.out.println("服务方法名：" + temp.getKey());
            }
        }
    }
}
