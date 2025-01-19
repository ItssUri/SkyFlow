package model.workers;

import java.text.DecimalFormat;
import java.time.LocalDate;

import model.abstracts.Worker;
import model.data.Gender;
import model.data.Nationality;

public class Stewardess extends Worker implements model.interfaces.IsWorker {
    private final double HOURLY_RATE = 45.0;
    private double height;
    private double weight;

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }


    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Stewardess(String name, String surnames, LocalDate birthDate, Gender gender, Nationality nationality,
            String id, String email, String phoneNumber, String workerCode, double workedHours, double height, double weight) {
        super(name, surnames, birthDate, gender, nationality, id, email, phoneNumber, workerCode, workedHours);
        this.height = height;
        this.weight = weight;
    }

    public Stewardess(String name, String surnames, LocalDate birthDate, Gender gender, Nationality nationality,
            String id, String email, String phoneNumber, String workerCode, double workedHours, double salary,
            double height, double weight) {
        super(name, surnames, birthDate, gender, nationality, id, email, phoneNumber, workerCode, workedHours, salary);
        this.height = height;
        this.weight = weight;
    }

    @Override
    public double calculateSalary() {
        return getWorkedHours()*HOURLY_RATE;
    }

    @Override
    public String generatePaysheet() {
        DecimalFormat df = new DecimalFormat("#.###");
        LocalDate today = LocalDate.now();
        StringBuilder sb = new StringBuilder();
        sb.append("\n\u001B[35m[STEWARDESS PAYSHEET]\n")
        .append("Name: " + getSurnames() +", " + getName()+"\n")
        .append("Position: Sterwardess\n")
        .append("\nWork Details:\n")
        .append("  Total Hours Worked: " + getWorkedHours() + " hours\n")
        .append("\nEarnings:")
        .append("\n  Gross Pay: " + calculateSalary()+"eur")
        .append("\n\nDeductions:")
        .append("\n  Income Tax (IRPF, 20%): " + calculateSalary()*0.2+"eur")
        .append("\n  Social Security (6.35%): " + calculateSalary()*0.0635+"eur")
        .append("\n  Total Deduction: " + calculateSalary()*0.2635+"eur")
        .append("\n\nNet Pay: " + df.format(calculateSalary()*0.7365)+"eur")
        .append("\nAuthorized By: Carmen Quintás, Josep Tarradellas Barcelona-El Prat Airport CEO.\n")
        .append("Date: " + today.toString()+"\u001B[0m\n");
        return sb.toString();
    }

    @Override
    public String toString() {
        return String.format(
            "%s" +
            "+--------------------+------------------------------+\n" +
            "| Height             | %-28s |\n" +
            "| Weight             | %-28s |\n" +
            "+--------------------+------------------------------+",
            super.toString(),
            String.valueOf(height),
            String.valueOf(weight)
        );
    }

    

}
