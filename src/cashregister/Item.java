/**
 * Author: Mattias Lindell
 * Last edit: 18-03-12
 * Desc: Items on a receipt
 */
package cashregister;

import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Item {
    @XmlElement
    private String seller;
    @XmlElement
    private BigDecimal price;
    @XmlElement
    private BigDecimal discount;
    @XmlElement
    private BigDecimal discountedPrice;
   
    public Item() {
        this("", new BigDecimal(0), new BigDecimal(0));
    }
    
    public Item(String seller, BigDecimal price) {
        this(seller, price, new BigDecimal(0));       
    }
    
    public Item(String seller, BigDecimal price, BigDecimal discount) {
        this.seller = seller;
        this.price = price;
        this.discount = discount;
        setDiscountedPrice();
    }
    
    private void setDiscountedPrice() { 
        discountedPrice = price.subtract(price.multiply(discount.divide(new BigDecimal(100))));
        
        // Round to two decimals
        discountedPrice = discountedPrice.setScale(2, RoundingMode.HALF_UP);

    }
    
    public String getSeller() { return seller; }
    public BigDecimal getPrice() { return price; }
    public BigDecimal getDiscount() { return discount; }
    public BigDecimal getDiscountedPrice() { return discountedPrice; }    
}
