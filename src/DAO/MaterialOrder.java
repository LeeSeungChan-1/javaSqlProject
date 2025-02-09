package DAO;


import static java.sql.Types.NULL;

public class MaterialOrder {
    private long id = NULL;
    private String orderNumber;
    private String orderDate;
    private String orderer;

    public MaterialOrder(String orderNumber, String orderDate, String orderer) {
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.orderer = orderer;
    }

    public MaterialOrder(long id, String orderNumber, String orderDate, String orderer) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.orderer = orderer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderer() {
        return orderer;
    }

    public void setOrderer(String orderer) {
        this.orderer = orderer;
    }
}
