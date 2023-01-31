package models;

import Rabbit.Producer;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.jpa.HibernateQuery;

import javax.persistence.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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

    private static Set<Users> users_list;



    public static Set<Users> allUsers(){

        return users_list;
    }


    public static Users findById(Integer id){
        for(Users user : users_list){
            if(user.id.equals(id)){
                return user;
            }
        }
        return null;
    }


    public static void add(String user) throws IOException, TimeoutException {

        Producer.setUserData(user);
        Producer.save();
    }


    public static boolean remove(Users user){
        return users_list.remove(user);
    }



}
