package karan.societe.hiring.apparel.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import karan.societe.hiring.apparel.dao.ProductDiscountDao;
import karan.societe.hiring.apparel.domain.Discount;
import karan.societe.hiring.apparel.domain.Item;
import karan.societe.hiring.apparel.domain.Product;
import karan.societe.hiring.apparel.domain.ProductBrand;
import karan.societe.hiring.apparel.domain.ProductCategory;

public class TestDiscountEngine {
	
	@Mock
	ProductDiscountDao productDiscountDao;
	@Mock
	DiscountSelector discountSelector;
	
	DiscountEngine discountEngine;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		discountEngine =  new DiscountEngine(productDiscountDao,discountSelector);
	}
	
	@Test
	public void  testApplicableDiscountItemFromBasket() {
		ItemBasket itemBasket =  new ItemBasket();
		Product product  =  createProduct();
		Item  item  =  new Item(product);
		when(productDiscountDao.findByProductAncestorCategory(product.getCategory())).thenReturn(Optional.of(new Discount(new BigDecimal(10.0))));
		//   Optional<Discount> discountByBrand = discountDao.findByProductBrand(brand);
		List<Discount> actuals =  Arrays.asList(new Discount(new BigDecimal(10.0)), new Discount(new BigDecimal(20.0)));
		when(productDiscountDao.findByProductBrand(product.getBrand())).thenReturn(Optional.of(new Discount(new BigDecimal(20.0))));
		Set<Discount> discounts = discountEngine.getApplicableDiscountsOnItem(itemBasket, item);
		verify(productDiscountDao, times(1)).findByProductAncestorCategory(product.getCategory());
		verify(productDiscountDao, times(1)).findByProductBrand(product.getBrand());
		Assert.assertArrayEquals(discounts.toArray(), actuals.toArray());
	}
	
	@Test
	public void testMaxDiscountIsSetInItemObject() {
		ItemBasket itemBasket =  new ItemBasket();
		Product product  =  createProduct();
		Item  item  =  new Item(product);
		when(productDiscountDao.findByProductAncestorCategory(product.getCategory())).thenReturn(Optional.of(new Discount(new BigDecimal(10.0))));
		when(productDiscountDao.findByProductBrand(product.getBrand())).thenReturn(Optional.of(new Discount(new BigDecimal(20.0))));
		Set<Discount> actuals =  new HashSet<>();
		actuals.add(new Discount(new BigDecimal(10.0)));
		actuals.add(new Discount(new BigDecimal(20.0)));
		when(discountSelector.selectDiscount(actuals)).thenReturn(new Discount(new BigDecimal(20.0)));
		discountEngine.applyDiscountOnitem(itemBasket, item);
		Assert.assertTrue(item.getAppliedDiscount().getDiscount().compareTo(new BigDecimal(20.0)) == 0);
	}
	
	private Product createProduct() {
		
		ProductCategory productCategory  =  new ProductCategory();
		productCategory.setName("Footwear");
		productCategory.setDiscount(new BigDecimal(40.0));
		ProductBrand brand =  new ProductBrand("menswear");
		Product p  =  new Product(1, "Provogue", brand, productCategory, new BigDecimal(10.0));
		return p;
	}

}
