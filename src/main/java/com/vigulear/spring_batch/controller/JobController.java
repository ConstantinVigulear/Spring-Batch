package com.vigulear.spring_batch.controller;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Constantin Vigulear
 */
@RestController
@RequestMapping("/jobs")
public class JobController {

  private final JobLauncher jobLauncher;
  private final Job importJob;
  private final Job exportJob;

  public JobController(JobLauncher jobLauncher, Job importJob, Job exportJob) {
    this.jobLauncher = jobLauncher;
    this.importJob = importJob;
    this.exportJob = exportJob;
  }

  @PostMapping
  public void importCsvToDataBaseJob() {
    runJob(importJob);
  }

  @GetMapping
  public void exportDataBaseBToCsv() {
    runJob(exportJob);
  }

  private void runJob(Job job) {
    JobParameters jobParameters = new JobParametersBuilder().toJobParameters();

    try {
      jobLauncher.run(job, jobParameters);
    } catch (JobInstanceAlreadyCompleteException
        | JobExecutionAlreadyRunningException
        | JobParametersInvalidException
        | JobRestartException e) {
      throw new RuntimeException(e);
    }
  }
}
