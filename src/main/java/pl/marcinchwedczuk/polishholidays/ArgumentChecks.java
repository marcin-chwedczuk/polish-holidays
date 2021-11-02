package pl.marcinchwedczuk.polishholidays;

import java.util.Optional;

class ArgumentChecks {
  private ArgumentChecks() {}

  public static void checkNonEmptyRange(
      Optional<Integer> fromIncluding, Optional<Integer> toExcluding, String message) {
    if (fromIncluding.isPresent()
        && toExcluding.isPresent()
        && fromIncluding.get() >= toExcluding.get()) {
      throw new IllegalArgumentException(message);
    }
  }

  public static void checkNonBlankString(String arg, String message) {
    if (arg == null || arg.matches("^\\s*$")) {
      throw new IllegalArgumentException(message);
    }
  }

  public static void checkInRange(int n, int minEq, int maxEq, String message) {
    if (n < minEq || n > maxEq) {
      throw new IllegalArgumentException(message);
    }
  }
}
