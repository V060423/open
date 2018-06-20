package org.apel.open.repository;

import org.apel.opem.message.domain.MessageInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wangbowen
 * @Description 消息服务dao接口
 * @Date 2018/6/20 10:34
 */
@Repository
public interface MessageInfoRepository  extends CrudRepository<MessageInfo,Long> {

    MessageInfo findByMessageId(Long messageId);

    Integer findByConsumerQueue(String queue);

    void modify(MessageInfo message);

    List<MessageInfo> findByQueue(String queue, Integer offset, Integer pageSize);

    void deleteByMessageId(Long messageId);

    void deleteByBizId(Long bizId);
}
