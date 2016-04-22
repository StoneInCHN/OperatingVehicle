package com.ov.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.ov.beans.FileInfo;
import com.ov.beans.FileInfo.FileType;
import com.ov.beans.FileInfo.OrderType;

public interface FileService {
  /**
   * 上传图片
   * @param multiFile 上传文件
   * @param fileType 文件类型
   * @param paramMap 替换${}参数
   * @param async 是否异步
   * @return
   */
  String saveImage(MultipartFile multiFile, FileType fileType, Map<String, Object> paramMap, boolean async);
  /**
   * 文件验证
   * 
   * @param fileType 文件类型
   * @param multipartFile 上传文件
   * @return 文件验证是否通过
   */
  boolean isValid(FileType fileType, MultipartFile multipartFile);


  /**
   * 文件浏览
   * 
   * @param path 浏览路径
   * @param fileType 文件类型
   * @param orderType 排序类型
   * @return 文件信息
   */
  List<FileInfo> browser(String path, FileType fileType, OrderType orderType);

  /**
   * 删除某个文件夹下的所有子文件夹和子文件
   * 
   * @param realpath 绝对路径
   * @return boolean
   */
  boolean deletefile(String realpath) throws Exception;

  /**
   * 根据相对路径获取 应用服务器上的绝对路径
   * 
   * @param relativepath 相对路径
   * @return
   */
  String getRealPath(String relativepath);

}
