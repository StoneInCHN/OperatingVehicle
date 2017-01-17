package com.ov.dao.impl; 

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.ov.dao.DeviceInfoDao;
import com.ov.entity.DeviceInfo;
import com.ov.framework.dao.impl.BaseDaoImpl;
@Repository("deviceInfoDaoImpl")
public class DeviceInfoDaoImpl extends  BaseDaoImpl<DeviceInfo,Long> implements DeviceInfoDao {
  @Override
  public DeviceInfo findByDeviceNo(String deviceNo) {
    if (deviceNo == null) {
      return null;
    }
    try {
      String jpql =
          "select deviceInfo from DeviceInfo deviceInfo where lower(deviceInfo.deviceNo) = lower(:deviceNo)";
      return entityManager.createQuery(jpql, DeviceInfo.class).setFlushMode(FlushModeType.COMMIT)
          .setParameter("deviceNo", deviceNo).getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }
}