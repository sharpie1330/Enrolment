package app.enrolment.lecture;

import app.enrolment.professor.Professor;

public class LectureDto {

    public static class RegisterRequest {
        private String title;
        private int capacity;

        public RegisterRequest(String title, int capacity) {
            this.title = title;
            this.capacity = capacity;
        }

        public Lecture toObject(Professor professor) {
            return new Lecture(professor, title, capacity);
        }
    }

    public static class LectureResponse {
        private Long lectureId;
        private String professorName;
        private String title;
        private int applyCnt;
        private int capacity;

        public LectureResponse(Long lectureId, String professorName, String title, int applyCnt, int capacity) {
            this.lectureId = lectureId;
            this.professorName = professorName;
            this.title = title;
            this.applyCnt = applyCnt;
            this.capacity = capacity;
        }

        public static LectureResponse fromObject(Lecture lecture) {
            return new LectureResponse(lecture.getId(), lecture.getProfessor().getName(),
                    lecture.getTitle(), lecture.getStudents().size(), lecture.getCapacity());
        }

        public Long getLectureId() {
            return lectureId;
        }

        public String getProfessorName() {
            return professorName;
        }

        public String getTitle() {
            return title;
        }

        public int getApplyCnt() {
            return applyCnt;
        }

        public int getCapacity() {
            return capacity;
        }
    }

    public static class UpdateRequest {
        private String title;
        private int capacity;


        public UpdateRequest(String title, int capacity) {
            this.title = title;
            this.capacity = capacity;
        }

        public String getTitle() {
            return title;
        }

        public int getCapacity() {
            return capacity;
        }
    }
}
