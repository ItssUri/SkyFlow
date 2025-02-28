 package model.abstracts;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import model.data.Gender;
import model.data.Nationality;

public abstract class Human {
    private String name;
    private String surnames;
    private LocalDate birthDate;
    private Gender gender;
    private Nationality nationality;
    private String id;
    private String email;
    private String phoneNumber;
    private final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurnames() {
        return surnames;
    }
    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    public Nationality getNationality() {
        return nationality;
    }
    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public Human(String name, String surnames, LocalDate birthDate, Gender gender, Nationality nationality, String id,
            String email, String phoneNumber) {
        this.name = name;
        this.surnames = surnames;
        this.birthDate = birthDate;
        this.gender = gender;
        this.nationality = nationality;
        this.id = id;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    @Override
    public String toString() {
        return String.format("""
                             
                             +--------------------+------------------------------+
                             | Attribute          | Value                        |
                             +--------------------+------------------------------+
                             | Name               | %-28s |
                             | Surnames           | %-28s |
                             | Birth Date         | %-28s |
                             | Gender             | %-28s |
                             | Nationality        | %-28s |
                             | ID                 | %-28s |
                             | Email              | %-28s |
                             | Phone Number       | %-28s |
                             +--------------------+------------------------------+""",
            String.valueOf(name), 
            String.valueOf(surnames), 
            String.valueOf(birthDate.format(DTF)), 
            String.valueOf(gender), 
            String.valueOf(nationality), 
            String.valueOf(id), 
            String.valueOf(email), 
            String.valueOf(phoneNumber)
        );
    }


    
    
}