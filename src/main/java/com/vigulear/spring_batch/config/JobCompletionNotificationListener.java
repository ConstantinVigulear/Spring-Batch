package com.vigulear.spring_batch.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;


/**
 * @author Constantin Vigulear
 */
@Component
public class JobCompletionNotificationListener implements JobExecutionListener {
  private static final Logger LOG = LogManager.getLogger(JobCompletionNotificationListener.class);

  @Override
  public void afterJob(JobExecution jobExecution) {
    if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
      LOG.info("!!! JOB FINISHED! Time to verify the results");
    }
  }
}
