package com.te.micoservice.common.definition;

/**
 * Created by hcw07605 on 2017/7/3.
 */
public class PublicConsts {

    /**
     * 公用字符
     */
    public class CommonCosts {
        /**
         * UTF8编码
         */
        public static final String CHARSET_UTF8 = "UTF-8";
        /**
         *UTF-16LE编码
         */
        public static final String CHARSET_UTF16LE ="UTF-16LE";
        /**
         * 上传固定路径
         */
        public static final String FilePath = "WEB-INF/classes/";
        /**
         * 年月日标
         */
        public static final String Year = "year";
        public static final String Month = "month";
        public static final String Day = "day";
        /**
         * 一般时间格
         */
        public static final String DateFormat = "yyyy-MM-dd HH:mm:ss";
        public static final String YYYY_MM_DD ="yyyy-MM-dd";
        public static final String YYYY_MM ="yyyy-MM";

        /**
         * 符号
         * 根据大项目编码获取项目编
         */
        public static final String Comma  =",";
        public static  final String  PROJECTGROUP_CODE="ProjectGroup_Code";

    }

    public class JobConfig{
        public static final String JobDefaultRegCron="0/1 * * * * ?"; //"JobDefaultRegCron";
        public static final String JobDefaultPBJobAction= "MicoSerInitJob";//"JobRunningMonitorActionService";
        public static final String JobDefaultJobMethod=  "initTask";
        public static final String JobDefaultJobTriger=  "initTaskTriger";
        public static final String JobDefaultNickName="心跳任务";
        public static final String JobMapMethodKey="jobMethod";
        public static final String JobMapClassKey="jobClassName";
        public static final String JobMapProxyKey="jobProxy";
        //任务调度路径
        public static final String loadJobPath = "com.te.micoservice.common.serviceagent.job.";

    }
}

