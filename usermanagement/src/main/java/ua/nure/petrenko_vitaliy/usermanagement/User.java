package ua.nure.petrenko_vitaliy.usermanagement;
//--
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Objects;
import java.time.temporal.ChronoUnit;
import java.util.Date;
//--
public class User implements Serializable {
    private Long id;
    private Date birthday;

    private String firstName;
    private String lastName;

    public User() {}
    public User(Long id, String firstName, String lastName, Date birthday) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
    }
    //-- 
    public Long getId() {
        return id;
    }
    //--
    public String getFirstName() {
        return firstName;
    }
    //--
    public String getLastName() {
        return lastName;
    }
    //--
    public String getFullName() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(lastName)
            .append(", ")
            .append(firstName);
        return stringBuilder.toString();
    }
    //--
    public Date getBirthday() {
        return birthday;
    }
    //--
    public int getAge() {
        LocalDate birthLocalDate = birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate currentLocalDate = LocalDate.now(ZoneId.systemDefault());
        return (int) ChronoUnit.YEARS.between(birthLocalDate, currentLocalDate);
    }
    //--
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    //--
    public void setId(Long id) {
        this.id = id;
    }
    //--
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    //--
    public void setbirthday(Date birthday) {
        this.birthday = birthday;
    }

}