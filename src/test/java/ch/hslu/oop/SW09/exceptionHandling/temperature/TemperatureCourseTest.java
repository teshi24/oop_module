package ch.hslu.oop.SW09.exceptionHandling.temperature;

import ch.hslu.oop.SW08.final_static_enum_collections.temperature.Temperature;
import ch.hslu.oop.SW08.final_static_enum_collections.temperature.TemperatureCourse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotSame;

class TemperatureCourseTest {

  private TemperatureCourse course;
  private static final Temperature DEFAULT_TEMPERATURE = new Temperature();

  @BeforeEach
  void setUp() {
    course = new TemperatureCourse();
  }

  @Test
  void defaultConstructor_createsAnEmptyCollection() {
    assertThat(course.getTemperatures()).isEmpty();
  }

  @Test
  void addTemperature_null_doesNotAddTemperatureAndReturnsFalse() {
    final boolean returnValue = course.add(null);
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
  void getCount_getTemperatureEmpty_returns0() {
    assertThat(course.getCount()).isZero();
  }

  @Test
  void getCount_getTemperatureFilled_returnsCountOfTemperaturesInTheList() {
    course.add(DEFAULT_TEMPERATURE);
    assertThat(course.getCount()).isEqualTo(1);
  }

  @Test
  void getMax_emptyList_returnsNull() {
    assertThat(course.getMax()).isNull();
  }

  @Test
  void getMax_1Item_returnsCopyOfItem() {
    course.add(DEFAULT_TEMPERATURE);
    final Temperature maxTemperature = course.getMax();
    assertThat(maxTemperature).isEqualTo(DEFAULT_TEMPERATURE);
    assertNotSame(maxTemperature, DEFAULT_TEMPERATURE);
  }

  @Test
  void getMax_multipleItemsFirstBigger_returnsMaxTemperature() {
    course.add(DEFAULT_TEMPERATURE);
    course.add(new Temperature(DEFAULT_TEMPERATURE.getCurrentTemperatureInCelsius() - 1));
    assertThat(course.getMax()).isEqualTo(DEFAULT_TEMPERATURE);
  }

  @Test
  void getMax_multipleItemsOtherThanFirst_returnsMaxTemperature() {
    course.add(new Temperature(DEFAULT_TEMPERATURE.getCurrentTemperatureInCelsius() - 1));
    course.add(DEFAULT_TEMPERATURE);
    assertThat(course.getMax()).isEqualTo(DEFAULT_TEMPERATURE);
  }

  @Test
  void getMin_emptyList_returnsNull() {
    assertThat(course.getMin()).isNull();
  }

  @Test
  void getMin_1Item_returnsCopyOfItem() {
    course.add(DEFAULT_TEMPERATURE);
    final Temperature minTemperature = course.getMin();
    assertThat(minTemperature).isEqualTo(DEFAULT_TEMPERATURE);
    assertNotSame(minTemperature, DEFAULT_TEMPERATURE);
  }

  @Test
  void getMin_multipleItemsFirstBigger_returnsMinTemperature() {
    course.add(DEFAULT_TEMPERATURE);
    course.add(new Temperature(DEFAULT_TEMPERATURE.getCurrentTemperatureInCelsius() + 1));
    assertThat(course.getMin()).isEqualTo(DEFAULT_TEMPERATURE);
  }

  @Test
  void getMin_multipleItemsOtherThanFirst_returnsMinTemperature() {
    course.add(new Temperature(DEFAULT_TEMPERATURE.getCurrentTemperatureInCelsius() + 1));
    course.add(DEFAULT_TEMPERATURE);
    assertThat(course.getMin()).isEqualTo(DEFAULT_TEMPERATURE);
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
    course.add(new Temperature(DEFAULT_TEMPERATURE.getCurrentTemperatureInCelsius() + 2));
    assertThat(course.getAverage()).isEqualTo(new Temperature(DEFAULT_TEMPERATURE.getCurrentTemperatureInCelsius() + 1));
  }
}
