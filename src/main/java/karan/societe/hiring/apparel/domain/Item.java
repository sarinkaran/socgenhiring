package karan.societe.hiring.apparel.domain;

import java.math.BigDecimal;

import karan.societe.hiring.apparel.util.Utils;

public class Item {
    private Product product;
    private Discount appliedDiscount;

    public Item(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return this.product;
    }

    public BigDecimal calculatePrice() {
        BigDecimal price = product.getPrice();
        Discount discount = Utils.coalesce(appliedDiscount, Discount.NUL);
        BigDecimal d  =  price.multiply((new BigDecimal(1.0).subtract(discount.getDiscount())));
        return d;
    }

    public Discount getAppliedDiscount() {
        return appliedDiscount;
    }

    public void setAppliedDiscount(Discount appliedDiscount) {
        this.appliedDiscount = appliedDiscount;
    }
}
