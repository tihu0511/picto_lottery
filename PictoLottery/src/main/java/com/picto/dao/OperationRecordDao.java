package com.picto.dao;

import com.picto.entity.OperationRecord;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by wujigang on 2016/5/22.
 */
public interface OperationRecordDao {
    List<OperationRecord> queryOperationRecords(@Param("openid") String openId, @Param("type") Integer type);

    List<OperationRecord> queryOperationRecordsToday(@Param("openid") String openid,
        @Param("type") Integer operationTypeLottery, @Param("today") Date today);

    void addOperationRecord(OperationRecord operationRecord);
}
