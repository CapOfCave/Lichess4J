package me.kecker.jlichess.http.adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class LocalDateTimeTypeAdapter extends TypeAdapter<LocalDateTime> {

  @Override
  public void write(JsonWriter out, LocalDateTime value) throws IOException {
    if (value == null) {
      out.nullValue();
      return;
    }
    long epochMillis = value.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    out.value(epochMillis);
  }

  @Override
  public LocalDateTime read(JsonReader in) throws IOException {
    if (in == null) {
      return null;
    }
    return LocalDateTime.ofInstant(Instant.ofEpochMilli(in.nextLong()), ZoneId.systemDefault());

  }

}
