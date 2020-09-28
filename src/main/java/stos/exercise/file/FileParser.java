package stos.exercise.file;

import stos.exercise.file.exceptions.DataExtractionException;
import stos.exercise.generated.BreakfastMenu;

public interface FileParser {
  BreakfastMenu parseMenu(String fileName) throws DataExtractionException;
}
