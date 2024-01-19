package app.enrolment.lecture;

import app.enrolment.professor.Professor;
import app.enrolment.student.Student;

import java.util.ArrayList;
import java.util.List;

public class Lecture {
    private Long id;
    private Professor professor;
    private String title;
    private int capacity;

    private List<Student> students = new ArrayList<>();

    public Lecture(Professor professor, String title, int capacity) {
        this.professor = professor;
        this.title = title;
        this.capacity = capacity;
    }

    // 신청 시 정원을 넘어가면 신청 불가
    public boolean apply(Student student) {
        if (students.size() == capacity) {
            return false;
        }
        students.add(student);
        return true;
    }

    // 수강신청 취소
    public void cancel(Student student) {
        students.remove(student);
    }

    public List<Student> getStudents() {
        return students;
    }

    // 강의 수정 (강의명, 정원만 수정 가능)
    public void update(String title, int capacity) {
        this.title = title;
        this.capacity = capacity;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Professor getProfessor() {
        return professor;
    }
}
