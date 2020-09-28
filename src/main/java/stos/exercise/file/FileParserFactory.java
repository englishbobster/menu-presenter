package stos.exercise.file;

public class FileParserFactory {

  //TODO: should this be a static factory? ...will I need to mock this to test further up the stack?
  private FileParserFactory() {
    //hide constructor
  }

  public static FileParser getFileParser(String fileEnding) {
    if ("xml".equals(fileEnding)) {
      return new XmlFileParser();
    }
    if ("json".equals(fileEnding)) {
      return new JsonFileParser();
    }
    throw new UnsupportedOperationException("Files with the given extension are not supported.");
  }
}
