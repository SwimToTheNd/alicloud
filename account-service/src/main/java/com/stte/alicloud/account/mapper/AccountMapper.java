package com.stte.alicloud.account.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stte.alicloud.account.entity.Account;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @author 虎哥
 */
public interface AccountMapper extends BaseMapper<Account> {

    @Update("update account_tbl set money = money - ${money} where user_id = #{userId}")
    int debit(@Param("userId") String userId, @Param("money") int money);
}
