package com.ov.dao.impl;

import org.springframework.stereotype.Repository;

import com.ov.dao.ElectronicRailDao;
import com.ov.entity.ElectronicRail;
import com.ov.framework.dao.impl.BaseDaoImpl;

@Repository("electronicRailDaoImpl")
public class ElectronicRailDaoImpl extends BaseDaoImpl<ElectronicRail, Long> implements ElectronicRailDao{

}
