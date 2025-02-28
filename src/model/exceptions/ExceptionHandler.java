package model.exceptions;

import java.util.InputMismatchException;
import model.debug.Config;

public class ExceptionHandler {
  static final boolean DEBUG_MODE = Config.DEBUG_MODE;

  /**
   * Handles exceptions by showing a different error message based on the
   * exception.
   * 
   * @param e
   */
  public static void consoleHandle(Exception e) {
    StackTraceElement[] stackTrace = e.getStackTrace();
    StackTraceElement fileStackElement = null;
    if (DEBUG_MODE)
      System.out.println("Stack trace:");
    for (StackTraceElement element : stackTrace) {
      if (DEBUG_MODE)
        System.out.println("  at " + element);
      if (element.getClassName().startsWith("view.") || element.getClassName().startsWith("model.")) {
        fileStackElement = element;
      }
    }
    if (fileStackElement == null) {
      fileStackElement = stackTrace[0];
    }
    if (e instanceof InterruptedException) {
      System.out.println(
          "\u001B[31mClass " + fileStackElement.getFileName() + " was interrupted (possibly by user)!\u001B[0m");
    } else if (e instanceof IllegalArgumentException || e instanceof InputMismatchException) {
      System.out
          .println("\u001B[31mClass " + fileStackElement.getFileName() + " recieved an illegal argument!\u001B[0m");
    } else if (e instanceof NoResultsFound) {
      System.out
      .println("\u001B[31m!Error! No Resuts found on your search! (" + e.getMessage() + ")\u001B[0m");
    } else if (e instanceof NullPointerException) {
      System.out
      .println("\u001B[31m!Error! User tried to access an uninitialized object! (" + e.getMessage() + ")\u001B[0m");
    }
    else {
      System.out.println(
          "\u001B[31mClass " + fileStackElement.getClassName() + " experienced an unexpected error. Error cause: "
              + e.getLocalizedMessage() + "(" + e.getClass() + ")\u001B[0m");
    }
  }
}
