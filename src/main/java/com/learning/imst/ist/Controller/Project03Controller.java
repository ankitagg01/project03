package com.learning.imst.ist.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.imst.ist.Dao.ClientMasterDaoImpl;
import com.learning.imst.ist.Entity.ClientMaster;
import com.learning.imst.ist.Entity.AccountMaster;
import com.learning.imst.ist.Model.AccountVO;
import com.learning.imst.ist.Model.ClientVO;
import com.learning.imst.ist.Model.SearchAccountVO;
import com.learning.imst.ist.Model.SearchClientVO;
import com.learning.imst.ist.Service.AccountMasterService;
import com.learning.imst.ist.Service.ClientMasterService;
import com.learning.imst.ist.Service.ClientServiceWrapperImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/v1")
public class Project03Controller {

    final static Logger logger = LoggerFactory.getLogger(ClientMasterDaoImpl.class);

    @Autowired
    ClientMasterService clientMasterService;

    @Autowired
    AccountMasterService accountMasterService;

    @Autowired
    ClientServiceWrapperImpl clientServiceWrapper;

    @GetMapping(value="/getjson/clientmaster")
    public String getClientJson(){
        logger.debug("Getting JSON object for clientmaster 1 - test");
//        To get JSON format of the Client Master
        String jsonStrAccMstr = new String();
        try {
            ClientMaster testcm = new ClientMaster();
            ObjectMapper obj = new ObjectMapper();
            jsonStrAccMstr = obj.writeValueAsString(testcm);
        }
        catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return jsonStrAccMstr;

    }

    @GetMapping(value="/getjson/accountmaster")
    public String getAccountJson(){
        logger.debug("Getting JSON object for accountmaster");
//        To get JSON format of the Client Master
        String jsonStrAccMstr = new String();
        try {
            AccountMaster testam = new AccountMaster();
            ObjectMapper obj = new ObjectMapper();
            jsonStrAccMstr = obj.writeValueAsString(testam);
        }
        catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return jsonStrAccMstr;

    }

    @GetMapping(value="/getjson/listclientvo")
    public String getClientVOJson(){
        logger.debug("Getting JSON object for listclientvo");
//        To get JSON format of the Client Master
        String jsonStrAccMstr = new String();
        try {
            List<ClientVO> testam = new ArrayList<ClientVO>(2);
            ClientVO test1 = new ClientVO();
            ClientVO test2 = new ClientVO();
            testam.add(test1);
            testam.add(test2);
            ObjectMapper obj = new ObjectMapper();
            jsonStrAccMstr = obj.writeValueAsString(testam);
        }
        catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return jsonStrAccMstr;

    }

    @GetMapping(value="/getjson/listaccountvo")
    public String getAccountVOJson(){
        logger.debug("Getting JSON object for listaccountvo");
//        To get JSON format of the Client Master
        String jsonStrAccMstr = new String();
        try {
            List<AccountVO> testam = new ArrayList<AccountVO>(2);
            AccountVO test1 = new AccountVO();
            AccountVO test2 = new AccountVO();
            testam.add(test1);
            testam.add(test2);
            ObjectMapper obj = new ObjectMapper();
            jsonStrAccMstr = obj.writeValueAsString(testam);
        }
        catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return jsonStrAccMstr;

    }

    @GetMapping(value="/getjson/searchClientVOList")
    public String searchClientVOList(){
        logger.debug("Getting JSON object for searchClientVOList");
//        To get JSON format of the Client Master
        String jsonStrAccMstr = new String();
        try {
            List<SearchClientVO> testam = new ArrayList<SearchClientVO>(2);
            SearchClientVO test1 = new SearchClientVO();
            SearchClientVO test2 = new SearchClientVO();
            testam.add(test1);
            testam.add(test2);
            ObjectMapper obj = new ObjectMapper();
            jsonStrAccMstr = obj.writeValueAsString(testam);
        }
        catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return jsonStrAccMstr;

    }

    @GetMapping(value="/getjson/searchAccountVOList")
    public String searchAccountVOList(){
        logger.debug("Getting JSON object for searchAccountVOList");
//        To get JSON format of the Client Master
        String jsonStrAccMstr = new String();
        try {
            List<SearchAccountVO> testam = new ArrayList<SearchAccountVO>(2);
            SearchAccountVO test1 = new SearchAccountVO();
            SearchAccountVO test2 = new SearchAccountVO();
            testam.add(test1);
            testam.add(test2);
            ObjectMapper obj = new ObjectMapper();
            jsonStrAccMstr = obj.writeValueAsString(testam);
        }
        catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return jsonStrAccMstr;

    }

    @PostMapping(value = "/getClient")
    public List<ClientVO> getClient(@RequestBody SearchClientVO searchClientVO){
        logger.debug("Controller calling - ClientMasterService - Get All Clients");
        return clientMasterService.getAllClients(searchClientVO);
    }

    @PostMapping(value = "/getClientWrapper")
    public List<ClientVO> getClientWrapper(@RequestBody List<SearchClientVO> searchClientVOList) throws IOException {
        logger.debug("Controller calling - ClientMasterService - Get All Clients");
        return clientServiceWrapper.getAllClients(searchClientVOList);
    }

    @PostMapping(value = "/getAccount")
    public List<AccountVO> getAccount(@RequestBody List<SearchAccountVO> searchAccountVOList){
        return accountMasterService.getAllAccounts(searchAccountVOList);
    }

    @PostMapping(value = "/createClient")
    public void createClient(@RequestBody List<ClientVO> clientVOList){
        logger.debug("Controller calling - ClientMasterService - Insert Clients");
        clientMasterService.insertClient(clientVOList);
    }

    @PostMapping(value = "/createAccount")
    public void createAccount(@RequestBody List<AccountVO> accountVOList) {
        logger.debug("Controller calling - AccountMasterService - Insert Accounts and Relationship");
        accountMasterService.insertAccount(accountVOList);
    }
}
