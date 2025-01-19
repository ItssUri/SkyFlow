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
        .append("Name: " + getSurnames() +", " + getName()+"\n")
        .append("Position: Pilot\n")
        .append("\nWork Details:\n")
        .append(" Total Hours Worked: " + getWorkedHours() + " hours\n")
        .append(" Regular Hours: " + standardHours + " hours\n")
        .append(" vertime Hours: " + overtimeHours + " hours\n")
        .append("\nEarnings:")
        .append("\n Base Pay: " + HOURLY_RATE+"eur/hour x " + standardHours + " = "+(HOURLY_RATE*standardHours)+"eur")
        .append("\n Overtime Pay: (" + HOURLY_RATE+"eur/hour + " + OVERTIME_BONUS + "eur bonus) x" + overtimeHours + " = "+(HOURLY_RATE + OVERTIME_BONUS)*overtimeHours+"eur")
        .append("\n Gross Pay: " + calculateSalary()+"eur")
        .append("\n\nDeductions:")
        .append("\n Income Tax (IRPF, 20%): " + calculateSalary()*0.2+"eur")
        .append("\n Social Security (6.35%): " + calculateSalary()*0.0635+"eur")
        .append("\n Total Deduction: " + calculateSalary()*0.2635+"eur")
        .append("\n\nNet Pay: " + calculateSalary()*0.7365+"eur")
        .append("\nAuthorized By: Carmen Quintás, Josep Tarradellas Barcelona-El Prat Airport CEO.\n")
        .append("Date: " + today.toString()+"\n\u001B[0m");
        return sb.toString();
    }

    @Override
public String toString() {
    return String.format(
        "%s" + 
        "+--------------------+------------------------------+\n" +
        "| Pilot Rank         | %-28s |\n" +
        "+--------------------+------------------------------+",
        super.toString(),
        String.valueOf(pilotRank)
    );
}

    

}
