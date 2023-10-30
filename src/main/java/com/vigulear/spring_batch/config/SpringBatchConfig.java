package com.vigulear.spring_batch.config;

import com.vigulear.spring_batch.dto.OverseasTradeIndexDTO;
import com.vigulear.spring_batch.entity.OverseasTradeIndex;
import com.vigulear.spring_batch.header_callback.OverseasTradeIndexHeaderCallback;
import com.vigulear.spring_batch.listener.JobCompletionNotificationListener;
import com.vigulear.spring_batch.processor.ImportProcessor;
import com.vigulear.spring_batch.processor.ExportProcessor;
import com.vigulear.spring_batch.repository.OverseasTradeIndexRepository;
import com.vigulear.spring_batch.tasklet.MoveFileTasklet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.SkipListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.builder.MultiResourceItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.HashMap;
import java.util.Map;

import static com.vigulear.spring_batch.config.BatchConstants.*;

/**
 * @author Constantin Vigulear
 */
@Configuration
public class SpringBatchConfig {
  private static final Logger LOG = LoggerFactory.getLogger(OverseasTradeIndex.class);

  @Value("classpath*:/import/*.csv")
  private Resource[] inputFiles;

  private final MoveFileTasklet tasklet;
  private final OverseasTradeIndexRepository overseasTradeIndexRepository;
  private final JobRepository jobRepository;
  private final PlatformTransactionManager transactionManager;
  private final JobCompletionNotificationListener listener;
  private final ImportProcessor importProcessor;
  private final ExportProcessor exportProcessor;

  public SpringBatchConfig(
      MoveFileTasklet tasklet,
      OverseasTradeIndexRepository overseasTradeIndexRepository,
      JobRepository jobRepository,
      PlatformTransactionManager transactionManager,
      JobCompletionNotificationListener listener,
      ImportProcessor importProcessor,
      ExportProcessor exportProcessor) {
    this.tasklet = tasklet;
    this.overseasTradeIndexRepository = overseasTradeIndexRepository;
    this.jobRepository = jobRepository;
    this.transactionManager = transactionManager;
    this.listener = listener;
    this.importProcessor = importProcessor;
    this.exportProcessor = exportProcessor;
  }

  @Bean
  public FlatFileItemReader<OverseasTradeIndexDTO> singleFlatFileReader() {
    return new FlatFileItemReaderBuilder<OverseasTradeIndexDTO>()
        .name("readerFlatFile")
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
        .fieldSetMapper(
            new BeanWrapperFieldSetMapper<>() {
              {
                setTargetType(OverseasTradeIndexDTO.class);
              }
            })
        .build();
  }

  @Bean
  public MultiResourceItemReader<OverseasTradeIndexDTO> multiResourceReader() {
    return new MultiResourceItemReaderBuilder<OverseasTradeIndexDTO>()
        .name("multiResourceReader")
        .resources(inputFiles)
        .delegate(singleFlatFileReader())
        .build();
  }

  @Bean
  public RepositoryItemReader<OverseasTradeIndex> dataBaseReader() {

    Map<String, Sort.Direction> sortingMap = new HashMap<>();
    sortingMap.put("id", Sort.Direction.ASC);

    return new RepositoryItemReaderBuilder<OverseasTradeIndex>()
        .name("readerDataBase")
        .methodName("findAll")
        .repository(overseasTradeIndexRepository)
        .sorts(sortingMap)
        .build();
  }

  @Bean
  public RepositoryItemWriter<OverseasTradeIndex> dataBaseWriter() {
    return new RepositoryItemWriterBuilder<OverseasTradeIndex>()
        .repository(overseasTradeIndexRepository)
        .methodName("save")
        .build();
  }

  @Bean
  public FlatFileItemWriter<OverseasTradeIndexDTO> flatFileWriter() {
    return new FlatFileItemWriterBuilder<OverseasTradeIndexDTO>()
        .name("writerFlatFile")
        .resource(new FileSystemResource(CSV_EXPORT_PATH + "OverseasTradeIndexExported.csv"))
        .headerCallback(new OverseasTradeIndexHeaderCallback())
        .delimited()
        .delimiter(",")
        .names(
            "seriesReference",
            "period",
            "dataValue",
            "status",
            "units",
            "magnitude",
            "subject",
            "group",
            "seriesTitle1",
            "seriesTitle2",
            "seriesTitle3",
            "seriesTitle4",
            "seriesTitle5")
        .shouldDeleteIfExists(true)
        .build();
  }

  @Bean
  public Step importStep() {
    return new StepBuilder("importOverseasTradeIndexToDataBase", jobRepository)
        .<OverseasTradeIndexDTO, OverseasTradeIndex>chunk(CHUNK_SIZE, transactionManager)
        .reader(multiResourceReader())
        .processor(importProcessor)
        .writer(dataBaseWriter())
        .faultTolerant()
        .skipPolicy(skipPolicy())
        .listener(skipListener())
        .allowStartIfComplete(true)
        .build();
  }

  @Bean
  public Step exportStep() {
    return new StepBuilder("exportOverseasTradeIndexToCSV", jobRepository)
        .<OverseasTradeIndex, OverseasTradeIndexDTO>chunk(CHUNK_SIZE, transactionManager)
        .reader(dataBaseReader())
        .processor(exportProcessor)
        .writer(flatFileWriter())
        .allowStartIfComplete(true)
        .build();
  }

  @Bean
  public Step moveProcessedFiles() {
    return new StepBuilder("moveStep", jobRepository)
        .tasklet(tasklet, transactionManager)
        .allowStartIfComplete(true)
        .build();
  }

  @Bean
  public Job importJob() {
    return new JobBuilder("importOverseasTradeIndexesJob", jobRepository)
        .start(importStep())
        .next(moveProcessedFiles())
        .listener(listener)
        .build();
  }

  @Bean
  public Job exportJob() {
    return new JobBuilder("exportOverseasTradeIndexesJob", jobRepository)
        .start(exportStep())
        .listener(listener)
        .build();
  }

  public SkipPolicy skipPolicy() {
    return (t, skipCount) -> t instanceof ValidationException;
  }

  public SkipListener<OverseasTradeIndexDTO, OverseasTradeIndex> skipListener() {
    return new SkipListener<>() {

      @Override
      public void onSkipInProcess(@NonNull OverseasTradeIndexDTO item, @NonNull Throwable t) {
        LOG.warn("Skipped " + item + " - " + t.getMessage());
      }
    };
  }
}
