package kth.distrolab1.ui.dtos;

import java.util.List;

public class OrderStatusDTO {
    private List<OrderDTO> unsentOrders;
    private List<OrderDTO> sentOrders;

    public OrderStatusDTO(List<OrderDTO> unsentOrders, List<OrderDTO> sentOrders) {
        this.unsentOrders = unsentOrders;
        this.sentOrders = sentOrders;
    }

    public List<OrderDTO> getUnsentOrders() {
        return unsentOrders;
    }

    public List<OrderDTO> getSentOrders() {
        return sentOrders;
    }
}

