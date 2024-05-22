package com.learning.imst.ist.Entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="account_master")

public class AccountMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="account_number")
    Integer account_number;

    @Column(name="account_name")
    String account_name;

    @Column(name="account_balance")
    Long account_balance;

    @Column(name="account_open_date")
    Date account_open_date;

    @Column(name = "create_ts")
    Timestamp create_ts;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "relation_master",
            joinColumns = {
                    @JoinColumn(name = "account_number", nullable = false, updatable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "client_id", nullable = false, updatable = false)
            }
    )
    Set<ClientMaster> clients = new HashSet<>(0);

    public void setAccount_number(Integer account_number){
        this.account_number=account_number;
    }
    public Integer getAccount_number(){
        return account_number;
    }

    public void setAccount_name(String account_name){
        this.account_name=account_name;
    }
    public String getAccount_name(){
        return account_name;
    }

    public void setAccount_balance(Long account_balance){
        this.account_balance=account_balance;
    }
    public Long getAccount_balance(){
        return account_balance;
    }

    public void setAccount_open_date(Date account_open_date){
        this.account_open_date=account_open_date;
    }
    public Date getAccount_open_date(){
        return account_open_date;
    }

    public void setCreate_ts(Timestamp create_ts){
        this.create_ts=create_ts;
    }
    public Timestamp getCreate_ts(){
        return create_ts;
    }

    public void setClients(Set<ClientMaster> clients){
        this.clients = clients;
    }

    public Set<ClientMaster> getClients(){
        return clients;
    }

    @Override
    public String toString() {
        return "Account Number : " + account_number + " , " +
                "Total Balance : " + account_balance + " , " +
                "Open Date : " + account_open_date + " , " +
                "Create Timestamp : " + create_ts +  " , ";
    }
}
