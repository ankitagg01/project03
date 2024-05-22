package com.learning.imst.ist.Dao;

import com.learning.imst.ist.Entity.AccountMaster;
import com.learning.imst.ist.Model.AccountVO;
import com.learning.imst.ist.Model.SearchAccountVO;

import java.util.List;

public interface AccountMasterDao {
    List<AccountVO> selectAccount(List<SearchAccountVO> searchAccountVOList);
    void insertAccount(List<AccountVO> accountVOList);
}

