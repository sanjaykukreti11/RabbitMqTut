package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Users;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.createForm;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import views.html.users;

import javax.inject.Inject;

public class UserController extends Controller {

    @Inject
    FormFactory formFactory;


    public Result  index(){
        List<Users> users_list = Users.allUsers();
        return ok(users.render( users_list ));
    }

    public Result create( ){

        Form<Users> usersForm = formFactory.form(Users.class);

        return ok( createForm.render(usersForm, "") ) ;

    }

    public Result save() throws IOException, TimeoutException {
        Form<Users> userForm = formFactory.form(Users.class).withDirectFieldAccess(true);
        Users userData = userForm.bindFromRequest().get();
        Users user = Users.findById(userData.id);
        if(user!=null){
            return ok(createForm.render(userForm , "User Id Already Exists"));
        }

        ObjectMapper mapper = new ObjectMapper();
        String data = mapper.writeValueAsString(userData);
        Users.add(data);
        return redirect( routes.UserController.index() );
    }


}