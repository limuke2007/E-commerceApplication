package Section12_JavaCollections.SortedCollections;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Basket {

// fields

    private final String name;
    private final Map<StockItem, Integer> list;    // key: "this item"; value: "quantity of this item in basket"

// constructor

    public Basket(String name) {
        this.name = name;
        this.list = new TreeMap<>();
    }

// methods

    public int addToBasket(StockItem item, int quantity) {
        if ((item != null) && (quantity > 0)) {
            int inBasket = list.getOrDefault(item, 0);
            list.put(item, inBasket + quantity);
            return inBasket;
        }
        return 0;
    }

    public int removeFromBasket(StockItem item, int quantity) {
        if ((item != null) && (quantity > 0)) {
            int inBasket = list.getOrDefault(item, 0);
            int newQuantity = inBasket - quantity;

            if (newQuantity > 0) {
                list.put(item, newQuantity);        // update entry in the map
                return quantity;
            } else if (newQuantity == 0) {
                list.remove(item);                  // entry no longer in map
                return quantity;
            }
        }
        return 0;
    }

    public void clearBasket() {
        this.list.clear();
    }

    public Map<StockItem, Integer> getItems() {
        return Collections.unmodifiableMap(list);
    }

    @Override
    public String toString() {
        String s = "\nShopping basket " + name + " contains " + list.size() + ((list.size() == 1) ? " item" : " items") + "\n";
        double totalCost = 0.0;
        for (Map.Entry<StockItem, Integer> entry : list.entrySet()) {
            s = s + entry.getKey() + ". Now " + entry.getValue() + " purchased\n";
            totalCost += entry.getKey().getPrice() * entry.getValue();
        }
        return s + "Total cost " + String.format("%.2f", totalCost);
    }
}
