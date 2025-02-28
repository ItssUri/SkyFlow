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
        sb.append("\n\u001B[35m[OPERATOR PAYSHEET]\n").append("Name: ").append(getSurnames()).append(", ").append(getName())
        .append("\n")
        .append("Position: Airport Operator\n")
        .append("\nWork Details:\n")
        .append(" Total Hours Worked: ")
        .append(getWorkedHours())
        .append(" hours\n")
        .append(" Regular Hours: ")
        .append(standardHours)
        .append(" hours\n")
        .append(" Overtime Hours: ")
        .append(overtimeHours)
        .append(" hours\n")
        .append("\nEarnings:")
        .append("\n Base Pay: ")
        .append(HOURLY_RATE)
        .append("eur/hour x ")
        .append(standardHours)
        .append(" = ")
        .append(HOURLY_RATE*standardHours)
        .append("eur")
        .append("\n Overtime Pay: (")
        .append(HOURLY_RATE)
        .append("eur/hour * ")
        .append(OVERTIME_RATE)
        .append("eur Hourly Rate Bonus) = eur")
        .append(overtimeHours * HOURLY_RATE * OVERTIME_RATE)
        .append("\n Gross Pay: ")
        .append(calculateSalary())
        .append("eur")
        .append("\n\nDeductions:")
        .append("\n Income Tax (IRPF, 20%): ")
        .append(calculateSalary()*0.2)
        .append("eur")
        .append("\n Social Security (6.35%): ")
        .append(calculateSalary()*0.0635)
        .append("eur")
        .append("\n Total Deduction: ")
        .append(calculateSalary()*0.2635)
        .append("eur")
        .append("\n\nNet Pay: ")
        .append(calculateSalary()*0.7365)
        .append("eur\n")
        .append("Authorized By: Carmen Quintás, Josep Tarradellas Barcelona-El Prat Airport CEO.\n")
        .append("Date: ")
        .append(today.toString())
        .append("\u001B[0m\n");
        return sb.toString();
    }
    @Override
    public String toString() {
        return String.format("""
                             %s+--------------------+------------------------------+
                             | Operator Charg     | %-28s |
                             | Start Time         | %-28s |
                             | End Time           | %-28s |
                             +--------------------+------------------------------+""",
            super.toString(), 
            String.valueOf(operatorCharge),
            String.valueOf(startTime),
            String.valueOf(endTime)
        );
    }
    
    

}