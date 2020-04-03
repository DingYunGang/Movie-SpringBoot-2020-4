package com.forezp.dao;

import com.forezp.entity.Address;
import com.forezp.entity.Customer;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by fangzhipeng on 2017/4/20.
 */
@Mapper
public interface CustomerMapper {


    @Select("select * from customer where first_name=#{firstName} and last_name=#{lastName}")
    List<Customer> Login(@Param("firstName") String firstName, @Param("lastName") String lastName);

    @Select("select * from customer e LEFT JOIN  address d ON e.address_id = d.address_id")
    List<Customer> FindAllList();
    @Select("select * from address")
    List<Address> FindAddress();

    @Insert("insert into customer(store_id, first_name,last_name,email,address_id,active,create_date,last_update) " +
            "values(1, #{firstName},#{lastName},#{email},#{addId},1,NOW(),NOW())")
    int insert(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("email") String email, @Param("addId") int addId);

    @Update("update customer set first_name = #{firstName}, last_name = #{lastName} ,email=#{email},address_id=#{addId} where customer_id = #{cutId}")
    int updateConstomer(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("email") String email, @Param("addId") int addId
    ,@Param("cutId") int cutId);

    @Delete("delete from customer where customer_id = #{id}")
    int deleteCustomer(@Param("id") Integer id);
}