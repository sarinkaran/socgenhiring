package karan.societe.hiring.apparel.service;

import java.util.Collections;
import java.util.Set;

import karan.societe.hiring.apparel.domain.Discount;

public class MaxDiscountSelector implements DiscountSelector{
    @Override
    public Discount selectDiscount(Set<Discount> discounts) {
        return Collections.max(discounts);
    }
}
