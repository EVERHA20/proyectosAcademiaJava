import java.time.LocalDate;
import java.time.Period;

class Employee {
    private String name;
    private String position;
    private double currentSalary;
    private double performance;
    private LocalDate hireDate;

    public Employee(String name, String position, double currentSalary, double performance, LocalDate hireDate) {
        this.name = name;
        this.position = position;
        this.currentSalary = currentSalary;
        this.performance = performance;
        this.hireDate = hireDate;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public double getCurrentSalary() {
        return currentSalary;
    }

    public double getPerformance() {
        return performance;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public boolean isEligibleForRaise() {
        return Period.between(hireDate, LocalDate.now()).toTotalMonths() >= 12;
    }

    public double calculateRaiseScore() {
        double performanceWeight = 1.5;
        double tenureWeight = 0.5;
        double performanceScore = performance * performanceWeight;
        double tenureInYears = Period.between(hireDate, LocalDate.now()).getYears() * tenureWeight;
        return performanceScore + tenureInYears;
    }

    @Override
    public String toString() {
        int years = Period.between(hireDate, LocalDate.now()).getYears();
        return String.format("%-15s %-15s $%.2f %-10.2f %d years", 
            name, 
            position, 
            currentSalary, 
            performance, 
            years
        );
    }
}
