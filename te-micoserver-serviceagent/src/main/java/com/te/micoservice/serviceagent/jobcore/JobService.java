package com.te.micoservice.serviceagent.jobcore;


import com.te.micoservice.model.TepayJobConfig;
import com.te.micoservice.common.definition.PublicConsts;
import com.te.micoservice.common.utils.JsonUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by cxj4842 on 2018/4/24.
 */
@Component
public class JobService {
//    public void LoadJobs(){
//        System.out.print("123");
//    }
private static JobService instance;
    public static JobService getInstance() {
        return instance != null ? instance : new JobService();
    }
    private static final String observerName = "JobService";
   private static String groupName = JobScheduler.getInstance().getDefaultJobGroup();

    //private static final String test =PublicConsts.CommonCosts.CHARSET_UTF8;
   // private static String groupName ="";// JobScheduler.getInstance().getDefaultJobGroup();

    public void LoadJobs() throws Exception {
        try {
            String errMsg=null;
            String defaultJob=getDefaultJob();
            String path = PublicConsts.JobConfig.loadJobPath;
            List<TepayJobConfig> jobConfigModelList;
            jobConfigModelList = JsonUtils.jsonToModelList(defaultJob, TepayJobConfig.class);
            for (TepayJobConfig varBasePamentJobConfigItem : jobConfigModelList) {
                try {
                  String classPath = path.concat(varBasePamentJobConfigItem.getPjcJobClassName());
                    Class className = Class.forName(classPath);
                    Object object = className.newInstance();
                    JobScheduler.getInstance().addCronTriggerJob(varBasePamentJobConfigItem, groupName, object);
                } catch (Exception ex) {
                    errMsg = String.format("%s:%s", varBasePamentJobConfigItem.getPjcJobClassName(), ex.getMessage());
                  //  SkyLog.logError(ZookeeperHelper.class.getName(), errMsg, "", "");
                }
            }
            JobScheduler.getInstance().startJob();
        } catch (Exception ex) {
            String err = ex.getMessage();
          //  SkyLog.logError(ZookeeperHelper.class.getName(), err, "", "");
            throw new Exception(err);
        }
    }

    public void JobShedulerInit() throws Exception {
        JobScheduler.clear();
    }

    /// <summary>
    /// 获取触发器状态
    /// </summary>
    /// <param name="triggerName"></param>
    /// <returns></returns>
    public String GetTriggerStatus(String triggerName,String groupName) throws Exception {
        return JobScheduler.getInstance().getTriggerStatus(triggerName,groupName);
    }

    public static String getDefaultJob() {
        String defaultRegCron = "0/1 * * * * ?";   //ConfigCenterUtil.getValue(PublicConsts.JobConfig.JobDefaultRegCron);
        StringBuilder defaultJobStr = new StringBuilder();
        defaultJobStr.append("[");
        defaultJobStr.append("{");
        defaultJobStr.append("\"pjcId\":0,");
        defaultJobStr.append("\"pjcJobClassName\":\"".concat(PublicConsts.JobConfig.JobDefaultPBJobAction).concat("\","));
        defaultJobStr.append("\"pjcJobMethodName\":\"".concat(PublicConsts.JobConfig.JobDefaultJobMethod).concat("\","));
        defaultJobStr.append("\"pjcTriggerName\":\"".concat(PublicConsts.JobConfig.JobDefaultJobTriger).concat("\","));
        defaultJobStr.append("\"pjcNickName\":\"".concat(PublicConsts.JobConfig.JobDefaultNickName).concat("\","));
        defaultJobStr.append("\"pjcRegcron\":\"".concat(defaultRegCron).concat("\","));
        defaultJobStr.append("\"pjcIsParallel\":0,");
        defaultJobStr.append("\"is_valid\":1,");
        defaultJobStr.append("\"createdOn\":\"2017-08-23 20:58:56.797\",");
        defaultJobStr.append("\"createdby\":\"\",");
        defaultJobStr.append("\"updatedOn\":\"2017-08-23 20:58:56.797\",");
        defaultJobStr.append("\"updatedBy\":\"\",");
        defaultJobStr.append("\"pjcRowStatus\":0,");
        defaultJobStr.append("\"remark\":\"\"");
        defaultJobStr.append("}");
        defaultJobStr.append("]");
        return defaultJobStr.toString();
    }
}
