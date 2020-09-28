package stos.exercise.domain.builders;

import stos.exercise.generated.BreakfastMenu;

public class BreakfastFoodBuilder extends BreakfastMenu.Food {

  BreakfastMenu.Food breakfastFood;

  public BreakfastFoodBuilder() {
    breakfastFood = new BreakfastMenu.Food();
  }

  public BreakfastFoodBuilder name(String name) {
    breakfastFood.setName(name);
    return this;
  }

  public BreakfastFoodBuilder price(String price) {
    breakfastFood.setPrice(price);
    return this;
  }

  public BreakfastFoodBuilder description(String description) {
    breakfastFood.setDescription(description);
    return this;
  }

  public BreakfastFoodBuilder calories(String calories) {
    breakfastFood.setCalories(Short.parseShort(calories));
    return this;
  }

  public BreakfastMenu.Food build() {
    return this.breakfastFood;
  }

}
