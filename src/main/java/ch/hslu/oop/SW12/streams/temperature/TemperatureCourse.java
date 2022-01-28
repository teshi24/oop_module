package ch.hslu.oop.SW12.streams.temperature;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static ch.hslu.oop.SW12.streams.exceptionhandling.Warnings.varShouldNotBeNull;
import static ch.hslu.oop.SW12.streams.temperature.Temperature.createFromCelsius;

public final class TemperatureCourse {

  private final List<Temperature> temperatures;
  private final List<TemperatureMaxChangeListener> maxChangeListeners;
  private final List<TemperatureMinChangeListener> minChangeListeners;
  private Temperature maxTemperature;
  private Temperature minTemperature;

  public TemperatureCourse() {
    temperatures = new ArrayList<>();
    maxChangeListeners = new ArrayList<>();
    minChangeListeners = new ArrayList<>();
  }

  public List<Temperature> getTemperatures() {
    return new ArrayList<>(temperatures);
  }

  public boolean add(final Temperature temperature) {
    if (temperature == null) {
      return false;
    }
    handleMax(temperature);
    handleMin(temperature);
    return temperatures.add(new Temperature(temperature));
  }

  /**
   * Informs all TemperatureMaxChangeListeners about the TemperatureMaxChangeEvent taken place
   *
   * @param event TemperatureMaxChangeEvent.
   */
  private void fireTemperatureMaxChangeEvent(final TemperatureMaxChangeEvent event) {
    maxChangeListeners.parallelStream().forEach(l -> l.temperatureMaxChange(event));
  }

  /**
   * Registers a listener wo will be informed when
   * {@link TemperatureCourse#fireTemperatureMaxChangeEvent(TemperatureMaxChangeEvent)} is
   * called.
   *
   * @param listener new listener to be registered
   * @throws NullPointerException if listener is null
   */
  public void addTemperatureMaxChangeListener(final TemperatureMaxChangeListener listener) {
    if (listener == null) {
      throw new NullPointerException(varShouldNotBeNull("listener"));
    }
    maxChangeListeners.add(listener);
  }

  /**
   * Unregister listener so that it will no longer be informed when
   * {@link TemperatureCourse#fireTemperatureMaxChangeEvent(TemperatureMaxChangeEvent)} is called.
   *
   * @param listener listener to be unregistered
   */
  public void removeTemperatureMaxChangeListener(final TemperatureMaxChangeListener listener) {
    maxChangeListeners.remove(listener);
  }

  /**
   * Informs all TemperatureMinChangeListeners about the TemperatureMinChangeEvent taken place
   *
   * @param event TemperatureMinChangeEvent.
   */
  private void fireTemperatureMinChangeEvent(final TemperatureMinChangeEvent event) {
    minChangeListeners.parallelStream().forEach(l -> l.temperatureMinChange(event));
  }

  /**
   * Registers a listener wo will be informed when
   * {@link TemperatureCourse#fireTemperatureMinChangeEvent(TemperatureMinChangeEvent)} is
   * called.
   *
   * @param listener new listener to be registered
   * @throws NullPointerException if listener is null
   */
  public void addTemperatureMinChangeListener(final TemperatureMinChangeListener listener) {
    if (listener == null) {
      throw new NullPointerException(varShouldNotBeNull("listener"));
    }
    minChangeListeners.add(listener);
  }

  /**
   * Unregister listener so that it will no longer be informed when
   * {@link TemperatureCourse#fireTemperatureMinChangeEvent(TemperatureMinChangeEvent)} is called.
   *
   * @param listener listener to be unregistered
   */
  public void removeTemperatureMinChangeListener(final TemperatureMinChangeListener listener) {
    minChangeListeners.remove(listener);
  }

  public void clear() {
    temperatures.clear();
    saveNewMax(null);
    saveNewMin(null);
  }

  public int getCount() {
    if (temperatures == null) {
      return 0;
    }
    return temperatures.size();
  }

  private void handleMax(final Temperature temperature) {
    if (temperature == null) {
      return;
    }

    if (temperature.compareTo(maxTemperature) > 0) {
      saveNewMax(temperature);
    }
  }

  private void saveNewMax(final Temperature temperature) {
    if (maxTemperature == temperature) {
      return;
    }
    if (temperature == null) {
      maxTemperature = null;
    } else {
      maxTemperature = new Temperature(temperature);
    }
    fireTemperatureMaxChangeEvent(new TemperatureMaxChangeEvent(this, minTemperature));
  }

  public Temperature getMaxTemperature() {
    if (maxTemperature == null) {
      return null;
    }
    return new Temperature(maxTemperature);
  }

  private void handleMin(final Temperature temperature) {
    if (temperature == null) {
      return;
    }
    if (minTemperature == null) {
      saveNewMin(temperature);
      return;
    }

    if (temperature.compareTo(minTemperature) < 0) {
      saveNewMin(temperature);
    }
  }

  private void saveNewMin(final Temperature temperature) {
    if (temperature == minTemperature) {
      return;
    }
    if (temperature == null) {
      minTemperature = null;
    } else {
      minTemperature = new Temperature(temperature);
    }
    fireTemperatureMinChangeEvent(new TemperatureMinChangeEvent(this, minTemperature));
  }

  public Temperature getMinTemperature() {
    if (minTemperature == null) {
      return null;
    }
    return new Temperature(minTemperature);
  }

  public Temperature getAverage() {
    if (temperatures.isEmpty()) {
      return null;
    }

    final double averageTemperatureInCelsius =
      temperatures.stream().collect(Collectors.averagingDouble(Temperature::getCurrentTemperatureInCelsius));

    return createFromCelsius(averageTemperatureInCelsius);
  }

  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }
    if (object instanceof TemperatureCourse that) {
      return getCount() == that.getCount() && //
             Objects.equals(maxTemperature, that.maxTemperature) && //
             Objects.equals(minTemperature, that.minTemperature) && //
             Objects.equals(temperatures, that.temperatures);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(temperatures, maxTemperature, minTemperature);
  }

  @Override
  public String toString() {
    return "TemperatureCourse{" + "temperatures=" + temperatures + ", maxTemperature=" + maxTemperature + ", " +
           "minTemperature=" + minTemperature + ", maxChangeListeners=" + maxChangeListeners + ", " +
           "minChangeListeners=" + minChangeListeners + '}';
  }
}
