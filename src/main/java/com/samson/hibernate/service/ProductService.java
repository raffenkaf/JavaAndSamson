package com.samson.hibernate.service;

import java.util.List;
import com.samson.hibernate.model.HibernateProduct;

public interface ProductService {
    HibernateProduct findProductById(int id);
    
    void saveProduct(HibernateProduct product);
     
    void deleteProductById(int Id);
     
    List<HibernateProduct> findAllProducts();
}
