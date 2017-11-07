package karan.societe.hiring.apparel.domain;

import java.math.BigDecimal;

public class Product {
    private Integer id;
    private String name;
    private ProductBrand brand;
    private ProductCategory category;
    private BigDecimal price;

    public Product(Integer id, String name, ProductBrand brand, ProductCategory category, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.category = category;
        this.price = price;
    }

    public Integer getId() {
        return this.id;
    }

    public ProductBrand getBrand() {
        return this.brand;
    }

    public ProductCategory getCategory() {
        return this.category;
    }

    public String getName() {
        return this.name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product [id=" + this.id + ", name=" + this.name + ", brand=" + this.brand + ", category=" + this.category + "]";
    }
}
