package karan.societe.hiring.apparel.service;

import java.util.Set;

import karan.societe.hiring.apparel.domain.Discount;

public interface DiscountSelector {
    /**
     * Return the selected discount from the given set of discounts
     */
    Discount selectDiscount(Set<Discount> discounts);
}
