package org.apel.open.repository;

import org.apel.open.model.TraceLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wangbowen
 * @Description TODO
 * @Date 2018/6/19 15:24
 */
@Repository
public interface TraceLogRepository extends CrudRepository<TraceLog,Long> {
}
