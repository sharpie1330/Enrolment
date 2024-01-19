package app.enrolment.student;

public class StudentDto {

    public static class SignUpRequest {
        private String loginId;
        private String password;
        private String name;

        public SignUpRequest(String loginId, String password, String name) {
            this.loginId = loginId;
            this.password = password;
            this.name = name;
        }

        public Student toObject() {
            return new Student(this.loginId, this.password, this.name);
        }
    }

    public static class LoginRequest {
        private String loginId;
        private String password;

        public LoginRequest(String loginId, String password) {
            this.loginId = loginId;
            this.password = password;
        }

        public String getLoginId() {
            return loginId;
        }

        public String getPassword() {
            return password;
        }
    }

    public static class GetResponse {
        private String name;

        public GetResponse(String name) {
            this.name = name;
        }

        public static GetResponse fromObject(Student student) {
            return new GetResponse(student.getName());
        }

        public String getName() {
            return name;
        }
    }
}
