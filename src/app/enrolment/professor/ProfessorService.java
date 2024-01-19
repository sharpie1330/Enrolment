package app.enrolment.professor;

public class ProfessorService {
    private final ProfessorRepository professorRepository = new ProfessorRepository();

    // 교수 등록
    public Long registerProfessor(ProfessorDto.SignUpRequest request) {
        Professor professor = request.toObject();
        Professor savedProfessor = professorRepository.saveProfessor(professor);
        System.out.println("[ALERT] 교수 회원가입이 완료되었습니다.");
        return savedProfessor.getId();
    }

    // 교수 로그인
    public Long professorLogin(ProfessorDto.LoginRequest request) {
        Professor professor = professorRepository.findByProfessorLoginId(request.getLoginId());
        if (professor != null) {
            if (professor.getPassword().equals(request.getPassword())) {
                return professor.getId();
            }
        }
        System.out.println("아이디나 비밀번호가 틀렸습니다. 다시 입력하거나, 회원가입한 적 없다면 회원가입하세요.");
        return null;
    }

    // 교수 조회
    public ProfessorDto.GetResponse getProfessor(Long professorId) {
        Professor professor = professorRepository.findByProfessorId(professorId);
        if(professor == null) {
            System.out.println("[ERROR] 존재하지 않는 교수입니다.");
            return null;
        }
        return ProfessorDto.GetResponse.fromObject(professor);
    }
}
