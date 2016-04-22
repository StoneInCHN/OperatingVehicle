package com.ov.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import com.ov.beans.FileInfo;
import com.ov.beans.FileInfo.FileType;
import com.ov.beans.FileInfo.OrderType;
import com.ov.beans.Setting;
import com.ov.plugin.StoragePlugin;
import com.ov.service.FileService;
import com.ov.service.PluginService;
import com.ov.service.TenantAccountService;
import com.ov.utils.FreemarkerUtils;
import com.ov.utils.ImageUtils;
import com.ov.utils.SettingUtils;

/**
 * 文件服务
 * 
 * @author luzhang
 *
 */
@Service("fileServiceImpl")
public class FileServiceImpl implements FileService, ServletContextAware {

  @Value("${ImageExtension}")
  private String imageExtension;//剪切后的图片类型
  @Value("${ImageUploadPath}")
  private String imageUploadPath;// 照片保存在磁盘的路径（前半部分）
  @Value("${ProjectUploadPath}")
  private String projectUploadPath;// 项目访问照片的路径（前半部分）
  @Resource(name="tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;
  
  @Resource(name = "taskExecutor")
  private TaskExecutor taskExecutor;
  @Resource(name = "pluginServiceImpl")
  private PluginService pluginService;
  
  /** servletContext */
  private ServletContext servletContext;
  public void setServletContext(ServletContext servletContext) {
    this.servletContext = servletContext;
  }
  /**
   * 上传图片
   * @param multiFile 上传文件
   * @param fileType 文件类型
   * @param paramMap 替换${}参数
   * @param async 是否异步
   * @return
   */
  @Override
  public String saveImage(MultipartFile multiFile, FileType fileType, Map<String, Object> paramMap, boolean async) {
    String imageDestURL = "";// 照片保存在磁盘的路径
    String projectAccessURL = "";// 项目访问照片的路径
    try {
      if (multiFile == null) {
        return null;
      }
      if (fileType == FileType.PROFILE_PICTURE) {
        String profilePicture_postfix =
            FreemarkerUtils.process(SettingUtils.get().getProfilePictureUploadPath(), paramMap);
        imageDestURL = imageUploadPath + profilePicture_postfix;
        projectAccessURL = projectUploadPath + profilePicture_postfix;
      }
      File tempFile =
          new File(System.getProperty("java.io.tmpdir") + File.separator + "upload_"
              + UUID.randomUUID() + ".tmp");
      if (!tempFile.getParentFile().exists()) {
        tempFile.getParentFile().mkdirs();
      }
      multiFile.transferTo(tempFile);
      if (async) {//异步，启用多线程
        addTask(tempFile, imageDestURL);
      }else{
        proccessImage(tempFile, imageDestURL);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return projectAccessURL;
  }
  private void addTask(final File tempFile, final String path) {
    taskExecutor.execute(new Runnable() {
      public void run() {
        try {
          proccessImage(tempFile, path);
        } finally {
          FileUtils.deleteQuietly(tempFile);
        }
      }
    });
  }
  /**
   * 直接保存手机端图片，不处理
   * @param tempFile
   * @param sourcePath
   * @param resizedPath
   */
  private void proccessImage(File tempFile, String sourcePath) {
    try {
          File destSrcFile = new File(sourcePath);
          FileUtils.moveFile(tempFile, destSrcFile);
          FileUtils.deleteQuietly(tempFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
  } 
  /**
   * 处理并保存图片
   * @param tempFile
   * @param sourcePath
   * @param resizedPath
   * @param width
   * @param height
   * @param moveSource
   */
  private void proccessImage(File tempFile, String sourcePath, String resizedPath, Integer width,
      Integer height, boolean moveSource) {
    String tempPath = System.getProperty("java.io.tmpdir");
    File resizedFile =
        new File(tempPath + File.pathSeparator + "upload_" + UUID.randomUUID() + "."
            + imageExtension);
    ImageUtils.zoom(tempFile, resizedFile, width, height);

    File destFile = new File(resizedPath);
    try {
      if (moveSource) {
        File destSrcFile = new File(sourcePath);
        FileUtils.moveFile(tempFile, destSrcFile);
      }
      FileUtils.moveFile(resizedFile, destFile);
      FileUtils.deleteQuietly(tempFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
 
  /**
   * 文件验证
   * 
   * @param fileType 文件类型
   * @param multipartFile 上传文件
   * @return 文件验证是否通过
   */
  @Override
  public boolean isValid(FileType fileType, MultipartFile multipartFile) {
    if (multipartFile == null) {
      return false;
    }
    Setting setting = SettingUtils.get();
    if (setting.getUploadMaxSize() != null && setting.getUploadMaxSize() != 0
        && multipartFile.getSize() > setting.getUploadMaxSize() * 1024L * 1024L) {
      return false;
    }
    String[] uploadExtensions;
    if (fileType == FileType.FLASH) {
      uploadExtensions = setting.getUploadFlashExtensions();
    } else if (fileType == FileType.MEDIA) {
      uploadExtensions = setting.getUploadMediaExtensions();
    } else if (fileType == FileType.FILE) {
      uploadExtensions = setting.getUploadFileExtensions();
    } else {
      uploadExtensions = setting.getUploadImageExtensions();
    }
    if (!ArrayUtils.isEmpty(uploadExtensions)) {
      return FilenameUtils.isExtension(multipartFile.getOriginalFilename(), uploadExtensions);
    }
    return false;
  }
  /**
   * 文件浏览
   * 
   * @param path 浏览路径
   * @param fileType 文件类型
   * @param orderType 排序类型
   * @return 文件信息
   */
  @Override
  public List<FileInfo> browser(String path, FileType fileType, OrderType orderType) {
    if (path != null) {
      if (!path.startsWith("/")) {
        path = "/" + path;
      }
      if (!path.endsWith("/")) {
        path += "/";
      }
    } else {
      path = "/";
    }
    Setting setting = SettingUtils.get();
    String uploadPath;
    if (fileType == FileType.FLASH) {
      uploadPath = setting.getFlashUploadPath();
    } else if (fileType == FileType.MEDIA) {
      uploadPath = setting.getMediaUploadPath();
    } else if (fileType == FileType.FILE) {
      uploadPath = setting.getFileUploadPath();
    } else {
      uploadPath = setting.getImageUploadPath();
    }
    String browsePath = StringUtils.substringBefore(uploadPath, "${");
    browsePath = StringUtils.substringBeforeLast(browsePath, "/") + path;

    List<FileInfo> fileInfos = new ArrayList<FileInfo>();
    if (browsePath.indexOf("..") >= 0) {
      return fileInfos;
    }
    for (StoragePlugin storagePlugin : pluginService.getStoragePlugins(true)) {
      fileInfos = storagePlugin.browser(browsePath);
      break;
    }
    if (orderType == OrderType.SIZE) {
      Collections.sort(fileInfos, new SizeComparator());
    } else if (orderType == OrderType.TYPE) {
      Collections.sort(fileInfos, new TypeComparator());
    } else {
      Collections.sort(fileInfos, new NameComparator());
    }
    return fileInfos;
  }

  private class NameComparator implements Comparator<FileInfo> {
    public int compare(FileInfo fileInfos1, FileInfo fileInfos2) {
      return new CompareToBuilder()
          .append(!fileInfos1.getIsDirectory(), !fileInfos2.getIsDirectory())
          .append(fileInfos1.getName(), fileInfos2.getName()).toComparison();
    }
  }

  private class SizeComparator implements Comparator<FileInfo> {
    public int compare(FileInfo fileInfos1, FileInfo fileInfos2) {
      return new CompareToBuilder()
          .append(!fileInfos1.getIsDirectory(), !fileInfos2.getIsDirectory())
          .append(fileInfos1.getSize(), fileInfos2.getSize()).toComparison();
    }
  }

  private class TypeComparator implements Comparator<FileInfo> {
    public int compare(FileInfo fileInfos1, FileInfo fileInfos2) {
      return new CompareToBuilder()
          .append(!fileInfos1.getIsDirectory(), !fileInfos2.getIsDirectory())
          .append(FilenameUtils.getExtension(fileInfos1.getName()),
              FilenameUtils.getExtension(fileInfos2.getName())).toComparison();
    }
  }

  /**
   * 删除某个文件夹下的所有子文件夹和子文件
   * 
   * @param realPath 绝对路径
   * @return boolean
   */
  @Override
  public boolean deletefile(String realPath) throws Exception {
    if (StringUtils.isBlank(realPath))
      return false;
    boolean isDeleted = false;
    try {
      File file = new File(realPath);
      if (!file.isDirectory()) {
        isDeleted = file.delete();
      } else if (file.isDirectory()) {
        String[] filelist = file.list();
        for (int i = 0; i < filelist.length; i++) {
          File delfile = new File(realPath + "\\" + filelist[i]);
          if (!delfile.isDirectory()) {
            delfile.delete();
          } else if (delfile.isDirectory()) {
            deletefile(realPath + "\\" + filelist[i]);
          }
        }
        isDeleted = file.delete();
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return isDeleted;
    }
    return isDeleted;
  }

  /**
   * 根据相对路径获取 应用服务器上的绝对路径
   * 
   * @param relativepath 相对路径
   * @return
   */
  @Override
  public String getRealPath(String relativepath) {
    if (StringUtils.isBlank(relativepath)) {
      return "";
    }
    return servletContext.getRealPath(relativepath);
  }

}
