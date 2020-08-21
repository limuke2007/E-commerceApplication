package Section12_JavaCollections.SortedCollections;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class StockList {

// fields

    private final Map<String, StockItem> list;

// constructor

    public StockList() {
        this.list = new LinkedHashMap<>();
    }

// methods

    public int addStock(StockItem item) {
        if (item != null) {
            StockItem inStockItem = list.getOrDefault(item.getItemName(), item);    // already exist, or new item
            if (inStockItem != item) {  // already exist
                item.adjustStock(inStockItem.availableQuantity());           // add the quantities together
            }
            list.put(item.getItemName(), item);     // put overwrites the value in map (the value includes price)
            return item.availableQuantity();                  // <K String, V StockItem>
        }
        return 0;
    }

    public int reserveStock(String itemName, int quantity) {
        StockItem inStockItem = list.get(itemName);

        if ((inStockItem != null) && (quantity > 0)) {
            return inStockItem.reserveStock(quantity);
        }
        return 0;
    }

    public int unreserveStock(String itemName, int quantity) {
        StockItem inStockItem = list.get(itemName);
        if ((inStockItem != null) && (quantity > 0)) {
            return inStockItem.unreserveStock(quantity);
        }
        return 0;
    }

    public int sellStock(String itemName, int quantity) {
        StockItem inStockItem = list.get(itemName);
        if ((inStockItem != null) && (quantity > 0)) {
            return inStockItem.finaliseStock(quantity);
        }
        return 0;

//        StockItem inStockItem = list.getOrDefault(itemName, null);
//        if ((inStockItem != null) && (inStockItem.availableQuantity() >= quantity) && (quantity > 0)) {
//            inStockItem.adjustStock(-quantity);
//            return quantity;
//        }
//        return 0;
    }

    public StockItem get(String key) {
        return list.get(key);
    }

    public Map<String, StockItem> getItems() {
        return Collections.unmodifiableMap(list);           // UnsupportedOperationException - "unmodifiable Map"
    }

    public Map<String, Double> listPrices() {
        Map<String, Double> prices = new LinkedHashMap<>();
        for (Map.Entry<String, StockItem> entry: list.entrySet()) {
            prices.put(entry.getKey(), entry.getValue().getPrice());
        }
        return Collections.unmodifiableMap(prices);
    }

    @Override
    public String toString() {
        String s = "\nStock List\n";
        double totalCost = 0.0;
        for (Map.Entry<String, StockItem> entry : list.entrySet()) {
            StockItem stockItem = entry.getValue();

            double itemValue = stockItem.getPrice() * stockItem.availableQuantity();

            s = s + stockItem + ". There are " + stockItem.availableQuantity() + " in stock. Value of items: ";
            s = s + String.format("%.2f", itemValue) + "\n";
            totalCost += itemValue;
        }

        return s + "Total stock value " + String.format("%.2f", totalCost);
    }
}
