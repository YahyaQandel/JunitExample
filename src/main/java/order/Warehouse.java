package order;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Warehouse {
    private ArrayList<WarehouseItem> items  = new ArrayList<WarehouseItem>();

    public Warehouse(String ...wItems){
        for(String item: wItems){
            items.add(new WarehouseItem(item,1));
        }
    }

    public void add(WarehouseItem wItem) {
        if (wItem.getQty() > 0 && wItem.getName() != null && !wItem.getName().equals("")) {
            items.add(new WarehouseItem(wItem.getName(), wItem.getQty()));
        }
    }

    public void remove(String wItemName){
        for (int i=0;i<items.size();i++) {
            if (items.get(i).getName().equals((wItemName))) {
                items.remove(i);
                return;
            }
        }
    }
    public boolean hasInventory(String wItem){
        return items.stream().filter(e -> e.getName().equals(wItem)).collect(Collectors.toList()).get(0).getQty() > 0;
    }
}
