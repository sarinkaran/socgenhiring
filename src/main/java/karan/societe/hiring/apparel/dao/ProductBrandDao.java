package karan.societe.hiring.apparel.dao;

import java.util.List;

import karan.societe.hiring.apparel.domain.ProductBrand;

public interface ProductBrandDao {
	
    ProductBrand getBrand(String brandRef);
    void saveBrand(ProductBrand brand);
    List<ProductBrand> getAllBrands();
   
}
