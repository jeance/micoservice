package com.te.micoservice.serviceagent.jobcore;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.PersistJobDataAfterExecution;

/**
 * Created by cxj4842 on 2018/5/24.
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class SerialJobProxy extends  JobProxy{

}
