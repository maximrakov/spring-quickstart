package spring_prac.models;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class User {
    private static int cnt = 0;

    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name = null;

    @Min(value = 0, message = "Age should be greater than 0")
    private int age = 3;

    public User(){
    }

    public User(@NotEmpty(message = "Name should not be empty") @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters") String name, @Min(value = 0, message = "Age should be greater than 0") int age, @NotEmpty(message = "Email should not be empty") @Email(message = "Email should be valid") String email) {
        this.id = cnt++;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    private String email;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }
}
