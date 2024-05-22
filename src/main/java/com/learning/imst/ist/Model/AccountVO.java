package com.learning.imst.ist.Model;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
public class AccountVO {
    Integer client_id;
    Integer account_number;
    String account_name;
    Long account_balance;
    Date account_open_date;
    Timestamp create_ts;
}
