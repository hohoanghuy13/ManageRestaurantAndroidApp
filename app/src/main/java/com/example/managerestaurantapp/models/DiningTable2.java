package com.example.managerestaurantapp.models;

public class DiningTable2 {
    private int TableID, TableFloor, SeatCount, TableState;
    private String Note;

    public DiningTable2(int tableID, int tableFloor, int seatCount, int tableState, String note) {
        TableID = tableID;
        TableFloor = tableFloor;
        SeatCount = seatCount;
        TableState = tableState;
        Note = note;
    }

    @Override
    public String toString() {
        return "DinningTable{" +
                "TableID=" + TableID +
                ", TableFloor=" + TableFloor +
                ", SeatCount=" + SeatCount +
                ", TableState=" + TableState +
                ", Note='" + Note + '\'' +
                '}';
    }

    public DiningTable2() {
    }

    public int getTableID() {
        return TableID;
    }

    public void setTableID(int tableID) {
        TableID = tableID;
    }

    public int getTableFloor() {
        return TableFloor;
    }

    public void setTableFloor(int tableFloor) {
        TableFloor = tableFloor;
    }

    public int getSeatCount() {
        return SeatCount;
    }

    public void setSeatCount(int seatCount) {
        SeatCount = seatCount;
    }

    public int getTableState() {
        return TableState;
    }

    public void setTableState(int tableState) {
        TableState = tableState;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }
}
