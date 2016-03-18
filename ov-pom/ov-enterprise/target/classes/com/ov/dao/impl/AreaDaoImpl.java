package com.ov.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.ov.entity.Area;
import com.ov.framework.dao.impl.BaseDaoImpl;
import com.ov.dao.AreaDao;
@Repository("areaDaoImpl")
public class AreaDaoImpl extends  BaseDaoImpl<Area,Long> implements AreaDao {

}