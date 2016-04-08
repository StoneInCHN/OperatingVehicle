package com.ov.service; 

import java.util.Date;

import com.ov.entity.OilCharge;
import com.ov.framework.paging.Page;
import com.ov.framework.paging.Pageable;
import com.ov.framework.service.BaseService;

public interface OilChargeService extends BaseService<OilCharge,Long>{

  Page<OilCharge> findPageByFilter(String vehiclePlateSearch, Date beginDate, Date endDate,
      Pageable pageable, boolean isTenant);

}