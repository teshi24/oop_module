package ch.hslu.oop.SW12.streams.temperature;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class TemperatureCourseTest {

  private TemperatureCourse course;
  private static final Temperature DEFAULT_TEMPERATURE = Temperature.createFromCelsius(20);

  @BeforeEach
  void setUp() {
    course = new TemperatureCourse();
  }

  @Test
  void defaultConstructor_createsAnEmptyCollection() {
    assertThat(course.getTemperatures()).isEmpty();
  }

  @Test
  void addTemperature_nullTemperature_doesNotAddTemperatureAndReturnsFalse() {
    final Temperature temperature = null;
    final boolean returnValue = course.add(temperature);
    assertThat(course.getTemperatures()).isEmpty();
    assertThat(returnValue).isFalse();
  }

  @Test
  void addTemperature_defaultTemperature_addsTemperatureToPropertyTemperaturesAndReturnsTrue() {
    final boolean returnValue = course.add(DEFAULT_TEMPERATURE);
    assertThat(course.getTemperatures().size()).isEqualTo(1);
    assertThat(returnValue).isTrue();
  }

  @Test
  void addTemperature_defaultTemperature_copyOffGivenTemperatureAddedToList() {
    course.add(DEFAULT_TEMPERATURE);
    final Temperature firstTemperatureInList = course.getTemperatures().get(0);
    assertThat(firstTemperatureInList == DEFAULT_TEMPERATURE).isFalse();
  }

  @Test
  void clear_emptyList_setsTemperaturesToEmptyList() {
    course.clear();
    assertThat(course.getTemperatures()).isEmpty();
  }

  @Test
  void clear_filledList_setsTemperaturesToEmptyList() {
    course.add(DEFAULT_TEMPERATURE);
    course.clear();
    assertThat(course.getTemperatures()).isEmpty();
  }

  @Test
  void clear_emptyList_maxTemperaturesIsNullNoEventSent() {
    final TemperatureCourse courseSpy = spy(TemperatureCourse.class);
    final TemperatureMaxChangeListener maxChangeListener = mock(TemperatureMaxChangeListener.class);
    courseSpy.addTemperatureMaxChangeListener(maxChangeListener);

    courseSpy.clear();
    assertThat(courseSpy.getMaxTemperature()).isNull();
    verify(maxChangeListener, never()).temperatureMaxChange(any());
  }

  @Test
  void clear_emptyList_minTemperaturesIsNullNoEventSent() {
    final TemperatureCourse courseSpy = spy(TemperatureCourse.class);
    final TemperatureMinChangeListener minChangeListener = mock(TemperatureMinChangeListener.class);
    courseSpy.addTemperatureMinChangeListener(minChangeListener);

    courseSpy.clear();
    assertThat(courseSpy.getMinTemperature()).isNull();
    verify(minChangeListener, never()).temperatureMinChange(any());
  }

  @Test
  void clear_filledList_setsMaxTemperaturesToNullAndSendsUpdateEvent() {
    final TemperatureCourse courseSpy = spy(TemperatureCourse.class);
    courseSpy.add(DEFAULT_TEMPERATURE);

    final TemperatureMaxChangeListener maxChangeListener = mock(TemperatureMaxChangeListener.class);
    courseSpy.addTemperatureMaxChangeListener(maxChangeListener);

    courseSpy.clear();
    assertThat(courseSpy.getMaxTemperature()).isNull();
    verify(maxChangeListener, times(1)).temperatureMaxChange(any());
  }

  @Test
  void clear_filledList_setsMinTemperaturesToNullAndSendsUpdateEvent() {
    final TemperatureCourse courseSpy = spy(TemperatureCourse.class);
    courseSpy.add(DEFAULT_TEMPERATURE);

    final TemperatureMinChangeListener minChangeListener = mock(TemperatureMinChangeListener.class);
    courseSpy.addTemperatureMinChangeListener(minChangeListener);

    courseSpy.clear();
    assertThat(courseSpy.getMinTemperature()).isNull();
    verify(minChangeListener, times(1)).temperatureMinChange(any());
  }

  @Test
  void getCount_getTemperatureEmpty_returns0() {
    assertThat(course.getCount()).isZero();
  }

  @Test
  void getCount_getTemperatureFilled_returnsCountOfTemperaturesInTheList() {
    course.add(DEFAULT_TEMPERATURE);
    assertThat(course.getCount()).isEqualTo(1);
  }

  @Test
  void saveMax_addNullAndMaxTemperatureNull_maxTemperatureStillNull() {
    assertThat(course.getMaxTemperature()).isNull();
    course.add(null);
    assertThat(course.getMaxTemperature()).isNull();
  }

  @Test
  void saveMax_addNotNullAndMaxTemperatureNull_maxTemperatureBecomesGivenTemperature() {
    assertThat(course.getMaxTemperature()).isNull();
    final Temperature maxTemp = Temperature.createFromCelsius(10);
    course.add(maxTemp);
    assertThat(course.getMaxTemperature()).isEqualTo(maxTemp);
  }

  @Test
  void saveMax_addNullAndMaxTemperatureNotNull_maxTemperatureDidNotChange() {
    final Temperature maxTemp = Temperature.createFromCelsius(10);
    course.add(maxTemp);
    assertThat(course.getMaxTemperature()).isEqualTo(maxTemp);
    course.add(null);
    assertThat(course.getMaxTemperature()).isEqualTo(maxTemp);
  }

  @Test
  void saveMax_multipleItemsOtherThanFirst_returnsMaxTemperature() {
    course.add(Temperature.createFromCelsius(DEFAULT_TEMPERATURE.getCurrentTemperatureInCelsius() - 1));
    course.add(DEFAULT_TEMPERATURE);
    assertThat(course.getMaxTemperature()).isEqualTo(DEFAULT_TEMPERATURE);
  }

  @Test
  void getMax_emptyList_returnsNull() {
    assertThat(course.getMaxTemperature()).isNull();
  }

  @Test
  void getMax_1Item_returnsCopyOfItem() {
    course.add(DEFAULT_TEMPERATURE);
    final Temperature maxTemperature = course.getMaxTemperature();
    assertThat(maxTemperature).isEqualTo(DEFAULT_TEMPERATURE);
    assertNotSame(maxTemperature, DEFAULT_TEMPERATURE);
  }

  @Test
  void getMax_multipleItemsFirstBigger_returnsMaxTemperature() {
    course.add(DEFAULT_TEMPERATURE);
    course.add(Temperature.createFromCelsius(DEFAULT_TEMPERATURE.getCurrentTemperatureInCelsius() - 1));
    assertThat(course.getMaxTemperature()).isEqualTo(DEFAULT_TEMPERATURE);
  }

  @Test
  void getMax_multipleItemsOtherThanFirst_returnsMaxTemperature() {
    course.add(Temperature.createFromCelsius(DEFAULT_TEMPERATURE.getCurrentTemperatureInCelsius() - 1));
    course.add(DEFAULT_TEMPERATURE);
    assertThat(course.getMaxTemperature()).isEqualTo(DEFAULT_TEMPERATURE);
  }

  @Test
  void saveMin_addNullAndMinTemperatureNull_minTemperatureStillNull() {
    assertThat(course.getMinTemperature()).isNull();
    course.add(null);
    assertThat(course.getMinTemperature()).isNull();
  }

  @Test
  void saveMin_addNotNullAndMinTemperatureNull_minTemperatureBecomesGivenTemperature() {
    assertThat(course.getMinTemperature()).isNull();
    final Temperature minTemp = Temperature.createFromCelsius(10);
    course.add(minTemp);
    assertThat(course.getMinTemperature()).isEqualTo(minTemp);
  }

  @Test
  void saveMin_addNullAndMinTemperatureNotNull_minTemperatureDidNotChange() {
    final Temperature minTemp = Temperature.createFromCelsius(10);
    course.add(minTemp);
    assertThat(course.getMinTemperature()).isEqualTo(minTemp);
    course.add(null);
    assertThat(course.getMinTemperature()).isEqualTo(minTemp);
  }

  @Test
  void saveMin_multipleItemsOtherThanFirst_returnsMinTemperature() {
    course.add(Temperature.createFromCelsius(DEFAULT_TEMPERATURE.getCurrentTemperatureInCelsius() + 1));
    course.add(DEFAULT_TEMPERATURE);
    assertThat(course.getMinTemperature()).isEqualTo(DEFAULT_TEMPERATURE);
  }

  @Test
  void getMin_emptyList_returnsNull() {
    assertThat(course.getMinTemperature()).isNull();
  }

  @Test
  void getMin_1Item_returnsCopyOfItem() {
    course.add(DEFAULT_TEMPERATURE);
    final Temperature minTemperature = course.getMinTemperature();
    assertThat(minTemperature).isEqualTo(DEFAULT_TEMPERATURE);
    assertNotSame(minTemperature, DEFAULT_TEMPERATURE);
  }

  @Test
  void getMin_multipleItemsFirstBigger_returnsMinTemperature() {
    course.add(DEFAULT_TEMPERATURE);
    course.add(Temperature.createFromCelsius(DEFAULT_TEMPERATURE.getCurrentTemperatureInCelsius() + 1));
    assertThat(course.getMinTemperature()).isEqualTo(DEFAULT_TEMPERATURE);
  }

  @Test
  void getMin_multipleItemsOtherThanFirst_returnsMinTemperature() {
    course.add(Temperature.createFromCelsius(DEFAULT_TEMPERATURE.getCurrentTemperatureInCelsius() + 1));
    course.add(DEFAULT_TEMPERATURE);
    assertThat(course.getMinTemperature()).isEqualTo(DEFAULT_TEMPERATURE);
  }

  @Test
  void getAverage_emptyList_returnsNull() {
    assertThat(course.getAverage()).isNull();
  }

  @Test
  void getAverage_1Item_returnsItem() {
    course.add(DEFAULT_TEMPERATURE);
    assertThat(course.getAverage()).isEqualTo(DEFAULT_TEMPERATURE);
  }

  @Test
  void getAverage_multipleItems_returnsAverage() {
    course.add(DEFAULT_TEMPERATURE);
    course.add(Temperature.createFromCelsius(DEFAULT_TEMPERATURE.getCurrentTemperatureInCelsius() + 2));
    assertThat(course.getAverage()).isEqualTo(Temperature.createFromCelsius(DEFAULT_TEMPERATURE.getCurrentTemperatureInCelsius() + 1));
  }

  /**
   * Test {@link TemperatureCourse#equals(Object)} Contract.
   */
  @Test
  void testEqualsContract() {
    EqualsVerifier.forClass(TemperatureCourse.class).suppress(Warning.NONFINAL_FIELDS).withIgnoredFields(
      "maxChangeListeners", "minChangeListeners").verify();
  }
}
