package pl.marcinchwedczuk.polishholidays;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Stream;

class Optionals {
  private Optionals() {}

  public static <T> Stream<T> asStream(Optional<T> optional) {
    return optional
        .map(Collections::singletonList)
        .orElse(Collections.emptyList())
        .stream();
  }
}
