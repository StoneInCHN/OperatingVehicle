package com.ov.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.ov.entity.Identifier;
import com.ov.framework.dao.impl.BaseDaoImpl;
import com.ov.dao.IdentifierDao;
@Repository("identifierDaoImpl")
public class IdentifierDaoImpl extends  BaseDaoImpl<Identifier,Long> implements IdentifierDao {

}