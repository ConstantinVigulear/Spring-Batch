package com.vigulear.spring_batch.header_callback;

import org.springframework.batch.item.file.FlatFileHeaderCallback;

import java.io.IOException;
import java.io.Writer;

/**
 * @author Constantin Vigulear
 */
public class OverseasTradeIndexHeaderCallback implements FlatFileHeaderCallback {

  private static final String header =
      "Series_reference,Period,Data_value,STATUS,UNITS,MAGNTUDE,"
          + "Subject,Group,Series_title_1,Series_title_2,Series_title_3,Series_title_4,Series_title_5";

  @Override
  public void writeHeader(Writer writer) throws IOException {
    writer.write(header);
  }
}
