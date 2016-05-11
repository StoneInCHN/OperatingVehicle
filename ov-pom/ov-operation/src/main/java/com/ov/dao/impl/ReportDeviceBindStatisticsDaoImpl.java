package com.ov.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.ov.dao.ReportDeviceBindStatisticsDao;
import com.ov.entity.ReportDeviceBindStatistics;
import com.ov.framework.dao.impl.BaseDaoImpl;

@Repository("reportDeviceBindStatisticsDaoImpl")
public class ReportDeviceBindStatisticsDaoImpl extends
    BaseDaoImpl<ReportDeviceBindStatistics, Long> implements ReportDeviceBindStatisticsDao {

  @Override
  public List<ReportDeviceBindStatistics> findList(Date startDate, Date endDate) {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<ReportDeviceBindStatistics> criteriaQuery =
        criteriaBuilder.createQuery(ReportDeviceBindStatistics.class);
    Root<ReportDeviceBindStatistics> root = criteriaQuery.from(ReportDeviceBindStatistics.class);
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
