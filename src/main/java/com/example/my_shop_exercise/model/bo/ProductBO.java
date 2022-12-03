package com.example.my_shop_exercise.model.bo;

import com.example.my_shop_exercise.model.dao.ProductDao;
import com.example.my_shop_exercise.model.entity.CategoryEntity;
import com.example.my_shop_exercise.model.entity.ProductEntity;

import java.util.List;

public class ProductBO {
    private ProductDao productDao;

    public ProductBO() {
        productDao = new ProductDao();
    }

    public List<ProductEntity> getAllProduct() {
        return productDao.getAllProducts();
    }

    public ProductEntity getProductById(String pId) {
        return productDao.getProductById(pId);
    }

    public List<CategoryEntity> getAllCategory() {
        return productDao.getAllCategory();
    }

    public boolean addProduct(ProductEntity product) {
        return productDao.addProduct(product);
    }

    public boolean updateProduct(ProductEntity product) {
        return productDao.updateProduct(product);
    }

    public boolean deleteProduct(String pId) {
        return productDao.deleteProduct(pId);
    }

    public List<ProductEntity> searchProductsByName(String name) {
        return productDao.searchProductsByName(name);
    }

}
