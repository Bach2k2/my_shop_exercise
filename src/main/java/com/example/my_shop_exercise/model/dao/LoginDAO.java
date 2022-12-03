package com.example.my_shop_exercise.model.dao;

import java.sql.*;

public class LoginDAO {

    private Connection conn;
    private static String url = "jdbc:mysql://localhost:3306/my_shop?useSSL=false";
    private static String username = "root";
    private static String password = "";

    public LoginDAO() {
        this.conn = getConnect();
    }

    public Connection getConnect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkIsUser(String username, String password) {
        if(username==null||password==null) return false;
        boolean isExist = false;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM account");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("username").equals(username) && rs.getString("password").equals(password)) {
                    isExist = true;
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isExist;
    }

}
