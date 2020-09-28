package stos.exercise.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stos.exercise.domain.builders.BreakfastFoodBuilder;
import stos.exercise.domain.builders.BreakfastMenuBuilder;
import stos.exercise.generated.BreakfastMenu;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static stos.exercise.generated.BreakfastMenu.Food;

class MenuTest {

  private Food foodA;
  private Food foodB;
  private Food foodC;
  private Food foodD;
  private BreakfastMenu breakfastMenu;
  public static final String EXPECTED_DISPLAY_STRING_FOR_FOODC =
      "==Breakfast Menu==\n" +
      "=FoodItem=\n" +
      "Name: C\n" +
      "Description: C\n" +
      "Price: $3.00\n" +
      "Calories: 300\n\n";

  @BeforeEach
  void setUp() {
    foodA = new BreakfastFoodBuilder().name("A").description("A").price("$1.00").calories("100").build();
    foodB = new BreakfastFoodBuilder().name("B").description("B").price("$2.00").calories("200").build();
    foodC = new BreakfastFoodBuilder().name("C").description("C").price("$3.00").calories("300").build();
    foodD = new BreakfastFoodBuilder().name("D").description("D").price("$4.00").calories("400").build();

  }

  @Test
  void sort_a_BreakfastMenu() {
    breakfastMenu = new BreakfastMenuBuilder().food(List.of(foodC, foodB, foodA, foodD)).build();
    Menu menu = new Menu(breakfastMenu);
    Menu sortedMenu = menu.sort(MenuSortOrder.ASCENDING);
    assertThat(sortedMenu.getBreakfastMenu().getFood(), contains(foodA, foodB, foodC, foodD));

    sortedMenu = menu.sort(MenuSortOrder.DESCENDING);
    assertThat(sortedMenu.getBreakfastMenu().getFood(), contains(foodD, foodC, foodB, foodA));
  }

  @Test
  void display_a_breakfastMenu() {
    breakfastMenu = new BreakfastMenuBuilder().food(List.of(foodC)).build();
    Menu menu = new Menu(breakfastMenu);
    assertThat(menu.toString(), is(equalTo(EXPECTED_DISPLAY_STRING_FOR_FOODC)));
  }
}
