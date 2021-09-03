/**
 * Author: Mattias Lindell
 * Last edit: 18-03-12
 * Desc: Receipt with items
 */
package cashregister;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Receipt {
    @XmlElement
    private int id;
    @XmlElement
    private String time;
    @XmlElement(name="item")
    private ArrayList<Item> items;
    
    public Receipt() {
//        id = 0;
        time = new String();
        items = new ArrayList<Item>();
    }
    
    public Receipt(ArrayList<Item> items) {
        this.items = items;
        setTime();
    }
    
    public void addItem(Item item) { items.add(item); }
//    public void removeLastItem() { if (!items.isEmpty()) items.remove(items.size() - 1); }
    public void setTime() { 
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        time = now.format(formatter);
    }
    public String getTime() { return time; }
    public void setId(int id) { this.id = id; }
    public int getId() { return id; }
    public int countItems() { return items.size(); }
    public ArrayList<Item> getItems() { return items; }
    public Item getItemAt(int index) {
        if (index >= 0 && index < countItems() ) {
            return items.get(index);
        } else {
            return null;
        }
    }
    public BigDecimal getSum() {
        BigDecimal sum = new BigDecimal(0);
        for (int i = 0; i < items.size(); i++) {
            sum = sum.add(items.get(i).getDiscountedPrice());
        }
        return sum;
    }
    
    public boolean removeItemAt(int index) {
        if (index < items.size()) {
            items.remove(index);
            return true;
        }        
        return false;
    }
    public void clear() {
        time = new String();
        items = new ArrayList<Item>();
    }
}
