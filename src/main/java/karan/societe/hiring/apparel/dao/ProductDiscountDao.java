package karan.societe.hiring.apparel.dao;

import java.util.Optional;

import karan.societe.hiring.apparel.domain.Discount;
import karan.societe.hiring.apparel.domain.ProductBrand;
import karan.societe.hiring.apparel.domain.ProductCategory;

public interface ProductDiscountDao {
    Optional<Discount> findByProductBrand(ProductBrand brand);

    /**
     * If discount is defined for product category it will be return otherwise
     * it will check the ancestor hierarchy
     */
    Optional<Discount> findByProductAncestorCategory(ProductCategory category);
    
}
