package pl.marcinchwedczuk.polishholidays;

import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static pl.marcinchwedczuk.polishholidays.ArgumentChecks.checkNonEmptyRange;

public class EffectiveTimespan {
  public static EffectiveTimespan atAllTimes() {
    return new EffectiveTimespan(Optional.empty(), Optional.empty());
  }

  public static EffectiveTimespan fromYearOnward(int fromYearIncluding) {
    return new EffectiveTimespan(
        Optional.of(fromYearIncluding),
        Optional.empty());
  }

  public static EffectiveTimespan fromYearsRange(int fromYearIncluding,
      int toYearExcluding) {
    return new EffectiveTimespan(
        Optional.of(fromYearIncluding),
        Optional.of(toYearExcluding));
  }

  private final Optional<Integer> validFromYearIncluding;
  private final Optional<Integer> validToYearExcluding;

  public EffectiveTimespan(Optional<Integer> validFromYearIncluding,
      Optional<Integer> validToYearExcluding) {
    requireNonNull(validFromYearIncluding);
    requireNonNull(validToYearExcluding);

    checkNonEmptyRange(validFromYearIncluding, validToYearExcluding,
        "Empty years range");

    this.validFromYearIncluding = validFromYearIncluding;
    this.validToYearExcluding = validToYearExcluding;
  }

  public boolean includesYear(int year) {
    int fromYear = validFromYearIncluding.orElse(Integer.MIN_VALUE);
    int toYear = validToYearExcluding.orElse(Integer.MAX_VALUE);
    return fromYear <= year && year < toYear;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof EffectiveTimespan)) {
      return false;
    }

    EffectiveTimespan other = (EffectiveTimespan) obj;
    return Objects.equals(this.validFromYearIncluding,
        other.validFromYearIncluding)
        && Objects.equals(this.validToYearExcluding,
            other.validToYearExcluding);
  }

  @Override
  public int hashCode() {
    return Objects.hash(validFromYearIncluding, validToYearExcluding);
  }

  @Override
  public String toString() {
    return String.format("[%s to %s]",
        validFromYearIncluding.map(Object::toString).orElse("-"),
        validToYearExcluding.map(Object::toString).orElse("-"));
  }
}
