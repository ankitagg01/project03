package com.learning.imst.ist.Dao;

import com.learning.imst.ist.Entity.ClientMaster;
import com.learning.imst.ist.Model.ClientVO;
import com.learning.imst.ist.Model.SearchClientVO;

import java.util.List;

public interface ClientMasterDao {
    public List<ClientVO> selectClient(SearchClientVO searchClientVO);
    public void insertClient(List<ClientVO> clientVOList);
//    public void updateClient(List<ClientMaster>);
}
