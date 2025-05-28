package net.javaguides.springboot.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name="first_name")
    public String firstName;
    @Column(name="last_name")
    public String lastName;

    @Column(name="email")
    public String email;

    @Column(name="birthday_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date birthdayDate;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="company_id")
    public Company company;


    @Column(columnDefinition = "TEXT")
    public String bio;


    @Column(name = "Image")
    public String imageFilename;

    public String getImageFilename() {
        return imageFilename;
    }

    public void setImageFilename(String imageFile) {
        this.imageFilename = imageFile;
    }

    public Date getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(Date birthdayDate) {
        this.birthdayDate = birthdayDate;
        /*
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = (Date)formatter.parse(this.birthdayDate.toString());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
         */
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getbio() {return bio;}

    public void setbio(String bio) {this.bio = bio;}

}
