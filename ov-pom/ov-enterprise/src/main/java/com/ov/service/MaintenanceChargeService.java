package com.ov.service; 

import java.util.Date;

import com.ov.entity.MaintenanceCharge;
import com.ov.framework.paging.Page;
import com.ov.framework.paging.Pageable;
import com.ov.framework.service.BaseService;

public interface MaintenanceChargeService extends BaseService<MaintenanceCharge,Long>{

  Page<MaintenanceCharge> findPageByFilter(String vehiclePlateSearch, Date beginDate, Date endDate, Pageable pageable,
      boolean isTenant);

}