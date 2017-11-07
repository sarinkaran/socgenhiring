package karan.societe.hiring.apparel.domain;

import java.math.BigDecimal;

public class ByBrandDiscount extends Discount {

    private ProductBrand brand;

    public ByBrandDiscount(ProductBrand brand, BigDecimal discount) {
        super(discount);
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "ByBrandDiscount [brand=" + this.brand + ", discount=" + this.discount + "]";
    }

}
