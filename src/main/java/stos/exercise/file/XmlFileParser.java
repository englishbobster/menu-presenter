package stos.exercise.file;

import stos.exercise.file.exceptions.DataExtractionException;
import stos.exercise.generated.BreakfastMenu;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XmlFileParser implements FileParser {

  private JAXBContext context;

  public XmlFileParser() {
    //do context config here and throw runtime exception?
  }

  public BreakfastMenu parseMenu(String fileName) throws DataExtractionException {
    File xmlFile = new File(fileName);
    try
    {
      context = JAXBContext.newInstance(BreakfastMenu.class);
      Unmarshaller unmarshaller = context.createUnmarshaller();
      return (BreakfastMenu)unmarshaller.unmarshal(xmlFile);
     }
    catch (JAXBException e)
    {
      throw new DataExtractionException(e.getMessage(), e);
    }
  }
}
