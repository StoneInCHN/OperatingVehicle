package com.ov.service; 

import java.util.Date;

import com.ov.entity.UpkeepCharge;
import com.ov.framework.paging.Page;
import com.ov.framework.paging.Pageable;
import com.ov.framework.service.BaseService;

public interface UpkeepChargeService extends BaseService<UpkeepCharge,Long>{

  Page<UpkeepCharge> findPageByFilter(String vehiclePlateSearch, Date beginDate, Date endDate,
      Pageable pageable, boolean isTenant);

}