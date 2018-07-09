package com.te.micoservice.serviceagent.jobcore;


import com.te.micoservice.model.TepayJobConfig;
import com.te.micoservice.common.definition.PublicEnum;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import com.te.micoservice.common.other.ProxyFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by cxj4842 on 2018/4/28.
 */
public class JobScheduler {
    private static String defaultJobGroup = "DefaultJobGroup";
    private static JobScheduler instance;
    public static Scheduler scheduler;

    public static JobScheduler getInstance() {
        return instance != null ? instance : new JobScheduler();
    }

    private JobScheduler() {
        try {
            scheduler = new StdSchedulerFactory().getScheduler();
        } catch (Exception ex) {
           // SkyLog.logError(JobScheduler.class.getName(), ex.getMessage(), "", "");
            System.out.print("123");
        }
    }

    public String getDefaultJobGroup() {
        return defaultJobGroup;
    }

    public void addCronTriggerJob(TepayJobConfig jobConfigModel, String groupName, Object model)
            throws Exception {
        try {
            //  groupName = StringHelper.isNullOrEmpty(groupName) ? defaultJobGroup : groupName;
            groupName = jobConfigModel.getPjcJobClassName();
            String triggerName = String.format("%s.%s", jobConfigModel.getPjcJobClassName(), jobConfigModel.getPjcTriggerName());
            ProxyFactory pf = new ProxyFactory(model, jobConfigModel.getPjcJobMethodName());
            JobDetail jobDetail = createJobDetail(pf, jobConfigModel.getPjcJobMethodName(), groupName, jobConfigModel.getPjcIsParallel());
            Trigger trigger = TriggerBuilder.newTrigger()
                    .forJob(jobDetail)//多个触发器可以对应一个jobDeatil，初始化的时候就需要绑定job
                    .withIdentity(triggerName, groupName)
                    .withSchedule(
                            CronScheduleBuilder.cronSchedule(jobConfigModel.getPjcRegcron()))
                    .build();
            if (!scheduler.checkExists(jobDetail.getKey())) {
                scheduler.scheduleJob(jobDetail, trigger);
            } else {
                scheduler.scheduleJob(trigger);
            }

        } catch (SchedulerException se) {
         //   SkyLog.logError(JobScheduler.class.getName(), se.getMessage(), "", "");
            throw new Exception(se.getMessage());
        }
    }

    public JobDetail createJobDetail(ProxyFactory pf, String jobName, String groupName, int isParallel) throws SchedulerException {
        Class classType = null;
        if (isParallel == PublicEnum.JobRunModel.Parallel.getIndex()) {
            JobProxy jp = new JobProxy();
            classType = jp.getClass();
        } else {
            SerialJobProxy jp = new SerialJobProxy();
            classType = jp.getClass();
        }
        JobKey jobKey = new JobKey(jobName, groupName);
        //需要注意构建Job的时候必须设置.storeDurably() 在添加Job到调度引擎当中的时候会抛出异常。
        JobDetail jobDetail = JobBuilder.newJob(classType).withIdentity(jobKey).storeDurably().build();
        jobDetail.getJobDataMap().put("jobProxy",pf);
        return jobDetail;
    }


    public void startJob() throws Exception {
        try {
            scheduler.start();
        } catch (SchedulerException se) {
        //    SkyLog.logError(JobScheduler.class.getName(), se.getMessage(), "", "");
            throw new Exception(se.getMessage());
        }
    }

    public String getTriggerStatus(String triggerName, String groupName) throws Exception {
        TriggerKey triggerKey = new TriggerKey(triggerName, groupName);
        Trigger.TriggerState state = scheduler.getTriggerState(triggerKey);
        String result = state.name();
        return result;
    }

    public List<String> getAllTriggerName() throws Exception {
        List<String> triggerNames = new ArrayList<>();
        Set<TriggerKey> keys = scheduler.getTriggerKeys(GroupMatcher.triggerGroupEquals(defaultJobGroup));
        for (TriggerKey item : keys) {
            triggerNames.add(item.getName());
        }
        return triggerNames;
    }

    public static void clear() throws Exception {
        try {
            scheduler.clear();
            scheduler.shutdown();
            scheduler=null;
            instance=null;
        } catch (SchedulerException se) {
           // SkyLog.logError(JobScheduler.class.getName(), se.getMessage(), "", "");
            throw new Exception(se.getMessage());
        }
    }
}
