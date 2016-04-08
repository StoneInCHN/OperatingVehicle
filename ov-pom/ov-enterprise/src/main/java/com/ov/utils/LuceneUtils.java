package com.ov.utils;

import java.io.Serializable;

import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class LuceneUtils implements Serializable {

  private static final long serialVersionUID = 7708468758384338657L;
  
  /**
   * 生成boolean query 
   * @param analyzer
   * @param indexName
   * @param value
   * @param isfuzzyQuery
   * @return
   */
  public static BooleanQuery getBooleanQuery(IKAnalyzer analyzer, String indexName,String value,boolean isfuzzyQuery) {
    try {
      BooleanQuery query = new BooleanQuery();
      if (value != null) {
        String text = QueryParser.escape(value);
        QueryParser filterParser = new QueryParser(Version.LUCENE_35,indexName, analyzer);
        Query filterQuery = null;
        if (isfuzzyQuery)
        {
          filterParser.setAllowLeadingWildcard (true);
          filterQuery = filterParser.parse("*"+text+"*");
        }else {
          filterQuery = filterParser.parse(text);
        }
        query.add(filterQuery, Occur.MUST);
      }
      return query;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
   
  }
  
  /**
   * 生成termquery
   * @param indexName
   * @param value
   * @return
   */
  public static TermQuery getTermQuery(String indexName,String value) {
      
      return new TermQuery (new Term (indexName,value));
  }
}
