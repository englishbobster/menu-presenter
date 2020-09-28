package stos.exercise.file;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import stos.exercise.file.exceptions.DataExtractionException;
import stos.exercise.generated.BreakfastMenu;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JsonFileParserTest {

  private static final String PATH_SEPARATOR = System.getProperties().getProperty("file.separator");
  private static final String TEST_MENU_JSON_FILE_NAME = "test-menu.json";
  private static final String TEST_JSON = "{" +
      "\"breakfast_menu\": {\n" +
      "    \"food\": [\n" +
      "      {\n" +
      "        \"name\": \"breakfast\",\n" +
      "        \"price\": \"$1.00\",\n" +
      "        \"description\": \"no time-late for work\",\n" +
      "        \"calories\": \"0\"\n" +
      "      }" +
      "]" +
      "}" +
      "}";

  @TempDir
  static Path tempDir;

  private Path testMenu;
  private JsonFileParser parser;
  private String absoluteFilePath;

  @BeforeEach
  void setUp() {
    testMenu = tempDir.resolve(TEST_MENU_JSON_FILE_NAME);
    absoluteFilePath = tempDir.toString() + PATH_SEPARATOR + TEST_MENU_JSON_FILE_NAME;
    parser = new JsonFileParser();
  }

  @Test
  void should_create_xml_objects_from_json() throws IOException, DataExtractionException {
    Files.write(testMenu, TEST_JSON.getBytes(StandardCharsets.UTF_8));

    BreakfastMenu menu = parser.parseMenu(absoluteFilePath);

    assertThat(menu, is(notNullValue(BreakfastMenu.class)));
    List<BreakfastMenu.Food> food = menu.getFood();
    assertThat(food.size(), is(equalTo(1)));
    assertThat(food.get(0).getName(), is(equalTo("breakfast")));
  }

  @Test
  void should_throw_dataExtractionException_with_corrupt_json() throws IOException {
    Files.write(testMenu, corruptJson());
    assertThrows(DataExtractionException.class,
        () -> parser.parseMenu(absoluteFilePath));
  }

  private byte[] corruptJson() {
    //remove the last tag brace
    byte[] xml = TEST_JSON.getBytes();
    xml[xml.length - 1] = 0;
    return xml;
  }
}
