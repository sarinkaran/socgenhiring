package karan.societe.hiring.apparel.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import karan.societe.hiring.apparel.domain.ProductBrand;

@Component
public class ProductBrandDaoImpl implements ProductBrandDao {
	
	private Map<String, ProductBrand> brands = new ConcurrentHashMap<>();
     
	@Override
	public ProductBrand getBrand(String brandRef) {
		return brands.get(brandRef);
	}

	@Override
	public void saveBrand(ProductBrand brand) {
		brands.put(brand.getName(), brand);
	}

	@Override
	public List<ProductBrand> getAllBrands() {
		return Collections.unmodifiableList(new ArrayList<>(brands.values()));
	}
}
