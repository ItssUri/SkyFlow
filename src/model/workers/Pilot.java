package model.workers;

import java.time.LocalDate;
import model.abstracts.Worker;
import model.data.Gender;
import model.data.Nationality;
import model.data.PilotRank;

public class Pilot extends Worker implements model.interfaces.IsWorker {
    private final double HOURLY_RATE = 100;
    private final double OVERTIME_BONUS = 50; // Per extra hour after 40 hours
    private PilotRank pilotRank;

    public PilotRank getPilotRank() {
        return pilotRank;
    }

    public void setPilotRank(PilotRank pilotRank) {
        this.pilotRank = pilotRank;
    }

    public Pilot(String name, String surnames, LocalDate birthDate, Gender gender, Nationality nationality, String id,
            String email, String phoneNumber, String workerCode, double workedHours, double salary,
            PilotRank pilotRank) {
        super(name, surnames, birthDate, gender, nationality, id, email, phoneNumber, workerCode, workedHours, salary);

        this.pilotRank = pilotRank;
    }

    public Pilot(String name, String surnames, LocalDate birthDate, Gender gender, Nationality nationality, String id,
            String email, String phoneNumber, String workerCode, double workedHours,
            PilotRank pilotRank) {
        super(name, surnames, birthDate, gender, nationality, id, email, phoneNumber, workerCode, workedHours);

        this.pilotRank = pilotRank;
    }

    @Override
    
    public double calculateSalary() {
        double standardHours = Math.min(getWorkedHours(), 40.0); // If the pilot has worked over 40 hours, it will be considered Overtime.
        double overtimeHours = Math.max(getWorkedHours() - 40.0, 0.0);
        return (standardHours * HOURLY_RATE) + (overtimeHours * (HOURLY_RATE + OVERTIME_BONUS));
    }

    // Deductions:

    // Income Tax (IRPF, 20%): eur965.00
    // Social Security (6.35%): eur306.39

    @Override
    public String generatePaysheet() {
        double standardHours = Math.min(getWorkedHours(), 40.0);
        double overtimeHours = Math.max(getWorkedHours() - 40.0, 0.0);
        LocalDate today = LocalDate.now();
        StringBuilder sb = new StringBuilder();
        sb.append("\n\u001B[35m[PILOT PAYSHEET]\n")
        .append("Name: ").append(getSurnames()).append(", ").append(getName()).append("\n")
        .append("Position: Pilot\n\n")
        
        .append("Work Details:\n")
        .append(" Total Hours Worked: ").append(getWorkedHours()).append(" hours\n")
        .append(" Regular Hours: ").append(standardHours).append(" hours\n")
        .append(" Overtime Hours: ").append(overtimeHours).append(" hours\n\n")
        
        .append("Earnings:\n")
        .append(" Base Pay: ").append(HOURLY_RATE).append("eur/hour x ").append(standardHours)
        .append(" = ").append(HOURLY_RATE * standardHours).append("eur\n")
        .append(" Overtime Pay: (").append(HOURLY_RATE).append("eur/hour + ")
        .append(OVERTIME_BONUS).append("eur bonus) x ").append(overtimeHours)
        .append(" = ").append((HOURLY_RATE + OVERTIME_BONUS) * overtimeHours).append("eur\n")
        .append(" Gross Pay: ").append(calculateSalary()).append("eur\n\n")
        
        .append("Deductions:\n")
        .append(" Income Tax (IRPF, 20%): ").append(calculateSalary() * 0.2).append("eur\n")
        .append(" Social Security (6.35%): ").append(calculateSalary() * 0.0635).append("eur\n")
        .append(" Total Deduction: ").append(calculateSalary() * 0.2635).append("eur\n\n")
        
        .append("Net Pay: ").append(calculateSalary() * 0.7365).append("eur\n\n")
        
        .append("Authorized By: Carmen Quintás, Josep Tarradellas Barcelona-El Prat Airport CEO.\n")
        .append("Date: ").append(today.toString()).append("\n\u001B[0m");

        return sb.toString();
    }

    @Override
public String toString() {
    return String.format("""
                         %s+--------------------+------------------------------+
                         | Pilot Rank         | %-28s |
                         +--------------------+------------------------------+""",
        super.toString(),
        String.valueOf(pilotRank)
    );
}

    

}
