package com.group.app.controllers;

import com.google.gson.Gson;
import com.group.app.DataBaseConnector;
import com.group.app.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class UserController {

    @RequestMapping(
            value = "/users",
            params = {"id"},
            method = GET)
    @ResponseBody
    public String getUserById(@RequestParam("id") int id) throws SQLException {

        User foundUser = new User();
        String username = "";
        String password = "";

        String query = "SELECT id, username, password FROM public.\"User\" where \"id\"=?;";
        PreparedStatement ps = DataBaseConnector.connection.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            username = rs.getString("username");
            password = rs.getString("password");
        }
        foundUser.id = id;
        if (username.equals("") && password.equals("")) {
            foundUser.errorCode = 1;
            foundUser.id = 0;
        }
        foundUser.username = username;
        foundUser.password = password;
        String jsonRep = new Gson().toJson(foundUser);

        return jsonRep;
    }

    @RequestMapping(
            value = "/users",
            params = {"username"},
            method = GET)
    @ResponseBody
    public String getUserByUsername(@RequestParam("username") String username) throws SQLException {

        User foundUser = new User();
        int id = 0;
        String password = "";

        String query = "SELECT id, username, password FROM public.\"User\" where \"username\"=?;";
        PreparedStatement ps = DataBaseConnector.connection.prepareStatement(query);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            id = rs.getInt("id");
            password = rs.getString("password");
        }
        foundUser.id = id;
        if (id == 0 && password.equals("")) {
            foundUser.errorCode = 1;
            foundUser.id = 0;
        }
        foundUser.username = username;
        foundUser.password = password;
        String jsonRep = new Gson().toJson(foundUser);

        return jsonRep;
    }

    @RequestMapping(
            value = "/users/login",
            params = {"username", "password"},
            method = GET)
    @ResponseBody
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) throws SQLException {

        User foundUser = new User();
        int id = 0;

        String query = "SELECT id, username, password FROM public.\"User\" where \"username\"=? and \"password\"=?;";
        PreparedStatement ps = DataBaseConnector.connection.prepareStatement(query);
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            id = rs.getInt("id");
        }
        if (id != 0) {
            foundUser.id = id;
            foundUser.username = username;
            foundUser.password = password;
        }else{
            foundUser.errorCode = 1;
        }
        String jsonRep = new Gson().toJson(foundUser);

        return jsonRep;

    }

    @RequestMapping(
            value = "/users/login",
            params = {"id", "password"},
            method = GET)
    @ResponseBody
    public String login(@RequestParam("id") int id, @RequestParam("password") String password) throws SQLException {

        User foundUser = new User();
        String username = "";

        String query = "SELECT id, username, password FROM public.\"User\" where \"id\"=? and \"password\"=?;";
        PreparedStatement ps = DataBaseConnector.connection.prepareStatement(query);
        ps.setInt(1, id);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            username = rs.getString("username");
        }
        if (!username.equals("")) {
            foundUser.id = id;
            foundUser.username = username;
            foundUser.password = password;
        }else{
            foundUser.errorCode = 1;
        }
        String jsonRep = new Gson().toJson(foundUser);

        return jsonRep;

    }


}
