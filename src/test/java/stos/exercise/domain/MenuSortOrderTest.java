package stos.exercise.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class MenuSortOrderTest {

  @Test
  void should_get_value_from_shortform() {
    assertThat(MenuSortOrder.of("asc"), is(equalTo(MenuSortOrder.ASCENDING)));
    assertThat(MenuSortOrder.of("desc"), is(equalTo(MenuSortOrder.DESCENDING)));
    assertThat(MenuSortOrder.of("noop"), is(equalTo(MenuSortOrder.NO_OP)));
  }

  @Test
  void should_give_noop_if_we_ever_get_there() {
    assertThat(MenuSortOrder.of("absoluterubbish"), is(equalTo(MenuSortOrder.NO_OP)));
  }
}
