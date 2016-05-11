package com.ov.dao.impl; 

import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.Query;
import org.springframework.stereotype.Repository; 

import com.ov.entity.Area;
import com.ov.framework.dao.impl.BaseDaoImpl;
import com.ov.framework.paging.Page;
import com.ov.framework.paging.Pageable;
import com.ov.dao.AreaDao;
@Repository("areaDaoImpl")
public class AreaDaoImpl extends  BaseDaoImpl<Area,Long> implements AreaDao {

 
}