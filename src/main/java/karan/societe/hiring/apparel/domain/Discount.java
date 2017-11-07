package karan.societe.hiring.apparel.domain;

import java.math.BigDecimal;

public class Discount implements Comparable<Discount> {
    public static final Discount NUL = new Discount(BigDecimal.ZERO);

    protected BigDecimal discount;

    public Discount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getDiscount() {
        return this.discount;
    }

    public String getDiscountInfo() {
        return "Not Applicable";
    };

    @Override
    public int compareTo(Discount o) {
        return discount.compareTo(o.discount);
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((discount == null) ? 0 : discount.hashCode());
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
		Discount other = (Discount) obj;
		if (discount == null) {
			if (other.discount != null)
				return false;
		} else if (!discount.equals(other.discount))
			return false;
		return true;
	}
    
    
    
}
