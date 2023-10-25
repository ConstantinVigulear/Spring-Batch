package com.vigulear.spring_batch.config;

/**
 * @author Constantin Vigulear
 */
public final class BatchConstants {
  public static final String CSV_SOURCE_PATH =
      "src/main/resources/import/overseas-trade-indexes-june-2023-quarter-provisional.csv";
  public static final String CSV_PROCESSED_PATH = "src/main/resources/processed/";
  public static final String CSV_IMPORT_PATH = "src/main/resources/import/";
  public static final int CHUNK_SIZE = 1000;
}
