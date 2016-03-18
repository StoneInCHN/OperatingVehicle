package com.ov.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.ov.entity.Admin;
import com.ov.framework.dao.impl.BaseDaoImpl;
import com.ov.dao.AdminDao;
@Repository("adminDaoImpl")
public class AdminDaoImpl extends  BaseDaoImpl<Admin,Long> implements AdminDao {

}