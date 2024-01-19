package app.enrolment.student;

import java.util.HashMap;
import java.util.Map;

public class StudentRepository {
    private Map<Long, Student> students = new HashMap<>();
    private long order = 0L;

    // 학생 등록
    public Student saveStudent(Student student) {
        student.setId(order + 1);
        students.put(student.getId(), student);
        return student;
    }

    // 학생 아이디로 학생 찾기
    public Student findByStudentId(Long studentId) {
        return students.get(studentId);
    }

    // 학생 로그인 아이디로 학생 찾기
    public Student findByStudentLoginId(String loginId) {
        for (Student student : students.values()) {
            if (student.getLoginId().equals(loginId)) {
                return student;
            }
        }
        return null;
    }
}
