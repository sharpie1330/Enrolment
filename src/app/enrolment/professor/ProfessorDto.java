package app.enrolment.professor;

import app.enrolment.student.Student;
import app.enrolment.student.StudentDto;

public class ProfessorDto {
    public static class SignUpRequest {
        private String loginId;
        private String password;
        private String name;

        public SignUpRequest(String loginId, String password, String name) {
            this.loginId = loginId;
            this.password = password;
            this.name = name;
        }

        public Professor toObject() {
            return new Professor(this.loginId, this.password, this.name);
        }
    }

    public static class GetResponse {
        private String name;

        public GetResponse(String name) {
            this.name = name;
        }

        public static ProfessorDto.GetResponse fromObject(Professor professor) {
            return new ProfessorDto.GetResponse(professor.getName());
        }

        public String getName() {
            return name;
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
}
