package com.te.micoservice.serviceagent.jobcore;

import com.te.micoservice.common.definition.PublicConsts;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.te.micoservice.common.other.ProxyFactory;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by cxj4842 on 2018/5/1.
 */
public class JobProxy implements Job {


    @Override
    /**
     * 任务调度的入口方法
     * @author cxj4842
     * @date 2018/5/9 10:39
     * @param jobExecutionContext
     * @return
     */
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
                ProxyFactory proxy = (ProxyFactory) jobExecutionContext.getJobDetail().getJobDataMap().get(PublicConsts.JobConfig.JobMapProxyKey);
                proxy.invoke();
        } catch (Throwable t) {
          //  SkyLog.logError(JobScheduler.class.getName(), t.getMessage(), "", "");
            throw new JobExecutionException(t.getMessage());
        }
    }
}
