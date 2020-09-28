package stos.exercise.file;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileParserFactoryTest {

  @Test
  void return_xml_file_parser_when_file_is_xml_format() {
    FileParser parser = FileParserFactory.getFileParser("xml");
    assertThat(parser, instanceOf(XmlFileParser.class));
  }

  @Test
  void return_json_file_parser_when_file_is_json_format() {
    FileParser parser = FileParserFactory.getFileParser("json");
    assertThat(parser, instanceOf(JsonFileParser.class));
  }

  @Test
  void throw_exception_when_unsupported_format_is_given() {
    assertThrows(UnsupportedOperationException.class, () -> FileParserFactory.getFileParser("wtf"));
  }
}
