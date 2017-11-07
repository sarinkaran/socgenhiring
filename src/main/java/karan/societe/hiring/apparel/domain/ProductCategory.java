package karan.societe.hiring.apparel.domain;

import java.math.BigDecimal;

public class ProductCategory {
   
    private String name;
    private String id;
    private ProductCategory parent;
    private BigDecimal discount = BigDecimal.ZERO;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ProductCategory getParent() {
        return this.parent;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getDiscount() {
        return this.discount;
    }

	public void setName(String name) {
		this.name = name.trim();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		ProductCategory other = (ProductCategory) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
    
	
	
    
}
