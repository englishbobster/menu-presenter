package stos.exercise.file;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import stos.exercise.file.exceptions.DataExtractionException;
import stos.exercise.generated.BreakfastMenu;

import java.io.File;
import java.io.IOException;

public class JsonFileParser implements FileParser{

  private ObjectMapper mapper;

  public JsonFileParser() {
    this.mapper = new ObjectMapper();
    mapper.registerModule(new JaxbAnnotationModule());
    mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
  }

  @Override
  public BreakfastMenu parseMenu(String fileName) throws DataExtractionException {
    try {
      return mapper.readValue(new File(fileName), BreakfastMenu.class);
    } catch (IOException e) {
      throw new DataExtractionException(e.getMessage(), e);
    }
  }
}
