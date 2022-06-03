package marcat.members.dao;

import marcat.members.dto.JusoDTO;
import marcat.members.vo.Member;
import marcat.members.vo.MemberImages;

import java.util.List;


public interface MemberDAO {

    List<Member> allList();

    Member findById(Long id);

    List<Member> findByUId(String loginId);

    List<Member> findByPhone(String phoneNum);

    int checkNick(String nickName);

    int signUp(Member member);

    List<JusoDTO> findJuso(String juso);

    Member findByKakaoToken(String code);

    int updatePw(Member member);

    List<Member> findByUidJustMember(String uId);

    int insertMemImage(MemberImages memberImages);

    void delete(Long id);
}
