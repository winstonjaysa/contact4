package com.example.contact;

public class OrderDetails {
    private String name,time,uname,amount,status;
   // private int amount;

    public OrderDetails() {
    }

    public OrderDetails(String name, String time, String uname, String amount,String status) {
        this.name = name;
        this.time = time;
        this.uname = uname;
        this.amount = amount;
        this.status=status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //public String tolString(){
       //return this.name+" "+this.amount+" "+this.time+" "+this.uname;
   // }
}
