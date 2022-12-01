package com.example.my_shop_exercise.controller;

import com.example.my_shop_exercise.model.bo.ProductBO;
import com.example.my_shop_exercise.model.entity.CategoryEntity;
import com.example.my_shop_exercise.model.entity.ProductEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@MultipartConfig
@WebServlet(name = "ProductServlet", urlPatterns = {"/home", "/"})
public class ProductServlet extends HttpServlet {
    ProductBO productBO = new ProductBO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        System.out.println(action);
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

    public void showProductList(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
        response.sendRedirect("/login.jsp");
    }
}
