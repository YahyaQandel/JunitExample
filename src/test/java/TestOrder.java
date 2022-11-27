import order.Order;
import order.OrderItem;
import order.OrderStatus;
import order.Warehouse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestOrder {
    Order order ;
    @Before
    public void setup(){
        order = new Order();
    }
    @Test
    public void testOrderWhenCreatedHasNoItems(){
        Order order = new Order();
        assertEquals(0,order.itemsCount());
    }

    @Test
    public void testOrderAddItemIncreasesOrderItemsCountByOne(){
        assertEquals(0,order.itemsCount());
        OrderItem item = new OrderItem("Pepsi",5.0);
        int itemsCount = order.addItem(item).size();
        assertEquals(1,itemsCount);
    }

    @Test
    public void testOrderAddItemIncreasesOrderTotalPriceByItemPrice(){
        assertEquals(0,order.itemsCount());
        OrderItem item = new OrderItem("Bread",2.5);
        order.addItem(item);
        assertEquals(2.5,order.getTotalPrice(),0);
    }

    @Test
    public void testOrderRemoveItemFromOrder(){
        OrderItem toBeRemovedItem = new OrderItem("Water",3);
        order.addItem(new OrderItem("Chips",10));
        order.addItem(toBeRemovedItem);
        order.addItem(new OrderItem("Nescafe",100));
        double orderTotalPrice = order.getTotalPrice();
        ArrayList<OrderItem> items = order.removeItem(toBeRemovedItem.getName());
        assertFalse("Item "+toBeRemovedItem.getName()+ " found in order", items.stream().anyMatch(e -> e.getName().equals(toBeRemovedItem.getName())));
        assertEquals(2,items.size());
        assertEquals(orderTotalPrice-toBeRemovedItem.getPrice(),order.getTotalPrice(),0);
    }
    @Test
    public void testOrderProceedToCheckoutFailsWithInsufficientBalanceDoesntChangeOrderStatus() throws Exception {
        order = new Order();
        double userBalance = 3.0;
        order.addItem(new OrderItem("Juice",12.0));
        order.proceedToCheckout(userBalance);
        assertEquals(OrderStatus.OPEN,order.getStatus());
    }

    @Test
    public void testOrderFillWithMock(){
        String TALISKER_NAME= "Talisker";
        Order order = new Order();
        OrderItem TALISKER = new OrderItem(TALISKER_NAME,1);
        TALISKER.setQty(50);
        order.addItem(TALISKER);
        Warehouse warehouseMock = mock(Warehouse.class);
        when(warehouseMock.hasInventory(TALISKER_NAME)).thenReturn(true);
        order.fill(warehouseMock);
        //verify
        assertTrue(order.isFilled());
    }
}
