package src;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.Objects;

public class Product {


    public java.time.LocalDate LocalDate;
    private Integer id;
        private String name;
        private Double unitPrice;
        private Integer qty;
        private LocalDate importDate;

        public Product( Integer id, String name, Double unitPrice, Integer qty, LocalDate importDate) {

            this.id = id;
            this.name = name;
            this.unitPrice = unitPrice;
            this.qty = qty;
            this.importDate = importDate;
        }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", unitPrice=" + unitPrice +
                ", qty=" + qty +
                ", importDate=" + importDate +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(unitPrice, product.unitPrice) && Objects.equals(qty, product.qty) && Objects.equals(importDate, product.importDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, unitPrice, qty, importDate);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public void setImportDate(LocalDateTime importDate) { }

    public Integer getId() {
        return id;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public Integer getQty() {
        return qty;
    }

    public LocalDateTime getImportDate() {
        return importDate.atStartOfDay();
    }

    public void setImportDate(LocalDate importDate) {
        this.importDate = importDate;
    }

    public Boolean contains(String sName) {
        return true;
    }

    public String getName() {
        return name;
    }

    public String getProName() {return "";}

    public boolean startsWith(String sName) {
            return true;
    }



}
