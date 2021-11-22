/*
 * Copyright 2021 Roland Gisler, HSLU Informatik, Switzerland
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.hslu.oop.SW09.exceptionHandling.temperature;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Demo-Applikation für {@link Temperature}-Klasse.
 */
public final class DemoApp {

  private static final Logger LOGGER = LogManager.getLogger(DemoApp.class);

  private DemoApp() {
  }

  public static void main(final String[] args) {
    final Temperature temperature = new Temperature();
    final ElementOfPeriodSystem lead = ElementOfPeriodSystem.Pb;
    final StateOfAggregation currentStateOfAggregationForElement =
      temperature.getCurrentStateOfAggregationForElement(lead);
    LOGGER.info("{} is {} at {}°C.", //
                lead.name(), //
                currentStateOfAggregationForElement, //
                temperature.getCurrentTemperatureInCelsius());
  }
}
