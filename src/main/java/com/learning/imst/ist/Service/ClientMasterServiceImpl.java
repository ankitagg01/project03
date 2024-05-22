package com.learning.imst.ist.Service;

import com.learning.imst.ist.Dao.ClientMasterDao;
import com.learning.imst.ist.Entity.ClientMaster;
import com.learning.imst.ist.Model.ClientVO;
import com.learning.imst.ist.Model.SearchClientVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientMasterServiceImpl implements ClientMasterService {
    @Autowired
    ClientMasterDao clientMasterDao;

    @Override
    public List<ClientVO> getAllClients(SearchClientVO searchClientVO){
        return clientMasterDao.selectClient(searchClientVO);
    }

    //    public ClientMaster getClientByID(ClientMaster){};
//    public List<ClientMaster> searchClientByName(ClientMaster){};
    @Override
    public void insertClient(List<ClientVO> clientVOList){
        clientMasterDao.insertClient(clientVOList);
    };
//    public void updateClient(ClientMaster){};
}
