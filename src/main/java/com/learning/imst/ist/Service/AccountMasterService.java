package com.learning.imst.ist.Service;

import com.learning.imst.ist.Model.AccountVO;
import com.learning.imst.ist.Model.SearchAccountVO;
import com.learning.imst.ist.Model.SearchClientVO;

import java.util.List;

public interface AccountMasterService {
    List<AccountVO> getAllAccounts(List<SearchAccountVO> searchAccountVOList);
    void insertAccount(List<AccountVO> accountVOList);
}
