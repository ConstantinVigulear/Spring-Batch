package com.vigulear.spring_batch.config;

import com.vigulear.spring_batch.entity.OverseasTradeIndexes;
import com.vigulear.spring_batch.tasklet.MoveFileTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.PlatformTransactionManager;

import static com.vigulear.spring_batch.config.BatchConstants.*;

/**
 * @author Constantin Vigulear
 */
@Configuration
public class SpringBatchConfig {

  private final MoveFileTasklet tasklet;
  private final CrudRepository<OverseasTradeIndexes, Long> crudRepository;
  private final JobRepository jobRepository;
  private final PlatformTransactionManager transactionManager;
  private final JobCompletionNotificationListener listener;
  private final OverseasTradeIndexesProcessor processor;

  public SpringBatchConfig(
      MoveFileTasklet tasklet,
      CrudRepository<OverseasTradeIndexes, Long> crudRepository,
      JobRepository jobRepository,
      PlatformTransactionManager transactionManager,
      JobCompletionNotificationListener listener,
      OverseasTradeIndexesProcessor processor) {
    this.tasklet = tasklet;
    this.crudRepository = crudRepository;
    this.jobRepository = jobRepository;
    this.transactionManager = transactionManager;
    this.listener = listener;
    this.processor = processor;
  }

  @Bean
  public FlatFileItemReader<OverseasTradeIndexes> reader() {
    return new FlatFileItemReaderBuilder<OverseasTradeIndexes>()
        .name("csvReader")
        .resource(new FileSystemResource(CSV_SOURCE_PATH))
        .strict(false)
        .delimited()
        .names(
            "Series_reference",
            "Period",
            "Data_value",
            "STATUS",
            "UNITS",
            "MAGNITUDE",
            "Subject",
            "Group",
            "Series_title_1",
            "Series_title_2",
            "Series_title_3",
            "Series_title_4",
            "Series_title_5")
        .linesToSkip(1)
        .targetType(OverseasTradeIndexes.class)
        .build();
  }

  @Bean
  public RepositoryItemWriter<OverseasTradeIndexes> writer() {
    return new RepositoryItemWriterBuilder<OverseasTradeIndexes>()
        .repository(crudRepository)
        .methodName("save")
        .build();
  }

  @Bean
  public Step step1() {
    return new StepBuilder("step1", jobRepository)
        .<OverseasTradeIndexes, OverseasTradeIndexes>chunk(CHUNK_SIZE, transactionManager)
        .reader(reader())
        .processor(processor)
        .writer(writer())
        .build();
  }

  @Bean
  public Step moveFiles() {
    return new StepBuilder("moveStep", jobRepository).tasklet(tasklet, transactionManager).build();
  }

  @Bean
  public Job importOverseasTradeIndexesJob() {
    return new JobBuilder("importOverseasTradeIndexesJob", jobRepository)
        .start(step1())
        .next(moveFiles())
        .listener(listener)
        .build();
  }
}
