package stos.exercise.validator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import stos.exercise.validator.exceptions.InvalidArgsException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidatorTest {

  private static final String PATH_SEPARATOR = System.getProperties().getProperty("file.separator");

  @TempDir
  static Path tempDir;

  private static Path xmlFile;
  private static Path jsonFile;
  private static Path noFileExt;


  @BeforeAll
  static void configure() throws IOException {
    xmlFile = tempDir.resolve("test.xml");
    Files.write(xmlFile, "testfile".getBytes());
    jsonFile = tempDir.resolve("test.json");
    Files.write(jsonFile, "testfile".getBytes());
    noFileExt = tempDir.resolve("noFileExt");
    Files.write(noFileExt, "testfile".getBytes());
  }

  private static Stream<Arguments> correctArgs() {
    return Stream.of(
        Arguments.of((Object) new String[]{"test.xml", "asc"}),
        Arguments.of((Object) new String[]{"test.xml", "desc"}),
        Arguments.of((Object) new String[]{"test.json", "asc"}),
        Arguments.of((Object) new String[]{"test.json", "desc"})
    );
  }

  @ParameterizedTest
  @MethodSource("correctArgs")
  void should_validate_correct_arguments(String[] args) throws InvalidArgsException {
    String filePath = tempDir.toString() + PATH_SEPARATOR + args[0];
    Validator validator = new Validator();
    assertThat(validator.isValid(filePath, args[1]), is(true));
  }

  @Test
  void should_throw_when_too_many_args() {
    Validator validator = new Validator();
    InvalidArgsException invalidArgsException =
        assertThrows(InvalidArgsException.class, () -> validator.isValid("1", "2", "3"));
    assertThat(invalidArgsException.getErrorMessage(), is(equalTo("Too many commandline arguments.")));
  }

  @Test
  void should_throw_when_too_few_args() {
    Validator validator = new Validator();
    InvalidArgsException invalidArgsException =
        assertThrows(InvalidArgsException.class, () -> validator.isValid("1"));
    assertThat(invalidArgsException.getErrorMessage(), is(equalTo("Too few commandline arguments.")));
  }

  @Test
  void should_throw_when_no_file_found() {
    Validator validator = new Validator();
    InvalidArgsException invalidArgsException =
        assertThrows(InvalidArgsException.class, () -> validator.isValid("somefile.xml", "asc"));
    assertThat(invalidArgsException.getErrorMessage(), is(equalTo("No menu file found.")));
  }


  @Test
  void should_throw_when_no_file_extension() {
    String filePath = tempDir.toString() + PATH_SEPARATOR + "noFileExt";
    Validator validator = new Validator();
    InvalidArgsException invalidArgsException =
        assertThrows(InvalidArgsException.class, () -> validator.isValid(filePath, "asc"));
    assertThat(invalidArgsException.getErrorMessage(), is(equalTo("Cannot determine the file extension.")));
  }

  @Test
  void should_throw_when_no_valid_sort_order() {
    String filePath = tempDir.toString() + PATH_SEPARATOR + "test.json";
    Validator validator = new Validator();
    InvalidArgsException invalidArgsException =
        assertThrows(InvalidArgsException.class, () -> validator.isValid(filePath, "up"));
    assertThat(invalidArgsException.getErrorMessage(), is(equalTo("Sort order not recognized.")));
  }
}
