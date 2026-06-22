package com.loomandluxe.util;

import java.util.List;

import com.loomandluxe.dao.ProductDAO;
import com.loomandluxe.dao.impl.ProductDAOImpl;
import com.loomandluxe.model.Product;

public class TestProductDAO {

    public static void main(String[] args) {

        ProductDAO productDAO = new ProductDAOImpl();

        List<Product> products = productDAO.getAllProducts();

        for (Product product : products) {

            System.out.println("Product ID : "
                    + product.getProductId());

            System.out.println("Product Name : "
                    + product.getProductName());

            System.out.println("Price : "
                    + product.getPrice());

            System.out.println("Brand : "
                    + product.getBrand());

            System.out.println("---------------------------");
        }
    }
}