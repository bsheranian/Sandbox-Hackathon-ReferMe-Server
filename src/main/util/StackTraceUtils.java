package util;

import java.util.ArrayList;
import java.util.List;

public class StackTraceUtils {
  public static List<String> traceToString(StackTraceElement[] elements) {
    List<String> newElements = new ArrayList<>();
    for (StackTraceElement element : elements) {
      newElements.add(element.toString());
    }
    return newElements;
  }
}
