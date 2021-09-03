/**
 * Author: Mattias Lindell
 * Last edit: 18-03-12
 * Desc: Stats for a cash register
 */
package cashregister;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Statistics {
    History allReceipts;
    
    public Statistics() { allReceipts = new History(); }    
   
    public ArrayList<Receipt> getReceipts() { return allReceipts.getReceipts(); }
    public ArrayList<Item> getItems() {
        ArrayList<Receipt> receipts = getReceipts();
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
                sum.add(i.getDiscountedPrice());
            }
        }
        return sum;       
    }
}

