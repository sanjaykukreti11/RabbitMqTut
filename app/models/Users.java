package models;

import Rabbit.Producer;

import javax.persistence.*;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeoutException;

@Entity
public class Users {

    @Id
    public Integer id;
    public String firstname;
    public String lastname;
    public Integer age;

    public Users(){}


    public  Users(Integer id,String first_name,String last_name,Integer age){
        this.id = id;
        this.firstname = first_name;
        this.lastname = last_name;
        this.age = age;

    }

    private static Set<Users> users_list ;





    public static Set<Users> allUsers(){

        return users_list;
    }


    public static Users findById(Integer id){
        try {
            for(Users user : users_list){
                if(user.id.equals(id)){
                    return user;
                }
            }
        }catch (Exception e){

        }

        return null;
    }


    public static void add(String user) throws IOException, TimeoutException {

        Producer.setUserData(user);
        Producer.save();
    }




}
