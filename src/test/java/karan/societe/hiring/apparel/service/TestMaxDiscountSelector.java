package karan.societe.hiring.apparel.service;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import karan.societe.hiring.apparel.domain.Discount;

public class TestMaxDiscountSelector {

	@Test
	public void testMaxDiscountIsReturnedWhenCompared() {
		
		MaxDiscountSelector maxDiscountSelector  =  new MaxDiscountSelector();
		Set<Discount> discounts  = new HashSet();
		discounts.add(new Discount(new BigDecimal(50.0)));
		discounts.add(new Discount(new BigDecimal(10.0)));
		discounts.add(new Discount(new BigDecimal(20.0)));
		discounts.add(new Discount(new BigDecimal(60.0)));
		discounts.add(new Discount(new BigDecimal(30.0)));
		Discount max =  maxDiscountSelector.selectDiscount(discounts);
		Assert.assertTrue(max.compareTo(new Discount(new BigDecimal(60.0))) ==0);
		
	}

}
