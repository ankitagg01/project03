package com.learning.imst.ist.Service;

import com.learning.imst.ist.Dao.AccountMasterDaoImpl;
import com.learning.imst.ist.Model.ClientVO;
import com.learning.imst.ist.Model.SearchClientVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public interface ClientServiceWrapper {

    final static Logger logger = LoggerFactory.getLogger(AccountMasterDaoImpl.class);

//    List<ClientVO> getAllClient(List<Integer> clientIdList);
    List<ClientVO> getAllClients(List<SearchClientVO> searchClientVOList) throws IOException;
}
