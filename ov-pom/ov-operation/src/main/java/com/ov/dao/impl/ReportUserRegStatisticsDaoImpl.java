package com.ov.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.ov.dao.ReportUserRegStatisticsDao;
import com.ov.entity.ReportUserRegStatistics;
import com.ov.framework.dao.impl.BaseDaoImpl;

@Repository("reportUserRegStatisticsDaoImpl")
public class ReportUserRegStatisticsDaoImpl extends BaseDaoImpl<ReportUserRegStatistics, Long>
    implements ReportUserRegStatisticsDao {

  @Override
  public List<ReportUserRegStatistics> findList(Date startDate, Date endDate) {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<ReportUserRegStatistics> criteriaQuery =
        criteriaBuilder.createQuery(ReportUserRegStatistics.class);
    Root<ReportUserRegStatistics> root = criteriaQuery.from(ReportUserRegStatistics.class);
    criteriaQuery.select(root);
    Predicate restrictions = criteriaBuilder.conjunction();
    if (startDate != null) {
      restrictions =
          criteriaBuilder.and(restrictions,
              criteriaBuilder.greaterThanOrEqualTo(root.<Date>get("statisticsDate"), startDate));
    }
    if (endDate != null) {
      restrictions =
          criteriaBuilder.and(restrictions,
              criteriaBuilder.lessThanOrEqualTo(root.<Date>get("statisticsDate"), endDate));
    }
    criteriaQuery.where(restrictions);
    criteriaQuery.orderBy(criteriaBuilder.asc(root.get("createDate")));

    return super.findList(criteriaQuery, null, null, null, null);
  }



}
