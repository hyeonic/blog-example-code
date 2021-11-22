package me.hyeonic.object.chapter07.employee;

public class HourlyEmployee extends Employee {
    private int timeCard;

    public HourlyEmployee(String name, double basePay, int timeCard) {
        super(name, basePay);
        this.timeCard = timeCard;
    }

    @Override
    public double calculatePay(double taxRate) {
        return (basePay * timeCard) - (basePay * timeCard) * taxRate;
    }

    @Override
    public double monthlyBasePay() {
        return 0;
    }
}
