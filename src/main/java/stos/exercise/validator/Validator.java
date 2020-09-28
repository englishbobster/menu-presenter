package stos.exercise.validator;

import org.apache.commons.io.FilenameUtils;
import stos.exercise.domain.MenuSortOrder;
import stos.exercise.validator.exceptions.InvalidArgsException;

import java.io.File;
import java.util.Arrays;

public class Validator {

  public static final int NR_OF_EXPECTED_ARGS = 2;

  public boolean isValid(String... args) throws InvalidArgsException {
    boolean result = checkArgsSize(args);
    result &= checkFileExists(new File(args[0]));
    result &= checkFileExtension(new File(args[0]));
    result &= checkSortOrder(args[1]);
    return result;
  }

  private boolean checkArgsSize(String... args) throws InvalidArgsException {
    if (args.length < 2) {
      System.out.println("Args given: ");
      Arrays.stream(args).forEach(System.out::println);
      throw new InvalidArgsException("Too few commandline arguments.");
    }
    if(args.length > NR_OF_EXPECTED_ARGS){
      System.out.println("Args given: ");
      Arrays.stream(args).forEach(System.out::println);
      throw new InvalidArgsException("Too many commandline arguments.");
    }
    return true;
  }

  private boolean checkFileExists(File file) throws InvalidArgsException {
    if (!fileExists(file)) {
      throw new InvalidArgsException("No menu file found.");
    }
    return true;
  }

  private boolean checkFileExtension(File file) throws InvalidArgsException {
    String extension = FilenameUtils.getExtension(file.getName());
    if (extension.isBlank()) {
      throw new InvalidArgsException("Cannot determine the file extension.");
    }
    if (!fileTypeSupported(extension)) {
      throw new InvalidArgsException("File type is not supported.");
    }
    return true;
  }

  private boolean checkSortOrder(String arg) throws InvalidArgsException {
    if (!oneOfAscOrDesc(arg)) {
      throw new InvalidArgsException("Sort order not recognized.");
    }
    return true;
  }

  private boolean oneOfAscOrDesc(String order) {
    return MenuSortOrder.ASCENDING.getShortForm().equals(order) || MenuSortOrder.DESCENDING.getShortForm().equals(order);
  }

  private boolean fileTypeSupported(String extension) {
    return "json".equalsIgnoreCase(extension) || "xml".equalsIgnoreCase(extension);
  }

  private boolean fileExists(File file) {
    return file.exists() && !file.isDirectory();
  }

}
