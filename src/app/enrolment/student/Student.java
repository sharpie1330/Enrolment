package app.enrolment.student;

public class Student {
    private Long id;
    private String loginId;
    private String password;
    private String name;

    public Student(String loginId, String password, String name) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }
}
