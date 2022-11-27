package order;

public class WarehouseItem {
    private int qty;
    private String name;

    public WarehouseItem(String itemName,int qty){
        this.qty = qty;
        this.name = itemName;
    }

    public int getQty() {
        return this.qty;
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
        return String.format("(%s[%d])",this.getName(),this.getQty());
    }
}
