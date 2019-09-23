package com.example.contact;

public class OrderDetails {
   // private String name,time,uname,amount,status,username;
    private String meal,time,date,amount,status,username;


    public OrderDetails() {
    }

    public OrderDetails(String meal, String time, String date, String amount,String status,String username) {
        this.meal = meal;
        this.time = time;
        this.date = date;
        this.amount = amount;
        this.status=status;
        this.username=username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
