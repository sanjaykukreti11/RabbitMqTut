package models;

import Rabbit.Producer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.*;
import java.io.IOException;
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

    public static List<Users> allUsers(){

        Configuration con = new Configuration().configure().addAnnotatedClass(Users.class);
        SessionFactory sf = con.buildSessionFactory();
        Session ses = sf.openSession();
        List<Users> usersList = null;
        try {
            usersList = ses.createNativeQuery("Select * from users", Users.class).getResultList();
            return usersList;
        }
        catch (Exception e){
            return usersList;
        }
        finally {
            ses.close();
        }
    }


    public static Users findById(Integer id){
        List<Users> users_list = allUsers();
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
