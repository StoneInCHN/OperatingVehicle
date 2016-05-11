package com.ov.dao.impl; 

import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.Query;
import org.springframework.stereotype.Repository;

import com.ov.dao.MetaRelationDao;
import com.ov.entity.MetaRelation;
import com.ov.framework.dao.impl.BaseDaoImpl;
import com.ov.framework.paging.Page;
import com.ov.framework.paging.Pageable;
@Repository("metaRelationDaoImpl")
public class MetaRelationDaoImpl extends  BaseDaoImpl<MetaRelation,Long> implements MetaRelationDao {


}