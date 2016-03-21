package com.ov.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ov.dao.PositionDao;
import com.ov.entity.Department;
import com.ov.entity.Position;
import com.ov.framework.filter.Filter;
import com.ov.framework.filter.Filter.Operator;
import com.ov.framework.service.impl.BaseServiceImpl;
import com.ov.service.PositionService;
import com.ov.utils.FieldFilterUtils;

/**
 * 职位
 * @author huyong
 *
 */
@Service("positionServiceImpl")
public class PositionServiceImpl extends BaseServiceImpl<Position, Long> implements PositionService {

  @Resource(name = "positionDaoImpl")
  private PositionDao positionDao;
  
  @Resource
  public void setBaseDao(PositionDao positionDao) {
    super.setBaseDao(positionDao);
  }

  @Override
  public List<Map<String, Object>> findPositions (Department department){
    List<Filter> filters = new ArrayList<Filter>();
    Filter filter = new Filter("department",Operator.eq, department);
    filters.add(filter);
    List<Position> positionList = positionDao.findList (null, null, filters, null);
    String[] propertys =
      {"id", "name"};
    return FieldFilterUtils.filterCollectionMap(propertys, positionList);
  }

}
