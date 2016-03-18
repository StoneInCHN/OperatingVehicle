package com.ov.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.ov.entity.VersionConfig;
import com.ov.framework.dao.impl.BaseDaoImpl;
import com.ov.dao.VersionConfigDao;
@Repository("versionConfigDaoImpl")
public class VersionConfigDaoImpl extends  BaseDaoImpl<VersionConfig,Long> implements VersionConfigDao {

}