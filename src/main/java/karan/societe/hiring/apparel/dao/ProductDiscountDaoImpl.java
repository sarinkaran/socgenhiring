package karan.societe.hiring.apparel.dao;


import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import karan.societe.hiring.apparel.domain.Discount;
import karan.societe.hiring.apparel.domain.ProductBrand;
import karan.societe.hiring.apparel.domain.ProductCategory;

@Component
public class ProductDiscountDaoImpl implements ProductDiscountDao {
    private Map<ProductBrand, Discount> discountByBrands = new ConcurrentHashMap<>();
    private Map<ProductCategory, Discount> discountByCategories = new ConcurrentHashMap<>();
    private final ProductBrandDao productBrandDao;
    private final ProductCategoryDao productCategoryDao;
    
    public ProductDiscountDaoImpl(ProductBrandDao productBrandDao, ProductCategoryDao productCategoryDao) {
    	this.productBrandDao  =  productBrandDao;
    	this.productCategoryDao =  productCategoryDao;
    }
    
    @PostConstruct
    public void init() {
       productBrandDao.getAllBrands().forEach(b->{
    	   Discount discount =  new Discount(b.getDiscount());
    	   discountByBrands.put(b, discount);
       });
       
       productCategoryDao.getAllCategoies().forEach(category->{
    	   Discount disCat =  new Discount(category.getDiscount());
    	   discountByCategories.put(category, disCat);
       });
    }

    @Override
    public Optional<Discount> findByProductBrand(ProductBrand brand) {
    	
        return Optional.ofNullable(discountByBrands.get(brand));
    }

    @Override
    public Optional<Discount> findByProductAncestorCategory(ProductCategory category) {
    	Discount maxDiscount = discountByCategories.get(category); 
        ProductCategory parentCategory =  category.getParent();
        while(parentCategory != null && discountByCategories.get(parentCategory) != null) {
    		if(maxDiscount.compareTo(discountByCategories.get(parentCategory)) <1 )  {
    			maxDiscount =  discountByCategories.get(parentCategory);
    		}
    		parentCategory =  parentCategory.getParent();
    	}
        return Optional.of(maxDiscount);
    }
}
