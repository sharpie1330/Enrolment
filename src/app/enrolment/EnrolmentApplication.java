package app.enrolment;

import app.enrolment.lecture.LectureDto;
import app.enrolment.professor.ProfessorDto;
import app.enrolment.student.StudentDto;
import app.enrolment.system.SystemManager;

import java.util.Scanner;

public class EnrolmentApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        SystemManager systemManager = new SystemManager();

        System.out.println("수강신청 시스템에 오신 것을 환영합니다.");

        // TODO: 메뉴 입력 시스템 만들기
//        System.out.println("메뉴) 1. 회원가입 | 2. 로그인 | 3. 강의 조회 | 4. 나가기");
//
//        int menu = scanner.nextInt();
//        switch (menu) {
//            case 1:
//                int menuDetail = scanner.nextInt();
//                System.out.println("회원가입) 1. 학생 | 2. 교수 | 3. 뒤로");
//                System.out.println();
//        }

        // TODO: 권한별 메서드 접근 제한
        System.out.println("교수 회원가입");
        Long professorId = systemManager.ProfessorSignUp(new ProfessorDto.SignUpRequest("pro", "1234", "김교수"));

        System.out.println("강의 등록");
        Long lectureId = systemManager.registerLecture(professorId, new LectureDto.RegisterRequest("컴퓨터 공학 기초", 15));

        System.out.println("학생 회원가입");
        Long studentId = systemManager.StudentSignUp(new StudentDto.SignUpRequest("stu", "1234", "김학생"));

        System.out.println("수강신청");
        systemManager.applyLecture(studentId, lectureId);

        System.out.println("수강신청 실패 예시");
        // TODO: 이미 수정신청 헀으면 예외 표시
        systemManager.applyLecture(studentId, 2L);

        System.out.println("강의 목록 조회");
        systemManager.getLectureList();

        scanner.close();
    }
}
