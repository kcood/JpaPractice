package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity @Getter @Setter
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;
    private int count; //주문 수량

    protected OrderItem(){}
    /*orderItem 생성은 createOrderItem()으로만 할수있게 하려고 막아두기용
    Orderitem orderItem1 = new OrderItem() 같은건 안대! */


    //===생성 메서드===
    public static OrderItem createOrderItem(Item item, int orderPrice, int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count);
        return orderItem;
    }

    //====비지니스 로직====
    public void cancel() {
        getItem().addStock(count);
    }

    //===조회 로직====
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
