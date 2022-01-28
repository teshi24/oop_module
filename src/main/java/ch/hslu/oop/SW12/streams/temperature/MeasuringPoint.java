package ch.hslu.oop.SW12.streams.temperature;

import java.time.LocalDateTime;
import java.util.Objects;

public final class MeasuringPoint implements Comparable<MeasuringPoint> {

  private final Temperature temperature;
  private final LocalDateTime timestamp;

  public MeasuringPoint(final Temperature temperature) {
    this(temperature, LocalDateTime.now());
  }

  public MeasuringPoint(final Temperature temperature, final LocalDateTime timestamp) {
    this.temperature = new Temperature(temperature);
    this.timestamp = timestamp;
  }

  public MeasuringPoint(final MeasuringPoint measuringPoint) {
    temperature = new Temperature(measuringPoint.temperature);
    timestamp = measuringPoint.timestamp;
  }

  public Temperature getTemperature() {
    return new Temperature(temperature);
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o instanceof MeasuringPoint that) {
      return Objects.equals(temperature, that.temperature) && Objects.equals(timestamp, that.timestamp);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(temperature, timestamp);
  }

  @Override
  public int compareTo(final MeasuringPoint measuringPoint) {
    if (measuringPoint == null) {
      return 1;
    }
    return temperature.compareTo(measuringPoint.temperature);
  }

  @Override
  public String toString() {
    return "MeasuringPoint{" + "temperature=" + temperature + ", timestamp=" + timestamp + '}';
  }
}
