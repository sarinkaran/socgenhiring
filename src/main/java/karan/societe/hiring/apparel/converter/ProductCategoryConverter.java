package karan.societe.hiring.apparel.converter;

import org.springframework.core.convert.converter.Converter;

import karan.societe.hiring.apparel.dao.ProductCategoryDao;
import karan.societe.hiring.apparel.domain.ProductCategory;

public class ProductCategoryConverter implements Converter<String, ProductCategory> {

    private ProductCategoryDao categoryDao;

    public ProductCategoryConverter(ProductCategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public ProductCategory convert(String source) {
        return categoryDao.getCategory(source);
    }

}
