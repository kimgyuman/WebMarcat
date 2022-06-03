package marcat.members.service;

import marcat.members.dto.JusoDTO;
import marcat.members.dto.KakaoResponseDTO;
import marcat.members.vo.Member;
import marcat.members.vo.MemberImages;

import java.util.List;

public interface MemberService {

    Member login(String loginId, String loginPw);

    Member findById(Long id);

    Member findByUid(String loginId);

    Member findByPhone(String phoneNum);

    int checkNick(String nickName);

    int sigunUpMember(Member member);

    List<JusoDTO> findJuso(String juso);

    KakaoResponseDTO findKakaoMember(String code);

    int updatePw(Member member);

    List<Member> findByUidJustMember(String uId);

    int insertMemImage(MemberImages memberImages);

    void delete(Long id);
}
