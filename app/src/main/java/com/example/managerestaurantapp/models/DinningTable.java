package com.example.managerestaurantapp.models;

public class DinningTable {
    int tableid,seat,tableFloor;
    String note;

    public DinningTable(int tableid,int tableFloor, int seat, String note) {
        this.tableid = tableid;
        this.seat = seat;
        this.note = note;
        this.tableFloor = tableFloor;
    }

    public DinningTable() {
    }

    public int getTableid() {
        return tableid;
    }

    public void setTableid(int tableid) {
        this.tableid = tableid;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getTableFloor() {
        return tableFloor;
    }

    public void setTableFloor(int tableFloor) {
        this.tableFloor = tableFloor;
    }
}
