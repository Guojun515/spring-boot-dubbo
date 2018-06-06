
package org.guojun.common.api.quartz;

import java.util.List;

import org.guojun.common.domain.quartz.JobInfo;

import com.github.pagehelper.Page;

/**
 * 
 * @Description 定时任务操作
 * @author Guojun
 * @Date 2018年4月21日 下午5:45:14
 *
 */
public interface IQuartzService {
	
	/**
	 * 查询所有job的信息
	 * @param queryParam
	 * @param pageNo
	 * @param pageSiez
	 * @return
	 */
	Page<JobInfo> queryAllJobInfo(JobInfo queryParam, int pageNo, int pageSiez);

	/**
	 * 新增job操作
	 * @param job
	 * @return
	 */
    void createJob(JobInfo job);
    
    /**
     * 立即执行job，通过新增一个执行一次的trigger实现
     * @param job
     */
    void executeJob(JobInfo job);
    
    /**
     * 暂停job,会暂停当前job的 所有trigger
     * @param jobInfos
     */
    void pauseJob(List<JobInfo> jobInfos);
    
    /**
     * 恢复job,会恢复当前job的 所有trigger
     * @param jobInfos
     */
    void resumeJob(List<JobInfo> jobInfos);
    
    /**
     * 暂停触发器
     * @param jobInfos
     */
    void pauseTrigger (List<JobInfo> jobInfos);
    
    /**
     * 恢复触发器
     * @param jobInfos
     */
    void resumeTrigger (List<JobInfo> jobInfos);
    
    /**
     * 暂停所有的job
     */
    void pauseAll();
    
    /**
     * 恢复所有的job
     */
    void resumeAll();
    
    /**
     * 删除job
     * @param jobKeys
     */
    void deleJobs(List<JobInfo> jobInfos);

}
