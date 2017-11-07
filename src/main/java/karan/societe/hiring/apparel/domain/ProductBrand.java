package karan.societe.hiring.apparel.domain;

import java.math.BigDecimal;

public class ProductBrand {
	
	private String id;
    private String name;
    private BigDecimal discount = BigDecimal.ZERO;
    
    public ProductBrand() {}
    public ProductBrand(String name) {
        this.name = name.trim();
    }

    public String getName() {
        return this.name;
    }

    public static ProductBrand valueOf(String name) {
        return new ProductBrand(name);
    }

    @Override
    public String toString() {
        return "ProductBrand [name=" + this.name + "]";
    }

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductBrand other = (ProductBrand) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	

}
