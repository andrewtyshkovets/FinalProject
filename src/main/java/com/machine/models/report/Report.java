package com.machine.models.report;

import com.machine.models.Entity;

import java.time.LocalDateTime;

public class Report implements Entity {
    private long id;
    private int typeId;
    private long cashBoxId;
    private LocalDateTime startTime;
    private double startMoney;
    private LocalDateTime TotalTime;
    private double totalMoney;
    private double sumOfSales;

    public Report() {
    }

    public Report(long id) {
        this.id = id;
    }

    public Report(int typeId, long cashBoxId, LocalDateTime startTime, double startMoney, LocalDateTime totalTime, double totalMoney, double sumOfSales) {
        this.typeId = typeId;
        this.cashBoxId = cashBoxId;
        this.startTime = startTime;
        this.startMoney = startMoney;
        TotalTime = totalTime;
        this.totalMoney = totalMoney;
        this.sumOfSales = sumOfSales;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public long getCashBoxId() {
        return cashBoxId;
    }

    public void setCashBoxId(long cashBoxId) {
        this.cashBoxId = cashBoxId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public double getStartMoney() {
        return startMoney;
    }

    public void setStartMoney(double startMoney) {
        this.startMoney = startMoney;
    }

    public LocalDateTime getTotalTime() {
        return TotalTime;
    }

    public void setTotalTime(LocalDateTime totalTime) {
        TotalTime = totalTime;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public double getSumOfSales() {
        return sumOfSales;
    }

    public void setSumOfSales(double sumOfSales) {
        this.sumOfSales = sumOfSales;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", typeId=" + typeId +
                ", cashBoxId=" + cashBoxId +
                ", startTime=" + startTime +
                ", startMoney=" + startMoney +
                ", TotalTime=" + TotalTime +
                ", totalMoney=" + totalMoney +
                ", sumOfSales=" + sumOfSales +
                '}';
    }
}
