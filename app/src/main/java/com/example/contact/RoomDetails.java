package com.example.contact;

public class RoomDetails {
    private String RType,InDate,OutDate;
    private Integer age,AmountAdults,AmountChildren;
    private Long Number;
    private Float Total;


    public RoomDetails() {

    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getNumber() {
        return Number;
    }

    public void setNumber(Long number) {
        Number = number;
    }

    public Float getTotal() {
        return Total;
    }

    public void setTotal(Float total) {
        Total = total;
    }

    public Integer getAmountAdults() {
        return AmountAdults;
    }

    public void setAmountAdults(Integer amountAdults) {
        AmountAdults = amountAdults;
    }

    public Integer getAmountChildren() {
        return AmountChildren;
    }

    public void setAmountChildren(Integer amountChildren) {
        AmountChildren = amountChildren;
    }

    public String getRType() {
        return RType;
    }

    public String getInDate() {
        return InDate;
    }

    public void setInDate(String inDate) {
        InDate = inDate;
    }

    public String getOutDate() {
        return OutDate;
    }

    public void setOutDate(String outDate) {
        OutDate = outDate;
    }

    public void setRType(String RType) {
        this.RType = RType;
    }
}
