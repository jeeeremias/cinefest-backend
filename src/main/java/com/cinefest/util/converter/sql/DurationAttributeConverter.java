package com.cinefest.util.converter.sql;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Duration;

@Converter(autoApply = true)
public class DurationAttributeConverter implements AttributeConverter<Duration, Long> {

  @Override
  public Long convertToDatabaseColumn(Duration duration) {
    return duration.toNanos();
  }

  @Override
  public Duration convertToEntityAttribute(Long aLong) {
    return Duration.ofNanos(aLong);
  }
}
