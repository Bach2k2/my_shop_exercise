package com.example.my_shop_exercise.model.bo;

import com.example.my_shop_exercise.model.dao.LoginDAO;

public class LoginBO {
    private LoginDAO loginDAO;
    public LoginBO(){
        loginDAO=new LoginDAO();
    }
    public boolean isLogin(String username,String password)
    {
        return loginDAO.checkIsUser(username, password);
    }
}
