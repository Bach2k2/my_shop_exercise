package com.example.my_shop_exercise.controller;

import com.example.my_shop_exercise.model.bo.LoginBO;
import com.example.my_shop_exercise.model.bo.ProductBO;
import com.example.my_shop_exercise.model.entity.CategoryEntity;
import com.example.my_shop_exercise.model.entity.ProductEntity;
import com.google.protobuf.TextFormat;


import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;

@MultipartConfig
@WebServlet(name = "ProductServlet", urlPatterns = {"/home", "/"})
public class ProductServlet extends HttpServlet {
    ProductBO productBO = new ProductBO();
    private LoginBO loginBo = new LoginBO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String username = null, password = null;
        if (session.getAttribute("username") != null) {
            username = session.getAttribute("username").toString();
        }
        if (session.getAttribute("password") != null) {
            password = session.getAttribute("password").toString();
        }
        System.out.println("username= " + username);
        String action = null;
        if (loginBo.isLogin(username, password)) {
            showHome(request, response);
        } else {
            // response.sendRedirect("/login.jsp");
            showLoginForm(request, response);
        }

        action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "get":
                showProductList(request, response);
                break;
            case "add":
                showInsertForm(request, response);
                break;
            case "update":
                showUpdateForm(request, response);
                break;
            case "delete":
                deleteProduct(request, response);
                break;
            case "login":
                showLoginForm(request, response);
                break;
            case "logout":
                logout(request, response);
                break;
            case "search":
                showSearchForm(request, response);
                break;

            default:
                showHome(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        System.out.println(action);
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "add":
                addProduct(request, response);
                break;
            case "update":
                updateProduct(request, response);
                break;

            default:
                showHome(request, response);
        }
    }

    public void showProductList(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        List<ProductEntity> pList = productBO.getAllProduct();
        request.setAttribute("products", pList);
        request.getRequestDispatcher("/product_list.jsp").forward(request, response);
    }

    public void showInsertForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<CategoryEntity> cList = productBO.getAllCategory();
        request.setAttribute("cList", cList);
        request.getRequestDispatcher("/add_product.jsp").forward(request, response);
    }

    public void showUpdateForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String pId = request.getParameter("pId");
        ProductEntity product = productBO.getProductById(pId);
        request.setAttribute("pId", pId);
        request.setAttribute("name", product.getName());
        request.setAttribute("thisId", product.getcId());
        request.setAttribute("price", product.getPrice());
        request.setAttribute("description", product.getDescription());
        request.setAttribute("image", product.getImage());
        List<CategoryEntity> cList = productBO.getAllCategory();
        request.setAttribute("cList", cList);
        request.getRequestDispatcher("/update_product.jsp").forward(request, response);
    }

    public void showSearchForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, TextFormat.ParseException {
//        String pId = request.getParameter("pId");
//        ProductEntity product = productBO.getProductById(pId);
//        request.setAttribute("pId", pId);
//        request.setAttribute("name", product.getName());
//        request.setAttribute("thisId", product.getcId());
//        request.setAttribute("price", product.getPrice());
//        request.setAttribute("description", product.getDescription());
//        request.setAttribute("image", product.getImage());
//        List<CategoryEntity> cList = productBO.getAllCategory();
//        request.setAttribute("cList", cList);
//        request.getRequestDispatcher("/update_product.jsp").forward(request, response);
        response.sendRedirect("/search.jsp");

    }


    public void addProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String pId = request.getParameter("pId");
        String name = request.getParameter("name");
        Double price = Double.parseDouble(request.getParameter("price"));
        String description = request.getParameter("description");
        String cId = request.getParameter("cId");

        String uploadFolder = request.getServletContext().getRealPath("/uploads");
        Path uploadPath = Paths.get(uploadFolder);
        if (!Files.exists(uploadPath)) {
            Files.createDirectory(uploadPath);
        }
        Part imagePart = request.getPart("image");
        String imageFilename = imagePart.getSubmittedFileName().toString();

        imagePart.write(Paths.get(uploadPath.toString(), imageFilename.toString()).toString());
        String imagePath = "/uploads/" + imageFilename;

        ProductEntity product = new ProductEntity(pId, name, cId, imagePath, price, description);
        productBO.addProduct(product);
        response.sendRedirect("/home");
    }

    public void updateProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String pId = request.getParameter("pId");
        String name = request.getParameter("name");
        Double price = Double.parseDouble(request.getParameter("price"));
        String description = request.getParameter("description");
        String cId = request.getParameter("cId");

//        String uploadFolder=request.getServletContext().getRealPath("/uploads");
//        Path uploadPath= Paths.get(uploadFolder);
//        if(!Files.exists(uploadPath))
//        {
//            Files.createDirectory(uploadPath);
//        }
//        Part imagePart=request.getPart("image");
//        String imageFilename=imagePart.getSubmittedFileName().toString();
//
//        imagePart.write(Paths.get(uploadPath.toString(),imageFilename.toString()).toString());
//        String imagePath="/uploads/"+imageFilename;

        ProductEntity product = new ProductEntity(pId, name, cId, price, description);
        productBO.updateProduct(product);
        response.sendRedirect("/home");
    }

    public void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String pId = request.getParameter("pId");
        productBO.deleteProduct(pId);
        response.sendRedirect("/home");

    }

    public void showHome(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<CategoryEntity> cList = productBO.getAllCategory();
        List<ProductEntity> pList = productBO.getAllProduct();
        request.setAttribute("pList", pList);
        request.setAttribute("p", pList.get(pList.size() - 1));
        request.setAttribute("cList", cList);
        request.getRequestDispatcher("/home.jsp").forward(request, response);
        // response.sendRedirect("/home.jsp");
        //  RequestDispatcher requestDispatcher= getServletContext()LoginFormgetRequestDispatcher("");
    }

    public void showLoginForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
//        session.invalidate();
        session.setAttribute("username",null);
        session.setAttribute("password",null);
        response.sendRedirect("/home");
        return; // <--- Here.
    }
}
