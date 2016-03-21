package com.ov.dao.impl;

import org.springframework.stereotype.Repository;

import com.ov.dao.PositionDao;
import com.ov.entity.Position;
import com.ov.framework.dao.impl.BaseDaoImpl;

/**
 * 职位
 * @author huyong
 *
 */
@Repository("positionDaoImpl")
public class PositionDaoImpl extends BaseDaoImpl<Position, Long> implements PositionDao {

}
