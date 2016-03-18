package com.ov.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ibm.icu.text.SimpleDateFormat;
import com.ov.beans.Setting.ImageType;
import com.ov.service.FileService;
import com.ov.utils.ImageUtils;

@Service("fileServiceImpl")
public class FileServiceImpl implements FileService {

  private static final String DEST_EXTENSION = "jpg";

  @Value("${ImageUploadPath}")
  private String uploadPath;
  @Value("${ImageMaxSize}")
  private Integer ImageMaxSize;

  @Value("${ListImageWidth}")
  private Integer listImageWidth;
  @Value("${ListImageHeight}")
  private Integer listImageHeight;
  @Value("${AutoServiceImageHeight}")
  private Integer autoServiceImageHeight;


  @Override
  public String saveImage(MultipartFile[] multipartFile) {
    String webPath = null;
    if (multipartFile == null || multipartFile.length == 0) {
      return null;
    }
    try {
      for (MultipartFile multiFile : multipartFile) {
        if (multiFile.getSize() > ImageMaxSize) {
          continue;
        }
        String uuid = UUID.randomUUID().toString();
        String sourcePath =
            uploadPath + File.separator + "src_" + uuid + "."
                + FilenameUtils.getExtension(multiFile.getOriginalFilename());
        webPath =
            File.separator + "src_" + uuid + "."
                + FilenameUtils.getExtension(multiFile.getOriginalFilename());
        String storePath = uploadPath + File.separator + uuid + "." + DEST_EXTENSION;;

        File tempFile =
            new File(System.getProperty("java.io.tmpdir") + File.separator + "upload_"
                + UUID.randomUUID() + ".tmp");
        if (!tempFile.getParentFile().exists()) {
          tempFile.getParentFile().mkdirs();
        }

        multiFile.transferTo(tempFile);
        proccessImage(tempFile, sourcePath, storePath, listImageWidth, listImageHeight, true);
      }
    } catch (IllegalStateException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return webPath;
  }

  private void proccessImage(File tempFile, String sourcePath, String resizedPath, Integer width,
      Integer height, boolean moveSource) {
    String tempPath = System.getProperty("java.io.tmpdir");
    File resizedFile =
        new File(tempPath + File.pathSeparator + "upload_" + UUID.randomUUID() + "."
            + DEST_EXTENSION);
    ImageUtils.zoom(tempFile, resizedFile, width, height);

    File destFile = new File(resizedPath);
    try {
      if (moveSource) {
        File destSrcFile = new File(sourcePath);
        FileUtils.moveFile(tempFile, destSrcFile);
      }
      FileUtils.moveFile(resizedFile, destFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String saveImage(MultipartFile multiFile, ImageType imageType) {
    SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
    String date = sdFormat.format(new Date());
    String webPath = null;
    String imgUploadPath = "";
    try {
      if (multiFile == null || multiFile.getSize() > ImageMaxSize) {
        return null;
      }
      String uuid = UUID.randomUUID().toString();
      if (imageType == ImageType.LICENSE) {
        imgUploadPath = uploadPath + File.separator + "license";
      }
      if (imageType == ImageType.STOREPICTURE) {
        imgUploadPath = uploadPath + File.separator + "storePicture";
      }

      String sourcePath =
          imgUploadPath + File.separator + date + File.separator + "src_" + uuid + "."
              + FilenameUtils.getExtension(multiFile.getOriginalFilename());
      webPath =
          date + File.separator + "src_" + uuid + "."
              + FilenameUtils.getExtension(multiFile.getOriginalFilename());
      String storePath =
          imgUploadPath + File.separator + date + File.separator + uuid + "." + DEST_EXTENSION;;

      File tempFile =
          new File(System.getProperty("java.io.tmpdir") + File.separator + "upload_"
              + UUID.randomUUID() + ".tmp");
      if (!tempFile.getParentFile().exists()) {
        tempFile.getParentFile().mkdirs();
      }
      multiFile.transferTo(tempFile);
      proccessImage(tempFile, sourcePath, storePath, listImageWidth, listImageHeight, true);

    } catch (IllegalStateException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return webPath;
  }

}
