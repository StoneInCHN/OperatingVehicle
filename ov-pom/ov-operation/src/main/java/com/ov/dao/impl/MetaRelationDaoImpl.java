package com.ov.dao.impl; 

import org.springframework.stereotype.Repository;

import com.ov.dao.MetaRelationDao;
import com.ov.entity.MetaRelation;
import com.ov.framework.dao.impl.BaseDaoImpl;
@Repository("metaRelationDaoImpl")
public class MetaRelationDaoImpl extends  BaseDaoImpl<MetaRelation,Long> implements MetaRelationDao {

}