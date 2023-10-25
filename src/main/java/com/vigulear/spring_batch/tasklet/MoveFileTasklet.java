package com.vigulear.spring_batch.tasklet;

import static com.vigulear.spring_batch.config.BatchConstants.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Objects;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * @author Constantin Vigulear
 */
@Component
public class MoveFileTasklet implements Tasklet {
  @Override
  public RepeatStatus execute(
      @NonNull StepContribution contribution, @NonNull ChunkContext chunkContext) {

    final File directory = new File(CSV_IMPORT_PATH);

    Arrays.stream(Objects.requireNonNull(directory.listFiles((dir, name) -> name.matches(".*?"))))
        .forEach(MoveFileTasklet::moveFileToProcessed);

    return RepeatStatus.FINISHED;
  }

  private static void moveFileToProcessed(File singleFile) {
    try {
      Files.move(
          singleFile.toPath(), // from
          Paths.get(CSV_PROCESSED_PATH + singleFile.getName()), // to
          StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
