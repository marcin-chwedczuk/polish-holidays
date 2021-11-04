package pl.marcinchwedczuk.polishholidays;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HolidayCalendar {
  public static HolidayCalendarBuilder newBuilder() {
    return new HolidayCalendarBuilder(Collections.emptyList());
  }

  public static HolidayCalendarBuilder newBuilderWithPolishHolidaysDefined() {
    List<HolidayDefinition> defaultHolidays =
        new PolishHolidaysLibrary().defaultHolidaysDefinitions();

    return new HolidayCalendarBuilder(defaultHolidays);
  }

  private final List<HolidayDefinition> holidayDefinitions;

  HolidayCalendar(List<HolidayDefinition> holidayDefinitions) {
    this.holidayDefinitions =
        Collections.unmodifiableList(new ArrayList<>(holidayDefinitions));
  }

  public List<Holiday> findHolidaysForYear(int year) {
    return holidayDefinitions.stream()
        .map(holidayDefinition -> holidayDefinition.maybeHolidayForYear(year))
        .flatMap(Optionals::asStream)
        .sorted(Comparator.comparing(Holiday::date))
        .collect(toList());
  }
}
