package order;

import java.util.ArrayList;
import java.util.logging.Logger;

public class Order {
    public static final Logger LOGGER = Logger.getLogger(Order.class.getName());
    private ArrayList<OrderItem> items = new ArrayList<OrderItem>();
    private double totalPrice = 0;
    private OrderStatus status = OrderStatus.OPEN;

    public ArrayList<OrderItem> getItems() {
        return items;
    }

    public int itemsCount() {
        return items.size();
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }

    public OrderStatus getStatus() {
        return this.status;
    }

    public ArrayList<OrderItem> addItem(OrderItem item) {
        if (item != null) {
            items.add(item);
            totalPrice += item.getPrice();
        }
        return items;
    }

    public ArrayList<OrderItem> removeItem(String itemName) {
        for (int i = 0; i < items.size(); i++) {
            OrderItem currentItem = items.get(i);
            if (itemName.equals(currentItem.getName())) {
                this.totalPrice-= currentItem.getPrice();
                items.remove(i);
                return items;
            }
        }
        LOGGER.info("Item not found in order");
        return items;
    }

    public boolean proceedToCheckout(double userBalance) throws Exception {
        // not a good design
        if (userBalance < this.totalPrice) {
            LOGGER.info("Insufficient balance");
            // throw new Exception("Insufficient balance");
            return false;
        }
        if (this.items.isEmpty()) {
            LOGGER.info("No items selected");
            // throw new Exception("No items selected");
            return false;
        }
        this.status = OrderStatus.PLACED;
        return true;
    }

    public String toString(){
        return this.getItems().toString();
    }

    public void fill(Warehouse warehouse){
        for (OrderItem oItem : items){
            if (!warehouse.hasInventory(oItem.getName())) {
                return;
            }
        }
        this.removeOrderQtyAndItemsFromWarehouse(warehouse);
        this.status = OrderStatus.IS_FILLED;
    }
    public boolean isFilled(){
        return this.status == OrderStatus.IS_FILLED;
    }

    public void removeOrderQtyAndItemsFromWarehouse(Warehouse warehouse){
        for (OrderItem oItem : items){
            warehouse.remove(oItem.getName());
        }
    }
}
