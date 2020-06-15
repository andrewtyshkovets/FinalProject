package com.machine.models.cashbox;

import com.machine.models.Entity;

import java.time.LocalDateTime;

public class CashBox implements Entity {
    private long id;
    private int cashBoxNumber;
    private long cashierId;
    private LocalDateTime startDateTime;
    private double StartMoney;
    private LocalDateTime currentDateTime;
    private double CurrentMoney;
    private LocalDateTime finishDateTime;
    private double FinishMoney;

    public long getCashierId() {
        return cashierId;
    }

    public void setCashierId(long cashierId) {
        this.cashierId = cashierId;
    }

    public CashBox() {
    }

    public CashBox(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public int getCashBoxNumber() {
        return cashBoxNumber;
    }

    public void setCashBoxNumber(int cashBoxNumber) {
        this.cashBoxNumber = cashBoxNumber;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public double getStartMoney() {
        return StartMoney;
    }

    public void setStartMoney(double startMoney) {
        StartMoney = startMoney;
    }

    public LocalDateTime getCurrentDateTime() {
        return currentDateTime;
    }

    public void setCurrentDateTime(LocalDateTime currentDateTime) {
        this.currentDateTime = currentDateTime;
    }

    public double getCurrentMoney() {
        return CurrentMoney;
    }

    public void setCurrentMoney(double currentMoney) {
        CurrentMoney = currentMoney;
    }

    public LocalDateTime getFinishDateTime() {
        return finishDateTime;
    }

    public void setFinishDateTime(LocalDateTime finishDateTime) {
        this.finishDateTime = finishDateTime;
    }

    public double getFinishMoney() {
        return FinishMoney;
    }

    public void setFinishMoney(double finishMoney) {
        FinishMoney = finishMoney;
    }

    @Override
    public String toString() {
        return "CashBox{" +
                "id=" + id +
                ", cashBoxNumber=" + cashBoxNumber +
                ", cashierId=" + cashierId +
                ", startDateTime=" + startDateTime +
                ", StartMoney=" + StartMoney +
                ", currentDateTime=" + currentDateTime +
                ", CurrentMoney=" + CurrentMoney +
                ", finishDateTime=" + finishDateTime +
                ", FinishMoney=" + FinishMoney +
                '}';
    }
}
