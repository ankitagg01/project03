package com.learning.imst.ist.Service;

import com.learning.imst.ist.Model.ClientVO;
import com.learning.imst.ist.Model.SearchClientVO;

import java.util.List;

public interface ClientMasterService {
    List<ClientVO> getAllClients(SearchClientVO searchClientVO);
//    public ClientMaster getClientByID(ClientMaster);
//    public List<ClientMaster> searchClientByName(ClientMaster);
    void insertClient(List<ClientVO> clientVOList);
//    public void updateClient(ClientMaster);
}
