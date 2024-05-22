package com.learning.imst.ist.Dao;

import com.learning.imst.ist.Entity.AccountMaster;
import com.learning.imst.ist.Entity.ClientMaster;
import com.learning.imst.ist.Model.ClientVO;
import com.learning.imst.ist.Model.SearchClientVO;
import org.slf4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Repository
public class ClientMasterDaoImpl implements ClientMasterDao {

    final static Logger logger = LoggerFactory.getLogger(ClientMasterDaoImpl.class);

    @Autowired
    SessionFactory sFactory;

//    @Autowired
//    HibernateInsertThread hibernateInsertThread;

    @Override
    public List<ClientVO> selectClient(SearchClientVO searchClientVO){
        logger.debug("DAO Impl - ClientMaster - Select Started");
        Transaction transaction = null;
        Session session = null;
        List<ClientMaster> cmList = null;
        List<ClientVO> clientVOList = new ArrayList<ClientVO>();
        try {
            session = sFactory.openSession();
            transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<ClientMaster> criteriaQuery = criteriaBuilder.createQuery(ClientMaster.class);
            Root<ClientMaster> root = criteriaQuery.from(ClientMaster.class);
            Predicate[] predicates = new Predicate[2];
            if (searchClientVO.getClient_name() != null) {
                predicates[0] = criteriaBuilder.like(root.get("client_name"), "%" + searchClientVO.getClient_name() + "%");
            }
            else{
                predicates[0] = criteriaBuilder.like(root.get("client_name"),"%%");
            }

            if (searchClientVO.getClient_id()!= null) {
                predicates[1] = criteriaBuilder.equal(root.get("client_id"),searchClientVO.getClient_id());
            }
            else {
                predicates[1] = criteriaBuilder.gt(root.get("client_id"),0);
            }
            criteriaQuery.select(root).where(predicates);

            Query<ClientMaster> query = session.createQuery(criteriaQuery);
            logger.debug("Getting Clients : " + query);
            cmList = query.getResultList();
            logger.debug("DAO Impl - ClientMaster - Select Success");

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        for (ClientMaster cM : cmList){
            ClientVO clientVO = new ClientVO();
            clientVO.setClient_id(cM.getClient_id());
            clientVO.setClient_name(cM.getClient_name());
            clientVO.setClient_address(cM.getClient_address());
            clientVO.setCreate_ts(cM.getCreate_ts());
            clientVOList.add(clientVO);
        }

        return clientVOList;
    }

    @Override
    public void insertClient(List<ClientVO> clientVOList){
        logger.debug("DAO Impl - ClientMaster - Insert Started");
        Transaction transaction = null;
        Session session = null;

        try {
            session = sFactory.openSession();
            transaction = session.beginTransaction();
            int clientInsertCount = 0;
            for (ClientVO clientVO : clientVOList) {
                clientInsertCount = clientInsertCount + 1;
            }

            for (ClientVO clientVO : clientVOList) {
                ClientMaster clientMaster = new ClientMaster();
                Set<AccountMaster> accounts = null;
                clientMaster.setAccounts(accounts);
                clientMaster.setClient_id(null);
                clientMaster.setClient_name(clientVO.getClient_name());
                clientMaster.setClient_address(clientVO.getClient_address());
                clientMaster.setCreate_ts(new Timestamp(new Date().getTime()));
//                hibernateInsertThread.setObject(clientMaster);
//                Thread thread_1 = new Thread(hibernateInsertThread);
//                thread_1.start();
                session.save(clientMaster);
            }
            transaction.commit();
            logger.debug("DAO Impl - ClientMaster - Insert Success");
        }
        catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }
//    public void updateClient(List<ClientMaster>);
}
