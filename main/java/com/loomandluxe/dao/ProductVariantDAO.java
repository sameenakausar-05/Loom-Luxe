package com.loomandluxe.dao;

import java.util.List;

import com.loomandluxe.model.ProductVariant;

public interface ProductVariantDAO {

    boolean addVariant(ProductVariant variant);

    boolean updateVariant(ProductVariant variant);

    boolean deleteVariant(int variantId);

    ProductVariant getVariantById(int variantId);

    ProductVariant getVariantByProductAndSize(int productId, String size);

    List<ProductVariant> getVariantsByProductId(int productId);

    List<ProductVariant> getAllVariants();

    boolean updateStock(int variantId, int quantity);

    boolean isVariantExists(int productId, String size);

    boolean isStockAvailable(int variantId, int requiredQuantity);

	List<ProductVariant> getVariantsByProduct(int productId);
}