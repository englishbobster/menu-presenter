package stos.exercise.domain.builders;

import org.junit.jupiter.api.Test;
import stos.exercise.generated.BreakfastMenu;
import stos.exercise.generated.BreakfastMenu.Food;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class BreakfastBuildersTest {

  @Test
  void should_build_a_breakfast_menu() {
    Food food = new BreakfastFoodBuilder().name("name")
        .price("1337").description("description").calories("3234").build();
    BreakfastMenu menu = new BreakfastMenuBuilder().food(List.of(food)).build();

    List<Food> containedFoods = menu.getFood();
    assertThat(containedFoods.size(),is(equalTo(1)));
    Food containedFood = containedFoods.get(0);
    assertThat(containedFood.getName(), is(equalTo("name")));
    assertThat(containedFood.getPrice(), is(equalTo("1337")));
    assertThat(containedFood.getDescription(), is(equalTo("description")));
    assertThat(containedFood.getCalories(), is(equalTo((short)3234)));

  }
}
