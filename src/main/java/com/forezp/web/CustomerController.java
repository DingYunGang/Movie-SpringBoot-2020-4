package com.forezp.web;

import com.forezp.entity.Address;
import com.forezp.entity.Customer;
import com.forezp.entity.Msg;
import com.forezp.service.CustomerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by fangzhipeng on 2017/4/20.
 */
@RestController
@RequestMapping("/account")
public class CustomerController {


    @Autowired
    CustomerService CustomerService;


    @RequestMapping(value = "/emps",method = RequestMethod.GET)
    @ResponseBody
    public Msg getEmpsWithJson(
            @RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        System.out.println("pn的值 "+pn);
        // 这不是一个分页查询
        // 引入PageHelper分页插件
        // 在查询之前只需要调用，传入页码，以及每页的大小

        PageHelper.startPage(pn,5);
        // startPage后面紧跟的这个查询就是一个分页查询
        List<Customer> emps = CustomerService.findAllList();
        // 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
        // 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
        PageInfo page = new PageInfo(emps, 5);
        return Msg.success().add("pageInfo", page);
    }




    @RequestMapping(value ="/depts",method = RequestMethod.GET)
    @ResponseBody
    public Msg getAdd(){
        List <Address> list =  CustomerService.getAdd();
        return Msg.success().add("depts",list);
    }


    @RequestMapping(value = "/login2", method = RequestMethod.POST)
    public Msg login(
            @RequestParam("firstName")String firstName,
            @RequestParam("lastName")String lastName
    ){
        System.out.println(firstName+"  "+lastName+"60");
        List<Customer> list = CustomerService.findCostomer(firstName,lastName);
        if(list.size()<1){
            //登陆失败
            System.out.print("登陆失败"+firstName+"  "+lastName);
            return Msg.fail();
        }else{
            //登陆成功
            System.out.print("登陆成功: "+list.get(0).getFirstName()+" "+list.get(0).getLastName());
            return Msg.success().add("rigth",list.get(0));
        }
    }


    @RequestMapping(value = "/Save", method = RequestMethod.POST)
    @ResponseBody
    public Msg save(@RequestParam("firstName")String firstName,
                    @RequestParam("lastName")String lastName,
                    @RequestParam("email")String email,
                    @RequestParam("AddDizhi")String AddDizhi
    ){
        //	System.out.print("成功进入保存后端");
        System.out.print(firstName+" "+lastName+" "+email+" "+AddDizhi);
        int AddId = Integer.valueOf(AddDizhi);
        CustomerService.insert(firstName,lastName,email,AddId);
       // customerService.saveEmp(customer);
        return Msg.success();
    }

    @RequestMapping(value = "/gengxi", method = RequestMethod.POST )
    @ResponseBody
    public Msg gengxin(
            @RequestParam("firstName")String firstName,
            @RequestParam("lastName")String lastName,
            @RequestParam("email")String email,
            @RequestParam("UpdateDizhi")String AddDizhi,
            @RequestParam("id")String id
    ){
        System.out.print(firstName+" "+lastName+" "+email+" "+AddDizhi+" "+id);
        int AddId = Integer.valueOf(AddDizhi);
        int CutId  = Integer.valueOf(id);
        CustomerService.UpdateConstomer(firstName,lastName,email,AddId,CutId);
        return Msg.success();
    }

    @ResponseBody
    @RequestMapping(value="/Delete/{ids}", method = RequestMethod.DELETE)
    public Msg deleteEmp(@PathVariable("ids")String ids){

       	    System.out.print("成功进入删除！"+ids);
        Integer id = Integer.parseInt(ids);
        CustomerService.deleteCustomer(id);
        return Msg.success();
    }


}