package app.enrolment.professor;

import java.util.HashMap;
import java.util.Map;

public class ProfessorRepository {
    private static final Map<Long, Professor> professors = new HashMap<>();
    private static long order = 0L;

    // 교수 등록
    public Professor saveProfessor(Professor professor) {
        professor.setId(++order);
        professors.put(professor.getId(), professor);
        return professor;
    }

    // 교수 아이디로 교수 찾기
    public Professor findByProfessorId(Long professorId) {
        return professors.get(professorId);
    }

    // 교수 로그인 아이디로 학생 찾기
    public Professor findByProfessorLoginId(String loginId) {
        for (Professor professor : professors.values()) {
            if (professor.getLoginId().equals(loginId)) {
                return professor;
            }
        }
        return null;
    }
}
