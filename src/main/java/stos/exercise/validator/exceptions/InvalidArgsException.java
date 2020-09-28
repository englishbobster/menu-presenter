package stos.exercise.validator.exceptions;

public class InvalidArgsException extends Exception{

  private String errorMessage;

  public InvalidArgsException(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public String getErrorMessage() {
    return errorMessage;
  }
}
