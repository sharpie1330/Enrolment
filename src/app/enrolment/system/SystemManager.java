package app.enrolment.system;

import app.enrolment.lecture.LectureDto;
import app.enrolment.lecture.LectureDto.LectureResponse;
import app.enrolment.lecture.LectureService;
import app.enrolment.professor.ProfessorDto;
import app.enrolment.professor.ProfessorService;
import app.enrolment.student.StudentDto;
import app.enrolment.student.StudentService;

import java.util.List;

public class SystemManager {
    private final LectureService lectureService = new LectureService();
    private final ProfessorService professorService = new ProfessorService();
    private final StudentService studentService = new StudentService();

    // TODO: 학생, 교수 User 클래스로 합치고 RoleType(enum) 클래스 만들어서 역할 구분할 것
    // 학생
    public Long StudentSignUp(StudentDto.SignUpRequest request) {
        return studentService.registerStudent(request);
    }

    public Long StudentLogin(StudentDto.LoginRequest request) {
        return studentService.studentLogin(request);
    }

    public void welcomeStudent(Long studentId) {
        StudentDto.GetResponse student = studentService.getStudent(studentId);
        if (student != null) {
            System.out.println(student.getName() + "님, 환영합니다.");
        }
    }

    // 수강신청
    public void applyLecture(Long studentId, Long lectureId) {
        Boolean applySuccess = lectureService.applyLecture(studentId, lectureId);
        if (applySuccess != null) {
            if (applySuccess) {
                System.out.println("[ALERT] 수강신청이 완료되었습니다.");
            } else {
                System.out.println("[ALERT] 정원을 초과하여 수강신청이 불가합니다.");
            }
        }
    }

    // 수강신청 취소
    public void cancelLecture(Long studentId, Long lectureId) {
        if (lectureService.cancelLecture(studentId, lectureId)) {
            System.out.println("[ALERT] 수강신청 취소가 완료되었습니다.");
        }
    }

    // 교수
    public Long ProfessorSignUp(ProfessorDto.SignUpRequest request) {
        return professorService.registerProfessor(request);
    }

    public Long ProfessorLogin(ProfessorDto.LoginRequest request) {
        return professorService.professorLogin(request);
    }

    public void welcomeProfessor(Long professorId) {
        ProfessorDto.GetResponse professor = professorService.getProfessor(professorId);
        if (professor != null) {
            System.out.println(professor.getName() + "님, 환영합니다.");
        }
    }

    // 강의 등록
    public Long registerLecture(Long professorId, LectureDto.RegisterRequest request) {
        return lectureService.registerLecture(professorId, request);
    }

    // 강의 수정
    public void updateLecture(Long professorId, Long lectureId, LectureDto.UpdateRequest request) {
        if (lectureService.updateLecture(professorId, lectureId, request)) {
            System.out.println("[ALERT] 강의 수정이 완료되었습니다.");
        }
    }

    // 강의 삭제
    public void deleteLecture(Long professorId, Long lectureId) {
        if (lectureService.deleteLecture(professorId, lectureId)) {
            System.out.println("[ALERT] 강의 삭제가 완료되었습니다.");
        }
    }

    // 강의 공통기능

    // 강의 목록 조회 TODO: 강의 수 표시
    public void getLectureList() {
        List<LectureResponse> lectureResponses = lectureService.getLectureList();
        System.out.println("[강의 목록]");
        System.out.println("아이디 | 담당교수 | 강의명 | 신청현황");
        System.out.println("--------------------------------");

        if (lectureResponses == null || lectureResponses.isEmpty()) {
            System.out.println("현재 등록된 강의가 없습니다.");
        } else {
            for (LectureResponse lectureResponse : lectureResponses) {
                System.out.println(lectureResponse.getLectureId() + " | "
                + lectureResponse.getProfessorName() + " | "
                + lectureResponse.getTitle() + " | "
                + lectureResponse.getApplyCnt() + "/" + lectureResponse.getCapacity());
            }
        }
    }

    // 강의 상세 조회
    public void getLectureDetail(Long lectureId) {
        LectureResponse lectureDetail = lectureService.getLectureDetail(lectureId);
        if (lectureDetail != null) {
            System.out.println("[강의 정보]");
            System.out.println("- 아이디: " + lectureDetail.getLectureId());
            System.out.println("- 담당교수: " + lectureDetail.getProfessorName());
            System.out.println("- 강의명: " + lectureDetail.getTitle());
            System.out.println("- 신청 현황: " + lectureDetail.getApplyCnt() + "/" + lectureDetail.getCapacity());
        }
    }
}
