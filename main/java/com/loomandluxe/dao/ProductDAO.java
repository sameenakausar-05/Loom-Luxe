package com.loomandluxe.dao;

import java.math.BigDecimal;
import java.util.List;

import com.loomandluxe.model.Product;

public interface ProductDAO {

    boolean addProduct(Product product);

    boolean updateProduct(Product product);

    boolean deleteProduct(int productId);

    Product getProductById(int productId);

    List<Product> getAllProducts();

    List<Product> getProductsByCategory(int categoryId);

    List<Product> getProductsByBrand(String brand);

    List<Product> getProductsByColor(String color);

    List<Product> searchProducts(String keyword);

    List<Product> filterProducts(
            Integer categoryId,
            String brand,
            String color,
            BigDecimal minPrice,
            BigDecimal maxPrice
    );

    List<Product> getLatestProducts();

    List<Product> getRelatedProducts(int categoryId, int productId);

    boolean updateProductStatus(int productId, boolean isActive);
}