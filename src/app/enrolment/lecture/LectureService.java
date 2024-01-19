package app.enrolment.lecture;

import app.enrolment.lecture.LectureDto.LectureResponse;
import app.enrolment.professor.ProfessorRepository;
import app.enrolment.student.Student;
import app.enrolment.student.StudentRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LectureService {
    private final LectureRepository lectureRepository = new LectureRepository();
    private final ProfessorRepository professorRepository = new ProfessorRepository();
    private final StudentRepository studentRepository = new StudentRepository();

    // 강의 등록
    public Long registerLecture(Long professorId, LectureDto.RegisterRequest request) {
        Lecture lecture = request.toObject(
                professorRepository.findByProfessorId(professorId));
        Lecture savedLecture = lectureRepository.saveLecture(lecture);
        System.out.println("[ALERT] 강의를 등록했습니다.");
        return savedLecture.getId();
    }

    // 강의 상세 조회
    public LectureResponse getLectureDetail(Long lectureId) {
        Lecture lecture = lectureRepository.findByLectureId(lectureId);
        if (lecture == null) {
            System.out.println("[ERROR] 존재하지 않는 강의입니다. 강의를 조회할 수 없습니다.");
            return null;
        }
        return LectureResponse.fromObject(lecture);
    }

    // 강의 목록 조회
    public List<LectureResponse> getLectureList() {
        List<LectureResponse> lectureResponses = new ArrayList<>();
        List<Lecture> lectures = lectureRepository.getLectures();

        if (lectures == null || lectures.isEmpty()) {
            return Collections.emptyList();
        }

        for (Lecture lecture : lectures) {
            lectureResponses.add(LectureResponse.fromObject(lecture));
        }
        return lectureResponses;
    }

    // 강의 수정
    public boolean updateLecture(Long professorId, Long lectureId, LectureDto.UpdateRequest request) {
        if (professorRepository.findByProfessorId(professorId) == null) {
            System.out.println("[ERROR] 존재하지 않는 교수입니다. 강의를 수정할 수 없습니다.");
            return false;
        }

        Lecture lecture = lectureRepository.findByLectureId(lectureId);
        if (lecture == null) {
            System.out.println("[ERROR] 존재하지 않는 강의입니다. 강의를 수정할 수 없습니다.");
            return false;
        }
        lecture.update(request.getTitle(), request.getCapacity());
        return true;
    }

    // 강의 삭제
    public boolean deleteLecture(Long professorId, Long lectureId) {
        if (professorRepository.findByProfessorId(professorId) == null) {
            System.out.println("[ERROR] 존재하지 않는 교수입니다. 강의를 삭제할 수 없습니다.");
            return false;
        }

        Lecture lecture = lectureRepository.findByLectureId(lectureId);
        if (lecture == null) {
            System.out.println("[ERROR] 존재하지 않는 강의입니다. 강의를 삭제할 수 없습니다.");
        }
        return lectureRepository.deleteLecture(lecture);
    }

    // 수강 신청
    public Boolean applyLecture(Long studentId, Long lectureId) {
        Lecture lecture = lectureRepository.findByLectureId(lectureId);
        if (lecture == null) {
            System.out.println("[ERROR] 존재하지 않는 강의입니다. 수강신청할 수 없습니다.");
            return null;
        }
        Student student = studentRepository.findByStudentId(studentId);
        return lecture.apply(student);
    }

    // 수강 신청 취소
    public boolean cancelLecture(Long studentId, Long lectureId) {
        Lecture lecture = lectureRepository.findByLectureId(lectureId);
        if (lecture == null) {
            System.out.println("[ERROR] 존재하지 않는 강의입니다. 수강신청 취소할 수 없습니다.");
            return false;
        }
        Student student = studentRepository.findByStudentId(studentId);
        lecture.cancel(student);
        return true;
    }
}
