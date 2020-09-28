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

class XmlFileParserTest {

  private static final String PATH_SEPARATOR = System.getProperties().getProperty("file.separator");
  public static final String TEST_MENU_XML_FILE_NAME = "test-menu.xml";
  private static final String TEST_XML = "<breakfast_menu>" +
      "\t<food>\n" +
      "\t\t<name>breakfast</name>\n" +
      "\t\t<price>$1.00</price>\n" +
      "\t\t<description>weetabix</description>\n" +
      "\t\t<calories>120</calories>\n" +
      "\t</food>\n" +
      "</breakfast_menu>";

  @TempDir
  static Path tempDir;

  private Path testMenu;
  private XmlFileParser parser;
  private String absoluteFilePath;

  @BeforeEach
  void setUp() {
    testMenu = tempDir.resolve(TEST_MENU_XML_FILE_NAME);
    parser = new XmlFileParser();
    absoluteFilePath = tempDir.toString() + PATH_SEPARATOR + TEST_MENU_XML_FILE_NAME;
  }

  @Test
  void should_create_xml_objects() throws IOException, DataExtractionException {
    Files.write(testMenu, TEST_XML.getBytes(StandardCharsets.UTF_8));

    BreakfastMenu menu = parser.parseMenu(absoluteFilePath);

    assertThat(menu, is(notNullValue(BreakfastMenu.class)));
    List<BreakfastMenu.Food> food = menu.getFood();
    assertThat(food.size(), is(equalTo(1)));
    assertThat(food.get(0).getName(), is(equalTo("breakfast")));
  }

  @Test
  void should_throw_dataExtractionException_when_corrupt_xml() throws IOException {
    Files.write(testMenu, corruptXml());

    assertThrows(DataExtractionException.class,
        () -> parser.parseMenu(absoluteFilePath));
  }

  private byte[] corruptXml() {
    //remove the last tag brace
    byte[] xml = TEST_XML.getBytes();
    xml[xml.length - 1] = 0;
    return xml;
  }
}
