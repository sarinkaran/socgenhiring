package karan.societe.hiring.apparel.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import karan.societe.hiring.apparel.domain.ProductCategory;

@Component
public class ProductCategoryDaoImpl implements ProductCategoryDao {
    private Map<String, ProductCategory> categories = new ConcurrentHashMap<>();
    
    
    
    public ProductCategoryDaoImpl() {
    }

    @Override
    public ProductCategory getCategory(String ref) {
        return categories.get(ref);
    }

	@Override
	public void saveCategory(ProductCategory category) {
		categories.put(category.getName() , category);
	}

	@Override
	public List<ProductCategory> getAllCategoies() {
		return Collections.unmodifiableList(new ArrayList<>(categories.values()));
	}
}
