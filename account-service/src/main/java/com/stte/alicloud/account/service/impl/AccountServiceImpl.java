package com.stte.alicloud.account.service.impl;

import com.stte.alicloud.account.mapper.AccountMapper;
import com.stte.alicloud.account.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 虎哥
 */
@Slf4j
@Service
public class AccountServiceImpl implements AccountService {
    private final AccountMapper accountMapper;

    public AccountServiceImpl(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @Override
    @Transactional
    public void debit(String userId, int money) {
        log.info("开始扣款");
        try {
            accountMapper.debit(userId, money);
        } catch (Exception e) {
            throw new RuntimeException("扣款失败，可能是余额不足！");
        }
        log.info("扣款成功");
    }
}
