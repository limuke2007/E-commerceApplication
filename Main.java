package Section12_JavaCollections.SortedCollections;

import java.util.Map;

public class Main {

    private static StockList stockList = new StockList();

    public static void main(String[] args) {

        StockItem temp = new StockItem("bread", 0.86, 100);
        stockList.addStock(temp);

        temp = new StockItem("cake", 1.10, 7);
        stockList.addStock(temp);

        temp = new StockItem("car", 12.50, 2);
        stockList.addStock(temp);

        temp = new StockItem("chair", 62.0, 10);
        stockList.addStock(temp);

        temp = new StockItem("cup", 0.50, 200);
        stockList.addStock(temp);
        temp = new StockItem("cup", 0.45, 7);
        stockList.addStock(temp);

        temp = new StockItem("door", 72.95, 4);
        stockList.addStock(temp);

        temp = new StockItem("juice", 2.50, 36);
        stockList.addStock(temp);

        temp = new StockItem("phone", 96.99, 35);
        stockList.addStock(temp);

        temp = new StockItem("towel", 2.40, 80);
        stockList.addStock(temp);

        temp = new StockItem("vase", 8.76, 40);
        stockList.addStock(temp);

        System.out.println(stockList);

        for (String s : stockList.getItems().keySet()) {
            System.out.println(s);
        }

        Basket timsBasket = new Basket("Tim");
        sellItem(timsBasket, "car", 1);
        System.out.println(timsBasket);

        sellItem(timsBasket, "car", 1);
        System.out.println(timsBasket);

        if(sellItem(timsBasket, "car", 1) != 1) {
            System.out.println("There are no more cars in stock");
        }
        sellItem(timsBasket, "spanner", 5);
//        System.out.println(timsBasket);

        sellItem(timsBasket, "juice", 4);
        sellItem(timsBasket, "cup", 12);
        sellItem(timsBasket, "bread", 1);
//        System.out.println(timsBasket);

//        System.out.println(stockList);

        Basket basket = new Basket("Customer");
        sellItem(basket, "cup", 100);
        sellItem(basket, "juice", 5);
        removeItem(basket, "cup", 1);
        System.out.println(basket);

        removeItem(timsBasket, "car", 1);
        removeItem(timsBasket, "cup", 9);
        removeItem(timsBasket, "car", 1);
        System.out.println("cars removed : " + removeItem(timsBasket, "car", 1));    // should not remove

        System.out.println(timsBasket);

        removeItem(timsBasket, "bread", 1);         // remove all items from timsBasket
        removeItem(timsBasket, "cup", 3);
        removeItem(timsBasket, "juice", 4);
        removeItem(timsBasket, "cup", 3);
        System.out.println(timsBasket);

        System.out.println("\nDisplay stock list before and after checkout");
        System.out.println(basket);
        System.out.println(stockList);
        checkOut(basket);
        System.out.println(basket);
        System.out.println(stockList);


//        temp = new StockItem("pen", 1.12);
//        stockList.getItems().put(temp.getItemName(), temp);    // UnsupportedOperationException - "unmodifiable Map"

        StockItem car = stockList.getItems().get("car");
        if (car != null) {
            car.adjustStock(2000);                              // But you can change the "individual entry" in the map
        }
        if (car != null) {
            car.adjustStock(-1000);
        }
        System.out.println(stockList);

        System.out.println("====================================================================================");
        System.out.println(stockList.listPrices());


        checkOut(timsBasket);
        System.out.println(timsBasket);
        

    }


    public static int sellItem(Basket basket, String itemName, int quantity) {
        StockItem stockItem = stockList.get(itemName);               // retrieve the item from the list
        if (stockItem == null) {
            System.out.println("We don't sell " + itemName);
            return 0;
        }
        if (stockList.reserveStock(itemName, quantity) != 0) {
            return basket.addToBasket(stockItem, quantity);
        }
        return 0;
    }

    public static int removeItem(Basket basket, String itemName, int quantity) {
        StockItem stockItem = stockList.get(itemName);               // retrieve the item from the list
        if (stockItem == null) {
            System.out.println("We don't sell " + itemName);
            return 0;
        }
        if (basket.removeFromBasket(stockItem, quantity) == quantity) {
            return stockList.unreserveStock(itemName, quantity);
        }
        return 0;
    }

    public static void checkOut(Basket basket) {
        for (Map.Entry<StockItem, Integer> entry : basket.getItems().entrySet()) {
            stockList.sellStock(entry.getKey().getItemName(), entry.getValue());
        }
        basket.clearBasket();
    }

}
