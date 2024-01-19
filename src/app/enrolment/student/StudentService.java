package app.enrolment.student;

public class StudentService {
    private final StudentRepository studentRepository = new StudentRepository();

    // 학생 등록
    public Long registerStudent(StudentDto.SignUpRequest request) {
        Student student = request.toObject();
        Student savedStudent = studentRepository.saveStudent(student);
        System.out.println("[ALERT] 학생 회원가입이 완료되었습니다.");
        return savedStudent.getId();
    }

    // 학생 로그인
    public Long studentLogin(StudentDto.LoginRequest request) {
        Student student = studentRepository.findByStudentLoginId(request.getLoginId());
        if (student != null) {
            if (student.getPassword().equals(request.getPassword())) {
                return student.getId();
            }
        }
        System.out.println("[ALERT] 아이디나 비밀번호가 틀렸습니다. 다시 입력하거나, 회원가입한 적 없다면 회원가입하세요.");
        return null;
    }

    // 학생 조회
    public StudentDto.GetResponse getStudent(Long studentId) {
        Student student = studentRepository.findByStudentId(studentId);
        if(student == null) {
            System.out.println("[ERROR] 존재하지 않는 학생입니다.");
            return null;
        }
        return StudentDto.GetResponse.fromObject(student);
    }
}
