package com.ov.service; 

import java.util.List;
import java.util.Map;

import com.ov.entity.Motorcade;
import com.ov.framework.service.BaseService;

public interface MotorcadeService extends BaseService<Motorcade,Long>{

  List<Map<String, Object>> findAllMotorcadeUnderTenant ();

}