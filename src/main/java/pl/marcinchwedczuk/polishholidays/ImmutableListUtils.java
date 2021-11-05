package pl.marcinchwedczuk.polishholidays;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

class ImmutableListUtils {
  public static <T> List<T> concat(List<T> a, List<T> b) {
    if (a.isEmpty())
      return b;
    if (b.isEmpty())
      return a;

    List<T> result = new ArrayList<>(a.size() + b.size());
    result.addAll(a);
    result.addAll(b);
    return result;
  }

  public static <T> List<T> add(List<T> list, T elem) {
    List<T> result = new ArrayList<>(list.size() + 1);
    result.addAll(list);
    result.add(elem);
    return result;
  }

  public static <T> List<T> removeIf(List<T> list, Predicate<T> predicate) {
    List<T> copy = new ArrayList<>(list);
    copy.removeIf(predicate);
    return copy;
  }
}
