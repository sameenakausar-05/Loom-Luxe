package com.loomandluxe.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.loomandluxe.dao.ProductVariantDAO;
import com.loomandluxe.model.ProductVariant;
import com.loomandluxe.util.DBConnection;

public class ProductVariantDAOImpl implements ProductVariantDAO {

    @Override
    public boolean addVariant(ProductVariant variant) {

        String query = "INSERT INTO product_variants "
                + "(product_id, size, stock_quantity) "
                + "VALUES (?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, variant.getProductId());
            preparedStatement.setString(2, variant.getSize());
            preparedStatement.setInt(3, variant.getStockQuantity());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updateVariant(ProductVariant variant) {

        String query = "UPDATE product_variants SET "
                + "product_id = ?, "
                + "size = ?, "
                + "stock_quantity = ? "
                + "WHERE variant_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, variant.getProductId());
            preparedStatement.setString(2, variant.getSize());
            preparedStatement.setInt(3, variant.getStockQuantity());
            preparedStatement.setInt(4, variant.getVariantId());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteVariant(int variantId) {

        String query = "DELETE FROM product_variants WHERE variant_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, variantId);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public ProductVariant getVariantById(int variantId) {

        String query = "SELECT * FROM product_variants WHERE variant_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, variantId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                return mapResultSetToVariant(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ProductVariant getVariantByProductAndSize(int productId, String size) {

        String query = "SELECT * FROM product_variants "
                + "WHERE product_id = ? AND size = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, productId);
            preparedStatement.setString(2, size);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                return mapResultSetToVariant(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<ProductVariant> getVariantsByProductId(int productId) {

        List<ProductVariant> variants = new ArrayList<>();

        String query = "SELECT * FROM product_variants WHERE product_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, productId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                variants.add(mapResultSetToVariant(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return variants;
    }
    
    @Override
    public List<ProductVariant> getVariantsByProduct(int productId) {

        List<ProductVariant> variants =
                new ArrayList<>();

        String query =
                "SELECT * FROM product_variants WHERE product_id = ?";

        try(Connection connection =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    connection.prepareStatement(query)) {

            ps.setInt(1, productId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                ProductVariant variant =
                        new ProductVariant();

                variant.setVariantId(
                        rs.getInt("variant_id"));

                variant.setProductId(
                        rs.getInt("product_id"));

                variant.setSize(
                        rs.getString("size"));

                variant.setStockQuantity(
                        rs.getInt("stock_quantity"));

                variants.add(variant);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return variants;
    }
    @Override
    public List<ProductVariant> getAllVariants() {

        List<ProductVariant> variants = new ArrayList<>();

        String query = "SELECT * FROM product_variants";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {

                variants.add(mapResultSetToVariant(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return variants;
    }

    @Override
    public boolean updateStock(int variantId, int quantity) {

        String query = "UPDATE product_variants "
                + "SET stock_quantity = ? "
                + "WHERE variant_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, variantId);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean isVariantExists(int productId, String size) {

        String query = "SELECT COUNT(*) FROM product_variants "
                + "WHERE product_id = ? AND size = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, productId);
            preparedStatement.setString(2, size);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                return resultSet.getInt(1) > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean isStockAvailable(int variantId, int requiredQuantity) {

        String query = "SELECT stock_quantity FROM product_variants "
                + "WHERE variant_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, variantId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                int stockQuantity = resultSet.getInt("stock_quantity");

                return stockQuantity >= requiredQuantity;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private ProductVariant mapResultSetToVariant(ResultSet resultSet)
            throws SQLException {

        ProductVariant variant = new ProductVariant();

        variant.setVariantId(resultSet.getInt("variant_id"));
        variant.setProductId(resultSet.getInt("product_id"));
        variant.setSize(resultSet.getString("size"));
        variant.setStockQuantity(resultSet.getInt("stock_quantity"));

        return variant;
    }
}