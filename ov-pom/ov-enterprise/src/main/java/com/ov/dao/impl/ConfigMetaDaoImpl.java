package com.ov.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.ov.entity.ConfigMeta;
import com.ov.framework.dao.impl.BaseDaoImpl;
import com.ov.dao.ConfigMetaDao;
@Repository("configMetaDaoImpl")
public class ConfigMetaDaoImpl extends  BaseDaoImpl<ConfigMeta,Long> implements ConfigMetaDao {

}