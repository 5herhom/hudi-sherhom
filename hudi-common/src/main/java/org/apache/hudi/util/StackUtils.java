package org.apache.hudi.util;

/**
 * @author sherhom
 * @date 2022/06/02 15:46
 */
public class StackUtils {
  public static String getStackTrace() {
    Throwable throwable = new Throwable();
    StackTraceElement[] stackElements = throwable.getStackTrace();
    StringBuilder sb = new StringBuilder();
    if (null != stackElements) {
      for (int i = 0; i < stackElements.length; i++) {
        sb.append(stackElements[i].getClassName());
        sb.append(".").append(stackElements[i].getMethodName());
        sb.append("(").append(stackElements[i].getFileName()).append(":");
        sb.append(stackElements[i].getLineNumber()+")").append("\n");
      }
    }
    return sb.toString();
  }

}
