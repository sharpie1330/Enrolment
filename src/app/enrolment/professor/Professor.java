package app.enrolment.professor;

public class Professor {
    private Long id;
    private String loginId;
    private String password;
    private String name;

    public Professor(String loginId, String password, String name) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
