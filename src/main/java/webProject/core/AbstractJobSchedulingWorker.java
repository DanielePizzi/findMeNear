package webProject.core;

import webProject.model.entity.Job;
import webProject.service.JobService;
import org.slf4j.Logger;

import java.util.Map;
import java.util.concurrent.Future;


public abstract class AbstractJobSchedulingWorker {
    void processResults(Map<Future<Job>, Job> result, JobService jobService, Logger LOG) {
        for(Future<Job> jobFuture : result.keySet()) {
            try {
                Job resultJob = jobFuture.get();
                LOG.info("Job Status: name="+resultJob.getName()+" status="+resultJob.getStatus());
                jobService.update(jobFuture.get());
            } catch (Exception e) {
                e.printStackTrace();
                Job failedJob = result.get(jobFuture);
                failedJob.setStatus(Job.Status.FAILED);
                try {
                    jobService.update(failedJob);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
