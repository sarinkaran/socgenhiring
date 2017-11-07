package karan.societe.hiring.apparel.service;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Component;

import karan.societe.hiring.apparel.dao.ProductDiscountDao;
import karan.societe.hiring.apparel.domain.Discount;
import karan.societe.hiring.apparel.domain.Item;
import karan.societe.hiring.apparel.domain.Product;
import karan.societe.hiring.apparel.domain.ProductBrand;
import karan.societe.hiring.apparel.domain.ProductCategory;

@Component
public class DiscountEngine {
    private final ProductDiscountDao discountDao;
    private DiscountSelector discountSelector;

    public DiscountEngine(ProductDiscountDao discountDao, DiscountSelector discountSelector) {
        this.discountDao = discountDao;
        this.discountSelector = discountSelector;
    }

    public Set<Discount> getApplicableDiscountsOnItem(ItemBasket basket, Item item) {
        // more sophisticated engine implementation shall use some advanced
        // rule-engine based technique to find applicable discounts. for this example, we
        // are doing manually
        Product product = item.getProduct();
        ProductCategory category = product.getCategory();
        ProductBrand brand = product.getBrand();
        Set<Discount> list = new LinkedHashSet<>();
        if(category!= null) {
        	Optional<Discount> discountByCategory = discountDao.findByProductAncestorCategory(category);
        	if (discountByCategory.isPresent()) {
        		list.add(discountByCategory.get());
        	}
        }
        if(brand !=null) {
        	Optional<Discount> discountByBrand = discountDao.findByProductBrand(brand);
        	if (discountByBrand.isPresent()) {
        		list.add(discountByBrand.get());
        	}
        }
        return Collections.unmodifiableSet(list);
    }

    public void applyDiscountOnitem(ItemBasket basket, Item item) {
        Set<Discount> items = getApplicableDiscountsOnItem(basket, item);
        Discount discount = discountSelector.selectDiscount(items);

        item.setAppliedDiscount(discount);
    }
}
