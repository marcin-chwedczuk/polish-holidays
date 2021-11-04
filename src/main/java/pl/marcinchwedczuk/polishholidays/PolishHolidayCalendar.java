package pl.marcinchwedczuk.polishholidays;

import static java.util.stream.Collectors.toList;

import java.util.List;

class PolishHolidayCalendar {
  private final List<PolishHolidayDefinition> holidayDefinitions;

  PolishHolidayCalendar(List<PolishHolidayDefinition> holidayDefinitions) {
    this.holidayDefinitions = holidayDefinitions;
  }

  public List<PolishHoliday> findHolidaysForYear(int year) {
    return holidayDefinitions.stream()
        .map(holidayDefinition -> holidayDefinition.maybeHolidayForYear(year))
        .flatMap(Optionals::asStream)
        .collect(toList());
  }
}
