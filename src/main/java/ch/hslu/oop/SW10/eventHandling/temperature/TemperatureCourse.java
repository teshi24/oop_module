package ch.hslu.oop.SW10.eventHandling.temperature;

import java.util.ArrayList;
import java.util.List;

import static ch.hslu.oop.SW10.eventHandling.temperature.Temperature.createFromCelsius;
import static ch.hslu.oop.SW10.exceptionhandling.Warnings.varShouldNotBeNull;

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
    for (final TemperatureMaxChangeListener listener : maxChangeListeners) {
      listener.temperatureMaxChange(event);
    }
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
    for (final TemperatureMinChangeListener listener : minChangeListeners) {
      listener.temperatureMinChange(event);
    }
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
    double temperatureSum = 0;
    for (final Temperature temperature : temperatures) {
      temperatureSum += temperature.getCurrentTemperatureInCelsius();
    }
    final double averageTemperatureInCelsius = temperatureSum / getCount();

    return createFromCelsius(averageTemperatureInCelsius);
  }
}
