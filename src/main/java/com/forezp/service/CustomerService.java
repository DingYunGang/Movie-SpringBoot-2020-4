package com.forezp.service;

import com.forezp.dao.CustomerMapper;
import com.forezp.entity.Address;
import com.forezp.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fangzhipeng on 2017/4/20.
 */
@Service
public class CustomerService {
    @Autowired
    private CustomerMapper customerMapper;


    public List<Customer> findCostomer(String firstName, String lastName){
        return customerMapper.Login(firstName,lastName);
    }
    public List<Customer> findAllList() {
        return customerMapper.FindAllList();
    }

    public List<Address> getAdd() {
        return customerMapper.FindAddress();
    }

    public int insert(String firstName, String lastName, String email, int addId) {
        return customerMapper.insert(firstName,lastName,email,addId);
    }

    public int UpdateConstomer(String firstName, String lastName, String email, int addId, int cutId) {
        return customerMapper.updateConstomer(firstName,lastName,email,addId,cutId);
    }

    public int deleteCustomer(Integer id) {
       return customerMapper.deleteCustomer(id);
    }
}