package karan.societe.hiring.apparel.dao;

import java.util.List;

import karan.societe.hiring.apparel.domain.ProductCategory;

public interface ProductCategoryDao {
	
    ProductCategory getCategory(String source);
    void saveCategory(ProductCategory category);
    List<ProductCategory> getAllCategoies();
}
