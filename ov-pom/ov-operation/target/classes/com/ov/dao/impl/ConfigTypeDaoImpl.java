package com.ov.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.ov.entity.ConfigType;
import com.ov.framework.dao.impl.BaseDaoImpl;
import com.ov.dao.ConfigTypeDao;
@Repository("configTypeDaoImpl")
public class ConfigTypeDaoImpl extends  BaseDaoImpl<ConfigType,Long> implements ConfigTypeDao {

}