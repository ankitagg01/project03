package com.learning.imst.ist.Service;

import com.learning.imst.ist.Dao.AccountMasterDao;
import com.learning.imst.ist.Dao.AccountMasterDaoImpl;
import com.learning.imst.ist.Model.AccountVO;
import com.learning.imst.ist.Model.SearchAccountVO;
import com.learning.imst.ist.Model.SearchClientVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountMasterServiceImpl implements AccountMasterService{

    final static Logger logger = LoggerFactory.getLogger(AccountMasterDaoImpl.class);

    @Autowired
    AccountMasterDao accountMasterDao;

    @Override
    public List<AccountVO> getAllAccounts(List<SearchAccountVO> searchAccountVOList){
        logger.debug("Service Impl - AccountMaster - Select Started");
        return accountMasterDao.selectAccount(searchAccountVOList);
//        logger.debug("Service Impl - AccountMaster - Select Finished");
    }

    @Override
    public void insertAccount(List<AccountVO> accountVOList){
        accountMasterDao.insertAccount(accountVOList);
    }
}
