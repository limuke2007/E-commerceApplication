package Section12_JavaCollections.SortedCollections;

public class StockItem implements Comparable<StockItem> {

// fields

    private final String itemName;
    private double price;
    private int quantityInStock = 0;      // can be initialized later
    private int reserved = 0;

// constructor

    public StockItem(String itemName, double price) {
        this.itemName = itemName;
        this.price = price;
        this.quantityInStock = 0;         // or here (but you wouldn't normally do both).
    }

    public StockItem(String itemName, double price, int quantityInStock) {
        this.itemName = itemName;                                               // overload the constructor
        this.price = price;                                                     // to include "quantity"
        this.quantityInStock = quantityInStock;
    }

    // methods

    public String getItemName() {
        return itemName;
    }

    public double getPrice() {
        return price;
    }

    public int availableQuantity() {
        return quantityInStock - reserved;
    }

    public void setPrice(double price) {
        if (price > 0.0) {
            this.price = price;
        }
    }

    public void adjustStock(int quantity) {
        int newQuantity = this.quantityInStock + quantity;
        if (newQuantity >= 0) {
            this.quantityInStock = newQuantity;
        }
    }

    public int reserveStock(int quantity) {
        if (quantity <= availableQuantity()) {
            reserved += quantity;
            return quantity;
        }
        return 0;
    }

    public int unreserveStock(int quantity) {
        if (quantity <= reserved) {
            reserved -= quantity;
            return quantity ;
        }
        return 0;
    }

    public int finaliseStock(int quantity) {
        if (quantity <= reserved) {
            quantityInStock -= quantity;
            reserved -= quantity;
            return quantity;
        }
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        System.out.println("Entering StockItem.equals()");
        if (this == obj) {
            return true;
        }
        if ((obj == null) || (obj.getClass() != this.getClass())) {
            return false;
        }
        StockItem another = (StockItem) obj;
        return this.itemName.equals(another.getItemName());
    }

    @Override
    public int hashCode() {
        return this.itemName.hashCode() + 31;
    }

    @Override
    public int compareTo(StockItem another) {
//        System.out.println("Entering StockItem.compareTo()");
        if (this == another) {
            return 0;
        }
        if (another != null) {
            return this.itemName.compareTo(another.getItemName());
        }
        throw new NullPointerException();
    }

    @Override
    public String toString() {
        return this.itemName + ": price " + this.price + ". Reserved: " + this.reserved;
    }
}
