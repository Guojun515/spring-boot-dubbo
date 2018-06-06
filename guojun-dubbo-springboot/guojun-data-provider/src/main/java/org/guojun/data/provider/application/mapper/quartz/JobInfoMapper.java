package org.guojun.data.provider.application.mapper.quartz;

import java.util.List;

import org.guojun.common.domain.quartz.JobInfo;

import tk.mybatis.mapper.common.Mapper;

public interface JobInfoMapper extends Mapper<JobInfo> {

	/**
	 * 查询所有的job信息
	 * @param queryParam
	 * @return
	 */
	public List<JobInfo> queryAllJobDetails(JobInfo queryParam);
}
