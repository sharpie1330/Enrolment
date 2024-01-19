package app.enrolment.lecture;

import java.util.*;

public class LectureRepository {
    private Map<Long, Lecture> lectures = new HashMap<>();
    private long order = 0L;

    // 강의 등록
    public Lecture saveLecture(Lecture lecture) {
        lecture.setId(order + 1);
        lectures.put(lecture.getId(), lecture);
        return lecture;
    }

    // 전체 강의 조회
    public List<Lecture> getLectures() {
        return new ArrayList<>(lectures.values());
    }

    // 강의 아이디로 강의 찾기
    public Lecture findByLectureId(Long lectureId) {
        return lectures.get(lectureId);
    }

    // 강의 삭제
    public boolean deleteLecture(Lecture lecture) {
        if(lecture != null) {
            lectures.remove(lecture.getId());
            return true;
        }
        return false;
    }
}
