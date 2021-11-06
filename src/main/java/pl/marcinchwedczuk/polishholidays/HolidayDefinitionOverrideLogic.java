package pl.marcinchwedczuk.polishholidays;

@FunctionalInterface
public interface HolidayDefinitionOverrideLogic {
  HolidayDefinitionBuilder apply(HolidayDefinitionBuilder original);
}
