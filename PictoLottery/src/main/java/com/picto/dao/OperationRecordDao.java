package com.picto.dao;

import com.picto.entity.OperationRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by wujigang on 2016/5/22.
 */
@Repository
public interface OperationRecordDao {
    List<OperationRecord> queryOperationRecords(@Param("openid") String openId, @Param("type") Integer type);

    List<OperationRecord> queryOperationRecordsToday(@Param("openid") String openid,
        @Param("type") Integer type, @Param("today") Date today);

    void addOperationRecord(OperationRecord operationRecord);

    OperationRecord queryLatestOperToday(@Param("openid") String openid, @Param("type") int operationTypeLottery,
                                         @Param("today") Date today);

    void updateSerialNumber(@Param("id") Integer id, @Param("serialNumber") String serialNumber);
}
