package com.loomandluxe.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.loomandluxe.dao.ProductDAO;
import com.loomandluxe.model.Product;
import com.loomandluxe.util.DBConnection;

public class ProductDAOImpl implements ProductDAO {

    @Override
    public boolean addProduct(Product product) {

        String query = "INSERT INTO products "
                + "(category_id, product_name, description, brand, color, "
                + "price, image_url, is_active) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, product.getCategoryId());
            preparedStatement.setString(2, product.getProductName());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setString(4, product.getBrand());
            preparedStatement.setString(5, product.getColor());
            preparedStatement.setBigDecimal(6, product.getPrice());
            preparedStatement.setString(7, product.getImageUrl());
            preparedStatement.setBoolean(8, product.isActive());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updateProduct(Product product) {

        String query = "UPDATE products SET "
                + "category_id = ?, "
                + "product_name = ?, "
                + "description = ?, "
                + "brand = ?, "
                + "color = ?, "
                + "price = ?, "
                + "image_url = ?, "
                + "is_active = ? "
                + "WHERE product_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, product.getCategoryId());
            preparedStatement.setString(2, product.getProductName());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setString(4, product.getBrand());
            preparedStatement.setString(5, product.getColor());
            preparedStatement.setBigDecimal(6, product.getPrice());
            preparedStatement.setString(7, product.getImageUrl());
            preparedStatement.setBoolean(8, product.isActive());
            preparedStatement.setInt(9, product.getProductId());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteProduct(int productId) {

        String query = "DELETE FROM products WHERE product_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, productId);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Product getProductById(int productId) {

        String query = "SELECT * FROM products WHERE product_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, productId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                Product product = mapResultSetToProduct(resultSet);

                return product;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Product> getAllProducts() {

        List<Product> products = new ArrayList<>();

        String query = "SELECT * FROM products";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

        	while(resultSet.next()) {

        	    Product product = new Product();

        	    product.setProductId(
        	            resultSet.getInt("product_id"));

        	    product.setProductName(
        	            resultSet.getString("product_name"));

        	    product.setDescription(
        	            resultSet.getString("description"));

        	    // IMPORTANT FIX

        	    product.setPrice(
        	            resultSet.getBigDecimal("price"));

        	    product.setImageUrl(
        	            resultSet.getString("image_url"));

        	    product.setBrand(
        	            resultSet.getString("brand"));

        	    products.add(product);
        	}

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public List<Product> getProductsByCategory(int categoryId) {

        List<Product> products =
                new ArrayList<>();

        String query =
                "SELECT * FROM products WHERE category_id = ?";

        try(Connection connection =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    connection.prepareStatement(query)) {

            ps.setInt(1, categoryId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                Product product = new Product();

                product.setProductId(
                        rs.getInt("product_id"));

                product.setProductName(
                        rs.getString("product_name"));

                product.setDescription(
                        rs.getString("description"));

                product.setImageUrl(
                        rs.getString("image_url"));

                product.setCategoryId(
                        rs.getInt("category_id"));

                product.setBrand(
                        rs.getString("brand"));

                product.setActive(
                        rs.getBoolean("is_active"));

                products.add(product);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return products;
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {

        List<Product> products = new ArrayList<>();

        String query = "SELECT * FROM products WHERE brand = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, brand);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                products.add(mapResultSetToProduct(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public List<Product> getProductsByColor(String color) {

        List<Product> products = new ArrayList<>();

        String query = "SELECT * FROM products WHERE color = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, color);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                products.add(mapResultSetToProduct(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public List<Product> searchProducts(String keyword) {

        List<Product> products = new ArrayList<>();

        String query = "SELECT * FROM products "
                + "WHERE product_name LIKE ? "
                + "OR brand LIKE ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            String searchKeyword = "%" + keyword + "%";

            preparedStatement.setString(1, searchKeyword);
            preparedStatement.setString(2, searchKeyword);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                products.add(mapResultSetToProduct(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public List<Product> filterProducts(
            Integer categoryId,
            String brand,
            String color,
            BigDecimal minPrice,
            BigDecimal maxPrice) {

        List<Product> products = new ArrayList<>();

        StringBuilder query = new StringBuilder(
                "SELECT * FROM products WHERE 1=1");

        List<Object> parameters = new ArrayList<>();

        if (categoryId != null) {
            query.append(" AND category_id = ?");
            parameters.add(categoryId);
        }

        if (brand != null && !brand.isEmpty()) {
            query.append(" AND brand = ?");
            parameters.add(brand);
        }

        if (color != null && !color.isEmpty()) {
            query.append(" AND color = ?");
            parameters.add(color);
        }

        if (minPrice != null) {
            query.append(" AND price >= ?");
            parameters.add(minPrice);
        }

        if (maxPrice != null) {
            query.append(" AND price <= ?");
            parameters.add(maxPrice);
        }

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(query.toString())) {

            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setObject(i + 1, parameters.get(i));
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                products.add(mapResultSetToProduct(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public List<Product> getLatestProducts() {

        List<Product> products = new ArrayList<>();

        String query = "SELECT * FROM products "
                + "ORDER BY created_at DESC LIMIT 10";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {

                products.add(mapResultSetToProduct(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public List<Product> getRelatedProducts(int categoryId, int productId) {

        List<Product> products = new ArrayList<>();

        String query = "SELECT * FROM products "
                + "WHERE category_id = ? "
                + "AND product_id != ? "
                + "LIMIT 8";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, categoryId);
            preparedStatement.setInt(2, productId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                products.add(mapResultSetToProduct(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public boolean updateProductStatus(int productId, boolean isActive) {

        String query = "UPDATE products SET is_active = ? WHERE product_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setBoolean(1, isActive);
            preparedStatement.setInt(2, productId);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private Product mapResultSetToProduct(ResultSet resultSet) throws SQLException {

        Product product = new Product();

        product.setProductId(resultSet.getInt("product_id"));
        product.setCategoryId(resultSet.getInt("category_id"));
        product.setProductName(resultSet.getString("product_name"));
        product.setDescription(resultSet.getString("description"));
        product.setBrand(resultSet.getString("brand"));
        product.setPrice(resultSet.getBigDecimal("price"));
        product.setImageUrl(resultSet.getString("image_url"));
        product.setActive(resultSet.getBoolean("is_active"));
        product.setCreatedAt(resultSet.getTimestamp("created_at"));

        return product;
    }
}