package com.vyatsu;

import java.sql.*;

public class Main {
    private static Connection connection;

    private static final String _addres = "jdbc:postgresql://localhost:5432/work100012";
    private static final String _user = "work100012";
    private static final String _pass = "000000";


    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
            if (connection == null)
                connection = DriverManager.getConnection(_addres, _user, _pass);
            insertProduct(new Product("Фоторамка 24/15", 1000.0, "Фоторамки"));
            insertProduct(new Product("Фоторамка 32/28", 2000.0, "Фоторамки"));
            insertProduct(new Product("Штатив 1м", 3000.0, "Штативы"));
            getAllProducts();
            getAllProductsPriceMore(2000.0);
            getAllProductsWithPrice(1000.0, 2000.0);
            updatePriceCategory("Фоторамки", 10);
            getAllProducts();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void insertProduct(Product product) {
        if (product == null) {
            System.out.println("Не верный объект\n");
            return;
        }
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO products VALUES (DEFAULT, ?, ?, ?)");
            statement.setObject(1, product.title);
            statement.setObject(2, product.price);
            statement.setObject(3, product.category);
            statement.executeUpdate();
            System.out.println("Продукт добавлен\n");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void getAllProducts() {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * from products");
            System.out.println("Все продукты:");
            print(rs);
            System.out.println();
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void getAllProductsPriceMore(double price) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM products WHERE price >= ?");
            statement.setObject(1, price);
            ResultSet rs = statement.executeQuery();
            System.out.println("Все продукты больше " + price + ":");
            print(rs);
            System.out.println();
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void getAllProductsWithPrice(double lowPrice, double morePrice) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM products WHERE price >= ? AND price <= ?");
            statement.setObject(1, lowPrice);
            statement.setObject(2, morePrice);
            ResultSet rs = statement.executeQuery();
            System.out.println("Все продукты в помежутке цен " + lowPrice + " и " + morePrice + ":");
            print(rs);
            System.out.println();
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updatePriceCategory(String category, int percent) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM products WHERE category_name=?");
            statement.setObject(1, category);
            ResultSet rs = statement.executeQuery();
            PreparedStatement statement1 = connection.prepareStatement("UPDATE products SET price=? WHERE id=?");
            while (rs.next()){
                Product product = new Product(rs.getInt("id"), rs.getString("title"), rs.getDouble("price"), rs.getString("category_name"));
                statement1.setObject(1, product.price + product.price*(percent/100.0));
                statement1.setObject(2, product.id);
                statement1.addBatch();
            }
            statement1.executeBatch();
            System.out.println("Цены продуктов обновлены\n");
            statement1.close();
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void print(ResultSet rs) throws SQLException {
        if (rs == null){
            System.out.println("Результат не существует");
            return;
        }
        while (rs.next()) {
            Product product = new Product(rs.getInt("id"), rs.getString("title"), rs.getDouble("price"), rs.getString("category_name"));
            System.out.println(product);
        }
    }
}