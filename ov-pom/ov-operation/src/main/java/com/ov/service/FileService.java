package com.ov.service;

import org.springframework.web.multipart.MultipartFile;

import com.ov.beans.Setting.ImageType;

public interface FileService {
  /**
   * 上传头像图片
   * 
   * @param multipartFile
   * @return
   */
  public String saveImage(MultipartFile[] multipartFile);

  public String saveImage(MultipartFile multipartFile, ImageType imageType);


}
