package com.ov.service;

import java.util.List;

import com.ov.beans.LogConfig;

/**
 * 获取日志配置
 * 
 * @author shijun
 *
 */
public interface LogConfigService {
  /**
   * 获取所有日志配置
   * 
   * @return 所有日志配置
   */
  List<LogConfig> getAll();
}
