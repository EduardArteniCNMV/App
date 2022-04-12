package com.grup.proiect.controllers;

import com.google.gson.Gson;
import com.grup.proiect.DataBaseConnector;
import com.grup.proiect.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Controller()
public class UserController {
    @CrossOrigin(origins = "http://l317:3000/")
    @RequestMapping(value = "/user/get", params = "id", method = RequestMethod.GET)
    @ResponseBody
    public String getUser(@RequestParam("id") int id) throws SQLException {
        User returnedUser = new User();
        String name = "";
        returnedUser.id = id;
        String query = "SELECT id, name FROM public.\"Users\" where \"id\" = ?;";
        PreparedStatement ps = DataBaseConnector.connection.prepareStatement(query);
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            name = rs.getString(2);
        }
        returnedUser.name = name;
        String jsonRep = new Gson().toJson(returnedUser);
        return jsonRep;
    }
    @CrossOrigin(origins = "http://l317:3000/")
    @RequestMapping(value = "/user/create", params = "name", method = RequestMethod.GET)
    @ResponseBody
    public String createUser(@RequestParam("name") String name) throws SQLException {

        int id = 0;
        User createdUser = new User();
        String query = "INSERT INTO public.\"Users\"(name) VALUES (?) returning \"id\";";
        PreparedStatement ps = DataBaseConnector.connection.prepareStatement(query);
        ps.setString(1,name);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            id = rs.getInt("id");
        }
        createdUser.id = id;
        createdUser.name = name;
        String jsonRep = new Gson().toJson(createdUser);
        return jsonRep;
    }
}
