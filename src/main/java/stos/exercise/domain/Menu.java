package stos.exercise.domain;

import stos.exercise.domain.builders.BreakfastMenuBuilder;
import stos.exercise.generated.BreakfastMenu;

import java.util.List;
import java.util.stream.Collectors;

public class Menu {

  private BreakfastMenu breakfastMenu;

  public Menu(BreakfastMenu breakfastMenu) {
    this.breakfastMenu = breakfastMenu;
  }

  public BreakfastMenu getBreakfastMenu() {
    return breakfastMenu;
  }

  public Menu sort(MenuSortOrder sortOrder) {
    List<BreakfastMenu.Food> sortedFoods = breakfastMenu.getFood().stream()
        .sorted(sortOrder.getSortMethod())
        .collect(Collectors.toList());
    return new Menu(new BreakfastMenuBuilder().food(sortedFoods).build());
  }

  public String toString() {
    StringBuilder displayString = new StringBuilder("==Breakfast Menu==\n");
    breakfastMenu.getFood().stream().forEach(foodItem -> {
      displayString.append("=FoodItem=\n")
          .append("Name: ").append(foodItem.getName()).append("\n")
          .append("Description: ").append(foodItem.getDescription()).append("\n")
          .append("Price: ").append(foodItem.getPrice()).append("\n")
          .append("Calories: ").append(foodItem.getCalories()).append("\n\n");
    });
    return displayString.toString();
  }
}
