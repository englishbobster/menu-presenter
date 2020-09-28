package stos.exercise;

import org.apache.commons.io.FilenameUtils;
import stos.exercise.domain.Menu;
import stos.exercise.domain.MenuSortOrder;
import stos.exercise.file.FileParser;
import stos.exercise.file.FileParserFactory;
import stos.exercise.file.exceptions.DataExtractionException;
import stos.exercise.generated.BreakfastMenu;
import stos.exercise.validator.exceptions.InvalidArgsException;
import stos.exercise.validator.Validator;

public class Sorter {
  private static final String usage = "USAGE:\n" +
      "java –jar menu.jar <filename> <sort order> where:\n" +
      "<filename>\t\t - menu file in json or xml format." +
      "<sort order>\t\t - one of asc|desc to sort on name field.";

  private String file;
  private String sortOrder;


  //TODO:meh! some dependency injection is in order and then we could test this class with mocks
  public Sorter(String... args) {
    try {
      new Validator().isValid(args);
    } catch (InvalidArgsException e) {
      exitWithError(e.getErrorMessage());
    }
    this.file = args[0];
    this.sortOrder = args[1];
  }

  public void execute() {
    FileParser fileParser = FileParserFactory.getFileParser(FilenameUtils.getExtension(file));
    try {
      BreakfastMenu breakfastMenu = fileParser.parseMenu(file);
      Menu menu = new Menu(breakfastMenu);
      System.out.println("*******Unsorted*******");
      System.out.println(menu.toString());
      System.out.println("*******Sorted*******");
      System.out.println(menu.sort(MenuSortOrder.of(sortOrder)).toString());

    } catch (DataExtractionException e) {
      exitWithError(e.getMessage());
    }
  }

  private void exitWithError(String message) {
    System.out.println(message);
    printUsage();
    System.exit(1);
  }

  private void printUsage() {
    System.out.println(usage);
  }
}
