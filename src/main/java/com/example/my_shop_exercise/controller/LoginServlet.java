package com.example.my_shop_exercise.controller;

import com.example.my_shop_exercise.model.bo.LoginBO;
import com.mysql.cj.Session;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet",urlPatterns = "/login") // Nếu urlPattern available thì no need value
public class LoginServlet extends HttpServlet {
    private LoginBO loginBo=new LoginBO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session= request.getSession();
        String username=null,password=null;
        if(session.getAttribute("username")!= null ){
            username=session.getAttribute("username").toString();
        } if(session.getAttribute("password")!= null ){
            password=session.getAttribute("password").toString();
        }
        System.out.println("username= "+username);
        if(loginBo.isLogin(username,password))
        {
            response.sendRedirect("/home");
        }else{
            response.sendRedirect("/login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        System.out.println("Đăng nhập :"+username);
        if(loginBo.isLogin(username,password))
        {
            HttpSession session= request.getSession();
            session.setAttribute("username",username);
            session.setAttribute("password",password);
            request.setAttribute("myUser",username);
            request.getRequestDispatcher("/home").forward(request,response);
        }
        else{
            request.setAttribute("message","Tai khoan hoac mat khau sai");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }
}
