package com.learning.imst.ist.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.imst.ist.Model.ClientVO;
import com.learning.imst.ist.Model.SearchClientVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


@Service
public class ClientServiceWrapperImpl implements ClientServiceWrapper{

    final static Logger logger = LoggerFactory.getLogger(ClientServiceWrapperImpl.class);

    @Override
    public List<ClientVO> getAllClients(List<SearchClientVO> searchClientVOList) throws IOException {
        URL url = new URL("http://localhost:8080/v1/getClient");
        logger.debug("Calling Rest API - " + url);
        List<ClientVO> clientVOList = new ArrayList<ClientVO>(0);

        for (SearchClientVO searchClientVO: searchClientVOList) {
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Accept","application/json");
            httpURLConnection.setRequestProperty("Content-Type","application/json");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            String searchClientVoJsonString = new String();
            try {
                ObjectMapper obj = new ObjectMapper();
                searchClientVoJsonString = obj.writeValueAsString(searchClientVO);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            try (OutputStream outputStream = httpURLConnection.getOutputStream()) {
                byte[] input = searchClientVoJsonString.getBytes("utf-8");
                outputStream.write(input, 0, input.length);
                outputStream.flush();
            }
            logger.debug("Calling Rest API for Client ID - " + searchClientVO.getClient_id() + " and Client Name - " + searchClientVO.getClient_name());
            if (httpURLConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + httpURLConnection.getResponseCode());
            }
            InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream(), "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = bufferedReader.readLine()) != null) {
                response.append(inputLine);
            }
            bufferedReader.close();
            ObjectMapper obj = new ObjectMapper();
            List<ClientVO> clientVOListTemp = obj.readValue(response.toString(),new TypeReference<List<ClientVO>>(){});
            for(ClientVO clientVO: clientVOListTemp){
                clientVOList.add(clientVO);
            }
            httpURLConnection.disconnect();
        }
        return clientVOList;
    }
}
