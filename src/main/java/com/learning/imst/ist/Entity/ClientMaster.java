package com.learning.imst.ist.Entity;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="client_master")

public class ClientMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @SequenceGenerator(name = "sequence_gen", initialValue = 1 , allocationSize = 100000 )
    @Column(name="client_id", unique = true)
    Integer client_id;

    @Column(name = "client_name")
    String client_name;

    @Column(name = "client_address")
    String client_address;

    @Column(name = "create_ts")
    Timestamp create_ts;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "clients")
    Set<AccountMaster> accounts = new HashSet<>(0);

    public void setClient_id(Integer client_id){
        this.client_id=client_id;
    }
    public Integer getClient_id(){
        return client_id;
    }

    public void setClient_name(String client_name){
        this.client_name=client_name;
    }
    public String getClient_name(){
        return client_name;
    }

    public void setClient_address(String client_address){
        this.client_address=client_address;
    }
    public String getClient_address(){
        return client_address;
    }

    public void setCreate_ts(Timestamp create_ts){
        this.create_ts=create_ts;
    }
    public Timestamp getCreate_ts(){
        return create_ts;
    }

    public void setAccounts(Set<AccountMaster> accounts){
        this.accounts=accounts;
    }
    public Set<AccountMaster> getAccounts(){
        return accounts;
    }

}
