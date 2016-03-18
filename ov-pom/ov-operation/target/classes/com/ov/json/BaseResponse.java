package com.ov.json;

public class BaseResponse {
  
    /** response code*/
    private String code;
    
    /** response desc*/
    private String desc;

    public String getCode() {
      return code;
    }

    public void setCode(String code) {
      this.code = code;
    }

    public String getDesc() {
      return desc;
    }

    public void setDesc(String desc) {
      this.desc = desc;
    }
    
}
