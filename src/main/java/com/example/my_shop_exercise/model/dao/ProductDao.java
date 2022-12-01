package com.example.my_shop_exercise.model.dao;

import com.example.my_shop_exercise.model.entity.CategoryEntity;
import com.example.my_shop_exercise.model.entity.ProductEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    private Connection conn;
    private static String url = "jdbc:mysql://localhost:3306/my_shop?useSSL=false";
    private static String username = "root";
    private static String password = "";

    public ProductDao() {
        this.conn = getConnect();
    }

    public Connection getConnect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ProductEntity> getAllProducts() {
        List<ProductEntity> products = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM product");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String pId = rs.getString("product_id");
                String name = rs.getString("name");
                String cId = rs.getString("category_id");
                String image = rs.getString("image");
                Double price = rs.getDouble("price");
                ProductEntity product = new ProductEntity(pId, name, cId, image, price);
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<CategoryEntity> getAllCategory() {
        List<CategoryEntity> categories = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM category");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String cId = rs.getString("category_id");
                String name = rs.getString("name");
                CategoryEntity category = new CategoryEntity(cId, name);
                categories.add(category);
            }
            return categories;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public ProductEntity getProductById(String pId) {
        ProductEntity product = null;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM product WHERE product_id='" + pId + "'");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String cId = rs.getString("category_id");
                String image = rs.getString("image");
                Double price = rs.getDouble("price");
                product = new ProductEntity(pId, name, cId, image, price);

            }
            return product;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean addProduct(ProductEntity product) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO product(product_id,name,price,category_id,image,description) VALUES (?,?,?,?,?,?)");
            preparedStatement.setString(1, product.getpId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setString(4, product.getcId());
            preparedStatement.setString(5, product.getImage());
            preparedStatement.setString(6, product.getDescription());
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean updateProduct(ProductEntity product) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE product set name=?,price=?,category_id=?,image=?,description=? WHERE product_id=?");
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setString(3, product.getcId());
            preparedStatement.setString(4, product.getImage());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setString(6, product.getpId());
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteProduct(String pId) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("DELETE from product WHERE product_id=?");
            preparedStatement.setString(1, pId);
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
