package com.ov.service.impl;

import java.awt.image.BufferedImage;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.ov.common.log.LogUtil;
import com.ov.beans.Setting;
import com.ov.beans.Setting.CaptchaType;
import com.ov.service.CaptchaService;
import com.ov.utils.SettingUtils;

/**
 * Service - 验证码
 */
@Service("captchaServiceImpl")
public class CaptchaServiceImpl implements CaptchaService {

  @Resource(name = "imageCaptchaService")
  private com.octo.captcha.service.CaptchaService imageCaptchaService;

  public BufferedImage buildImage(String captchaId) {
    if (LogUtil.isDebugEnabled(getClass())) {
      LogUtil.debug(getClass(), "buildImage", "Parameter captchaId=%s",
          captchaId);
    }
    return (BufferedImage) imageCaptchaService.getChallengeForID(captchaId);
  }

  public boolean isValid(CaptchaType captchaType, String captchaId, String captcha) {
    if (LogUtil.isDebugEnabled(getClass())) {
      LogUtil.debug(getClass(), "isValid", "Parameter captchaType=%s, captchaId=%s, captcha=%s",
          captchaType.toString(),captchaId,captcha);
    }
    Setting setting = SettingUtils.get();
    if (captchaType == null || ArrayUtils.contains(setting.getCaptchaTypes(), captchaType)) {
      if (StringUtils.isNotEmpty(captchaId) && StringUtils.isNotEmpty(captcha)) {
        try {
          return imageCaptchaService.validateResponseForID(captchaId, captcha.toUpperCase());
        } catch (Exception e) {
          return false;
        }
      } else {
        return false;
      }
    } else {
      return true;
    }
  }

}
