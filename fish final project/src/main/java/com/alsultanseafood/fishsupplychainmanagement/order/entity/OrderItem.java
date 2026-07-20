package com.alsultanseafood.fishsupplychainmanagement.order.entity;



import com.alsultanseafood.fishsupplychainmanagement.Fish.entity.Fish;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    @ManyToOne
@JoinColumn(name = "order_id")
@JsonBackReference
private Order order;

    @ManyToOne
    @JoinColumn(name = "fish_id")
    private Fish fish;

    private Double quantity;

    private Double price;

    private Double total;

    public OrderItem() {
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(
            Long orderItemId) {
        this.orderItemId =
                orderItemId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(
            Order order) {
        this.order = order;
    }

    public Fish getFish() {
        return fish;
    }

    public void setFish(
            Fish fish) {
        this.fish = fish;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(
            Double quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(
            Double price) {
        this.price = price;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(
            Double total) {
        this.total = total;
    }
}
