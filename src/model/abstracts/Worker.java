package model.abstracts;

import java.time.LocalDate;
import model.data.Gender;
import model.data.Nationality;

public abstract class Worker extends Human {
    private String workerCode;
    private double workedHours;
    private double salary;
    public String getWorkerCode() {
        return workerCode;
    }
    public void setWorkerCode(String workerCode) {
        this.workerCode = workerCode;
    }
    public double getWorkedHours() {
        return workedHours;
    }
    public void setWorkedHours(double workedHours) {
        this.workedHours = workedHours;
    }
    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
    public Worker(String name, String surnames, LocalDate birthDate, Gender gender, Nationality nationality, String id,
            String email, String phoneNumber, String workerCode, double workedHours) {
        super(name, surnames, birthDate, gender, nationality, id, email, phoneNumber);
        this.workerCode = workerCode;
        this.workedHours = workedHours;
    }
    public Worker(String name, String surnames, LocalDate birthDate, Gender gender, Nationality nationality, String id,
            String email, String phoneNumber, String workerCode, double workedHours, double salary) {
        super(name, surnames, birthDate, gender, nationality, id, email, phoneNumber);
        this.workerCode = workerCode;
        this.workedHours = workedHours;
        this.salary = salary;
    }
    @Override
    public String toString() {
        return String.format("""
                             %s
                             | Worker Code        | %-28s |
                             | Worked Hours       | %-28s |
                             | Salary             | %-28s |
                             """,
            super.toString(),
            String.valueOf(workerCode),
            String.valueOf(workedHours),
            String.valueOf(salary)
        );
    }

    

}
