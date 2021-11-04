package pl.marcinchwedczuk.polishholidays.testutils;

import java.time.LocalDate;
import java.util.Objects;

import org.assertj.core.api.AbstractAssert;

import pl.marcinchwedczuk.polishholidays.Holiday;
import pl.marcinchwedczuk.polishholidays.HolidayType;

public class PolishHolidayAssert
    extends AbstractAssert<PolishHolidayAssert, Holiday> {
  public static PolishHolidayAssert assertThat(Holiday actual) {
    return new PolishHolidayAssert(actual);
  }

  public PolishHolidayAssert(Holiday actual) {
    super(actual, PolishHolidayAssert.class);
  }

  public PolishHolidayAssert hasDate(LocalDate expected) {
    if (!Objects.equals(expected, actual.date())) {
      failWithMessage(
          "Expected holiday to have date %s but was %s",
          expected,
          actual.date());
    }
    return this;
  }

  public PolishHolidayAssert hasEnglishName(String expected) {
    if (!Objects.equals(expected, actual.englishName())) {
      failWithMessage(
          "Expected holiday to have english name '%s' but was '%s'",
          expected,
          actual.englishName());
    }
    return this;
  }

  public PolishHolidayAssert hasPolishName(String expected) {
    if (!Objects.equals(expected, actual.polishName())) {
      failWithMessage(
          "Expected holiday to have polish name '%s' but was '%s'",
          expected,
          actual.polishName());
    }
    return this;
  }

  public PolishHolidayAssert hasType(HolidayType expected) {
    if (!Objects.equals(expected, actual.type())) {
      failWithMessage("Expected holiday to have type %s but was %s", expected,
          actual.type());
    }
    return this;
  }

  public PolishHolidayAssert isPublicHoliday() {
    return isPublicHoliday(true);
  }

  public PolishHolidayAssert isNotPublicHoliday() {
    return isPublicHoliday(false);
  }

  private PolishHolidayAssert isPublicHoliday(boolean expected) {
    if (!Objects.equals(expected, actual.isPublicHoliday())) {
      failWithMessage(
          expected ? "Expected holiday to be public holiday but was not"
              : "Expected holiday to NOT be public holiday but it was");
    }
    return this;
  }
}
