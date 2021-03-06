package org.apel.open.repository;

import org.apel.open.model.CommonLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wangbowen
 * @Description 请求数据dao
 * @Date 2018/6/19 15:24
 */
@Repository
public interface CommonLogRepository  extends CrudRepository<CommonLog,Long> {
}
