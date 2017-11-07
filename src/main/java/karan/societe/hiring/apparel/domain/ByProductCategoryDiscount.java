package karan.societe.hiring.apparel.domain;

import java.math.BigDecimal;

public class ByProductCategoryDiscount extends Discount {
    private ProductCategory category;

    public ByProductCategoryDiscount(ProductCategory category, BigDecimal discount) {
        super(discount);
        this.category = category;
    }

    public ProductCategory getCategory() {
        return this.category;
    }

    @Override
    public String toString() {
        return "ByProductCategoryDiscount [category=" + this.category + ", discount=" + this.discount + "]";
    }

}
