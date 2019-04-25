package cn.word2pdf.exceptions;

public class FileExtentionForbiddenException extends Exception{

  public FileExtentionForbiddenException() {
    super();
  }

  public FileExtentionForbiddenException(String message) {
    super(message);
  }

  public FileExtentionForbiddenException(String message, Throwable cause) {
    super(message, cause);
  }

  public FileExtentionForbiddenException(Throwable cause) {
    super(cause);
  }

  public FileExtentionForbiddenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
