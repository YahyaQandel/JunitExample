package order;

public class OrderItem {
    private int id;
    private double price;
    private String name;
    private int qty;
    public OrderItem(String itemName,double itemPrice){
        this.price = itemPrice;
        this.name = itemName;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        return String.format("(%s - %f)",this.getName(),this.getPrice());
    }
}
