package com.learning.imst.ist.Dao;

import com.learning.imst.ist.Entity.AccountMaster;
import com.learning.imst.ist.Entity.ClientMaster;
import com.learning.imst.ist.Model.AccountVO;
import com.learning.imst.ist.Model.ClientVO;
import com.learning.imst.ist.Model.SearchAccountVO;
import com.learning.imst.ist.Model.SearchClientVO;
import com.learning.imst.ist.Service.ClientServiceWrapper;
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
import java.util.*;

@Repository
public class AccountMasterDaoImpl implements AccountMasterDao {

    final static Logger logger = LoggerFactory.getLogger(AccountMasterDaoImpl.class);

    @Autowired
    SessionFactory sFactory;

    @Autowired
    ClientServiceWrapper clientServiceWrapper;

    @Override
    public List<AccountVO> selectAccount(List<SearchAccountVO> searchAccountVOList){
        logger.debug("DAO Impl - AccountMaster - Select Started");
        Transaction transaction = null;
        Session session = null;
        List<AccountMaster> amList = null;
        List<AccountVO> accountVOList = new ArrayList<AccountVO>();

        for (SearchAccountVO searchAccountVO:searchAccountVOList) {

            try {
                session = sFactory.openSession();
                transaction = session.beginTransaction();
                CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
                CriteriaQuery<AccountMaster> criteriaQuery = criteriaBuilder.createQuery(AccountMaster.class);
                Root<AccountMaster> root = criteriaQuery.from(AccountMaster.class);
                Predicate[] predicates = new Predicate[3];
                if (searchAccountVO.getAccount_name() != null) {
                    predicates[0] = criteriaBuilder.like(root.get("account_name"), "%" + searchAccountVO.getAccount_name() + "%");
                } else {
                    predicates[0] = criteriaBuilder.like(root.get("account_name"), "%%");
                }

                if (searchAccountVO.getAccount_number() != null) {
                    predicates[1] = criteriaBuilder.equal(root.get("account_number"), searchAccountVO.getAccount_number());
                } else {
                    predicates[1] = criteriaBuilder.gt(root.get("account_number"), 0);
                }

                if (searchAccountVO.getAccount_balance() != null) {
                    predicates[2] = criteriaBuilder.equal(root.get("account_balance"), searchAccountVO.getAccount_balance());
                } else {
                    predicates[2] = criteriaBuilder.gt(root.get("account_balance"), 0);
                }

                criteriaQuery.select(root).where(predicates);

                Query<AccountMaster> query = session.createQuery(criteriaQuery);
                amList = query.getResultList();
                logger.debug("DAO Impl - AccountMaster - Select Success");

            } catch (Exception e) {
                e.printStackTrace();
            }
            for (AccountMaster aM : amList) {
                AccountVO accountVO = new AccountVO();
                accountVO.setAccount_number(aM.getAccount_number());
                accountVO.setAccount_name(aM.getAccount_name());
                accountVO.setAccount_balance(aM.getAccount_balance());
                accountVO.setAccount_open_date(aM.getAccount_open_date());
                accountVO.setCreate_ts(aM.getCreate_ts());
                Set<ClientMaster> clientMasterSet = aM.getClients();
                for (ClientMaster clientMaster: clientMasterSet){
                    accountVO.setClient_id(clientMaster.getClient_id());
                }
                accountVOList.add(accountVO);
            }
        }
        return accountVOList;
    }

    @Override
    public void insertAccount(List<AccountVO> accountVOList){
        logger.debug("DAO Impl - AccountMaster - Insert Started");
        Transaction transaction = null;
        Session session = null;

        try{
            session = sFactory.openSession();
            transaction = session.beginTransaction();
            for (AccountVO accountVO: accountVOList) {
                AccountMaster accountMaster = new AccountMaster();
                Set<ClientMaster> clientMasterSet = new HashSet<>(0);
                ClientMaster clientMaster = new ClientMaster();

                List<SearchClientVO> searchClientVOList = new ArrayList<>(0);
                SearchClientVO searchClientVO = new SearchClientVO();
                searchClientVO.setClient_id(accountVO.getClient_id());
                searchClientVO.setClient_name(null);
                searchClientVOList.add(searchClientVO);
                List<ClientVO> clientVOList = clientServiceWrapper.getAllClients(searchClientVOList);

                clientMaster.setClient_id(accountVO.getClient_id());
                clientMaster.setClient_name(clientVOList.get(0).getClient_name());
                clientMaster.setClient_address(clientVOList.get(0).getClient_address());
                clientMaster.setCreate_ts(clientVOList.get(0).getCreate_ts());

                clientMasterSet.add(clientMaster);
                accountMaster.setAccount_name(accountVO.getAccount_name());
                accountMaster.setAccount_balance(accountVO.getAccount_balance());
                accountMaster.setAccount_open_date(new java.sql.Date(new Date().getTime()));
                accountMaster.setClients(clientMasterSet);
                accountMaster.setCreate_ts(new Timestamp(new Date().getTime()));
                session.persist(accountMaster);
            }
            session.getTransaction().commit();
            logger.debug("DAO Impl - AccountMaster - Insert Failed");
        }
        catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }

    };
}
