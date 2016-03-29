package com.ov.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.ov.entity.Motorcade;
import com.ov.framework.dao.impl.BaseDaoImpl;
import com.ov.dao.MotorcadeDao;
@Repository("motorcadeDaoImpl")
public class MotorcadeDaoImpl extends  BaseDaoImpl<Motorcade,Long> implements MotorcadeDao {

}