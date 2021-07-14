package com.stte.alicloud.storage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stte.alicloud.storage.entity.Storage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface StorageMapper extends BaseMapper<Storage> {
    @Update("update storage_tbl set `count` = `count` - #{count} where commodity_code = #{code}")
    int deduct(@Param("code") String commodityCode, @Param("count") int count);
}
