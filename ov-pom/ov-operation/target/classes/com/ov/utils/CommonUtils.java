package com.ov.utils;

import java.util.Random;

/**
 * 
 * @author tanbiao 公共方法
 */
public class CommonUtils {

  public static String randomPwd() {
    Random r = new Random();
    char[] str1 =
        {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
            's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9'};
    char[] str2 = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    char[] str3 = {'!', '@', '#', '$', '%', '^', '&', '*', '.', '~'};
    StringBuffer sBuffer = new StringBuffer();
    sBuffer.append(str1[Math.abs(r.nextInt(str1.length))]);
    sBuffer.append(str1[Math.abs(r.nextInt(str1.length))]);
    sBuffer.append(str1[Math.abs(r.nextInt(str1.length))]);
    sBuffer.append(str1[Math.abs(r.nextInt(str1.length))]);
    sBuffer.append(str1[Math.abs(r.nextInt(str1.length))]);
    sBuffer.append(str1[Math.abs(r.nextInt(str1.length))]);
    int Intindex = Math.abs(r.nextInt(6));
    sBuffer.replace(Intindex, Intindex, str2[Math.abs(r.nextInt(str2.length))] + "");
    boolean flag = true;
    while (flag) {
      int Strindex = Math.abs(r.nextInt(6));
      if (Intindex != Strindex) {
        sBuffer.replace(Strindex, Strindex, str3[Math.abs(r.nextInt(str3.length))] + "");
        flag = false;
      }
    }
    return sBuffer.toString();
  }

}
