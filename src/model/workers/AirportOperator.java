package model.workers;

import java.time.LocalDate;
import java.time.LocalTime;

import model.abstracts.Worker;
import model.data.Gender;
import model.data.Nationality;

public class AirportOperator extends Worker implements model.interfaces.IsWorker {
    private final double HOURLY_RATE = 50.0;
    private final double OVERTIME_RATE = 1.5; // 1.5x pay for overtime hours
    private String operatorCharge;
    private LocalTime startTime;
    private LocalTime endTime;
    
    public String getOperatorCharge() {
        return operatorCharge;
    }
    public void setOperatorCharge(String operatorCharge) {
        this.operatorCharge = operatorCharge;
    }
    
    public LocalTime getStartTime() {
        return startTime;
    }
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }
    public LocalTime getEndTime() {
        return endTime;
    }
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
    public AirportOperator(String name, String surnames, LocalDate birthDate, Gender gender, Nationality nationality,
            String id, String email, String phoneNumber, String workerCode, double workedHours, String operatorCharge,
            LocalTime startTime, LocalTime endTime) {
        super(name, surnames, birthDate, gender, nationality, id, email, phoneNumber, workerCode, workedHours);
        this.operatorCharge = operatorCharge;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public AirportOperator(String name, String surnames, LocalDate birthDate, Gender gender, Nationality nationality,
            String id, String email, String phoneNumber, String workerCode, double workedHours, double salary,
            String operatorCharge, LocalTime startTime, LocalTime endTime) {
        super(name, surnames, birthDate, gender, nationality, id, email, phoneNumber, workerCode, workedHours, salary);
        this.operatorCharge = operatorCharge;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    @Override
    public double calculateSalary() {
        double standardHours = Math.min(getWorkedHours(), 40.0); // If the operator has worked over 40 hours, it will be considered Overtime.
        double overtimeHours = Math.max(getWorkedHours() - 40.0, 0.0);
        return (standardHours * HOURLY_RATE) + (overtimeHours * HOURLY_RATE * OVERTIME_RATE);
    }
    @Override
    public String generatePaysheet() {
        double standardHours = Math.min(getWorkedHours(), 40.0);
        double overtimeHours = Math.max(getWorkedHours() - 40.0, 0.0);
        LocalDate today = LocalDate.now();
        StringBuilder sb = new StringBuilder();
        sb.append("\n\u001B[35m[OPERATOR PAYSHEET]\n")
        .append("Name: " + getSurnames() +", " + getName()+"\n")
        .append("Position: Airport Operator\n")
        .append("\nWork Details:\n")
        .append(" Total Hours Worked: " + getWorkedHours() + " hours\n")
        .append(" Regular Hours: " + standardHours + " hours\n")
        .append(" Overtime Hours: " + overtimeHours + " hours\n")
        .append("\nEarnings:")
        .append("\n Base Pay: " + HOURLY_RATE+"eur/hour x " + standardHours + " = "+(HOURLY_RATE*standardHours) + "eur")
        .append("\n Overtime Pay: (" + HOURLY_RATE+"eur/hour * " + OVERTIME_RATE + "eur Hourly Rate Bonus) = eur"+(overtimeHours * HOURLY_RATE * OVERTIME_RATE))
        .append("\n Gross Pay: " + calculateSalary()+"eur")
        .append("\n\nDeductions:")
        .append("\n Income Tax (IRPF, 20%): " + calculateSalary()*0.2+"eur")
        .append("\n Social Security (6.35%): " + calculateSalary()*0.0635+"eur")
        .append("\n Total Deduction: " + calculateSalary()*0.2635+"eur")
        .append("\n\nNet Pay: " + calculateSalary()*0.7365+"eur\n")
        .append("Authorized By: Carmen Quintás, Josep Tarradellas Barcelona-El Prat Airport CEO.\n")
        .append("Date: " + today.toString()+"\u001B[0m\n");
        return sb.toString();
    }
    @Override
    public String toString() {
        return String.format(
            "%s" + 
            "+--------------------+------------------------------+\n" +
            "| Operator Charg     | %-28s |\n" +
            "| Start Time         | %-28s |\n" +
            "| End Time           | %-28s |\n" +
            "+--------------------+------------------------------+",
            super.toString(), 
            String.valueOf(operatorCharge),
            String.valueOf(startTime),
            String.valueOf(endTime)
        );
    }
    
    

}