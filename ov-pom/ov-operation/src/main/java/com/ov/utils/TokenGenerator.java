package com.ov.utils;

import java.util.Date;
import java.util.UUID;

public class TokenGenerator {

  public static String generateToken() {
    String token = UUID.randomUUID().toString() + "__" + new Date().getTime();
    return token;
  }

}
