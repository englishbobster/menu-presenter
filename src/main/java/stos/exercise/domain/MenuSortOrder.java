package stos.exercise.domain;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import static stos.exercise.generated.BreakfastMenu.*;

public enum MenuSortOrder {
  //TODO: sort on other fields? move the comparator generation to a comparator generator method or something?
  ASCENDING("asc",Comparator.comparing(Food::getName)),
  DESCENDING("desc", Comparator.comparing(Food::getName).reversed()),
  NO_OP("noop", (f1, f2) -> 0);

  private String shortForm;
  private Comparator<Food> sortMethod;

  private static Map<String, MenuSortOrder> name2Order = new HashMap(values().length);
  static{
    Arrays.stream(values()).forEach(v -> name2Order.put(v.getShortForm(), v));
  }

  MenuSortOrder(String shortForm, Comparator<Food> sortMethod) {
    this.shortForm = shortForm;
    this.sortMethod = sortMethod;
  }

  public String getShortForm() {
    return shortForm;
  }

  public Comparator<Food> getSortMethod() {
    return sortMethod;
  }

  public static MenuSortOrder of(String sortOrder) {
    MenuSortOrder menuSortOrder = name2Order.get(sortOrder);
    return menuSortOrder == null ? NO_OP : menuSortOrder;
  }
}
