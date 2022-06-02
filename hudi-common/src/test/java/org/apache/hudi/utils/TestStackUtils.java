package org.apache.hudi.utils;

import org.apache.hudi.util.StackUtils;
import org.junit.jupiter.api.Test;

/**
 * @author sherhom
 * @date 2022/06/02 15:52
 */
public class TestStackUtils {
  @Test
  public void stackTest(){
    System.out.println(StackUtils.getStackTrace());
  }
}
