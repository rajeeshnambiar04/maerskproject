package com.rajeesh.maersk.service;

import com.rajeesh.maersk.model.JobInformation;
import com.rajeesh.maersk.model.JobDetail;
import com.rajeesh.maersk.model.JobResponse;
import com.rajeesh.maersk.model.JobTrigger;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.quartz.JobKey.jobKey;
import static org.quartz.impl.matchers.GroupMatcher.groupEquals;

@Slf4j
@Service
public class SchedulerService {
    private Scheduler scheduler;

    public SchedulerService(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void getSchedulerInformation() throws SchedulerException {
        SchedulerMetaData schedulerMetaData = scheduler.getMetaData();
/*
        JobInformation quartzInformation = new JobInformation();
        quartzInformation.setVersion(schedulerMetaData.getVersion());
        quartzInformation.setSchedulerName(schedulerMetaData.getSchedulerName());
        quartzInformation.setInstanceId(schedulerMetaData.getSchedulerInstanceId());

        quartzInformation.setThreadPoolClass(schedulerMetaData.getThreadPoolClass());
        quartzInformation.setNumberOfThreads(schedulerMetaData.getThreadPoolSize());

        quartzInformation.setSchedulerClass(schedulerMetaData.getSchedulerClass());
        quartzInformation.setClustered(schedulerMetaData.isJobStoreClustered());

        quartzInformation.setJobStoreClass(schedulerMetaData.getJobStoreClass());
        quartzInformation.setNumberOfJobsExecuted(schedulerMetaData.getNumberOfJobsExecuted());

        quartzInformation.setInStandbyMode(schedulerMetaData.isInStandbyMode());
        quartzInformation.setStartTime(schedulerMetaData.getRunningSince());
*/
        for (String groupName : scheduler.getJobGroupNames()) {
            List<String> simpleJobList = new ArrayList<>();

            for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                String jobName = jobKey.getName();
                String jobGroup = jobKey.getGroup();

                List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
                Date nextFireTime = triggers.get(0).getNextFireTime();
                Date lastFireTime = triggers.get(0).getPreviousFireTime();

                simpleJobList.add(String.format("%1s.%2s - next run: %3s (previous run: %4s)", jobGroup, jobName, nextFireTime, lastFireTime));
            }

          //  quartzInformation.setSimpleJobDetail(simpleJobList);
        }

        //return quartzInformation;
    }

    public List<JobKey> getJobKeys() throws SchedulerException {
        List<JobKey> jobKeys = new ArrayList<>();

            for (String group : scheduler.getTriggerGroupNames()) {
                jobKeys.addAll(scheduler.getJobKeys(groupEquals(group)));
            }

        return jobKeys;
    }

    public JobDetail getJobDetail(String name, String group) throws SchedulerException {
        org.quartz.JobDetail jobDetail = scheduler.getJobDetail(jobKey(name, group));

        JobDetail quartzJobDetail = new JobDetail();
        BeanUtils.copyProperties(jobDetail, quartzJobDetail);

        List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey(name, group));

        if (CollectionUtils.isNotEmpty(triggers)) {
            List<JobTrigger> quartzTriggers = new ArrayList<>();

            for (Trigger trigger : triggers) {
            	JobTrigger quartzTrigger = new JobTrigger();
                BeanUtils.copyProperties(trigger, quartzTrigger);

                if (trigger instanceof SimpleTrigger) {
                    SimpleTrigger simpleTrigger = (SimpleTrigger) trigger;
/*
                    quartzTrigger.setTriggerType(simpleTrigger.getClass().getSimpleName());
                    quartzTrigger.setRepeatInterval(simpleTrigger.getRepeatInterval());
                    quartzTrigger.setRepeatCount(simpleTrigger.getRepeatCount());
                    quartzTrigger.setTimesTriggered(simpleTrigger.getTimesTriggered());*/
                } else if (trigger instanceof CronTrigger) {
                    CronTrigger cronTrigger = (CronTrigger) trigger;

                    /*quartzTrigger.setTriggerType(cronTrigger.getClass().getSimpleName());
                    quartzTrigger.setTimeZone(cronTrigger.getTimeZone());
                    quartzTrigger.setCronExpression(cronTrigger.getCronExpression());
                    quartzTrigger.setExpressionSummary(cronTrigger.getExpressionSummary()); */
                }

                quartzTriggers.add(quartzTrigger);
            }

            //quartzJobDetail.setTriggers(quartzTriggers);
        }

        return quartzJobDetail;
    }

public JobResponse deleteJobDetail(String name, String group) throws SchedulerException {
    JobResponse quartzResponse = new JobResponse();
    //quartzResponse.setType(JobResponse.ResponseType.DELETE);
    //quartzResponse.setName(name);
    //quartzResponse.setGroup(group);

    boolean result = scheduler.deleteJob(jobKey(name, group));
    //quartzResponse.setResult(result);
    //quartzResponse.setStatus(String.format("%1s.%2s has been successfully deleted", group, name));

    return quartzResponse;
}
}
