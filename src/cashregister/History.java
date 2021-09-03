/**
 * Author: Mattias Lindell
 * Last edit: 18-03-12
 * Desc: History stores receipts
 */
package cashregister;

import java.math.BigDecimal;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class History {
    @XmlElement(name="receipt")
    private ArrayList<Receipt> receipts;
    
    public History() { receipts = new ArrayList<>(); }
    
    public void addReceipt(Receipt receipt) {
        receipt.setId(receipts.size());
        receipts.add(receipt); 
    }
    public Receipt removeReceipt(int id) {
        if (id < receipts.size()) {
            Receipt tmp = receipts.get(id);
            receipts.remove(id);
            
            for (int i = 0; i < receipts.size(); i++) {
                receipts.get(i).setId(i);
            }
            return tmp;
        }
        return null;
    }
    
    public ArrayList<Receipt> getReceipts() { return receipts; }
    public Receipt getReceiptAt(int index) {        
        if (index < receipts.size()) {            
            return receipts.get(index);            
        }
        return null;
    }
    
    public void clear() { receipts.clear(); }
    
    // Statistics
    public ArrayList<Item> getItems() {        
        ArrayList<Item> items = new ArrayList<>();
        for (Receipt r: receipts) {
            ArrayList<Item> i = r.getItems();
            items.addAll(i);
        }
        return items;
    }
    
    public ArrayList<String> getSellers() {
        ArrayList<String> sellers = new ArrayList<>();
        ArrayList<Item> items = getItems();
        for (Item i: items) {
            if(!sellers.contains(i.getSeller())) {
                sellers.add(i.getSeller());
            }
        }      
        return sellers;
    }
    
    public boolean sellerExist(String seller) { 
        ArrayList<String> sellers = getSellers();
        if (sellers.contains(seller)) {
            return true;
        }
        return false;
    }
    
    public BigDecimal getSum(String seller) {
        BigDecimal sum = new BigDecimal(0);        
        ArrayList<Item> items = getItems();  
        for(Item i: items) {
            if (i.getSeller().equals(seller)) {
                sum = sum.add(i.getDiscountedPrice());
            }             
        }
        return sum;       
    }
    
    public int getSoldItems(String seller) {
        int itemsCount = 0;
        ArrayList<Item> items = getItems();
        for(Item i: items) {
            if(i.getSeller().equals(seller))
                itemsCount++;
        }
        return itemsCount;
    }
}
