package stos.exercise.domain.builders;

import stos.exercise.generated.BreakfastMenu;

import java.util.List;

public class BreakfastMenuBuilder {

  private BreakfastMenu menu;

  public BreakfastMenuBuilder() {
    menu = new BreakfastMenu();
  }

  public BreakfastMenuBuilder food(List<BreakfastMenu.Food> foods) {
    List<BreakfastMenu.Food> food = menu.getFood();
    food.addAll(foods);
    return this;
  }

  public BreakfastMenu build() {
    return menu;
  }
}
