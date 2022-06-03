package marcat.members.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import marcat.members.dao.MemberDAO;
import marcat.members.dto.JusoDTO;
import marcat.members.dto.KakaoResponseDTO;
import marcat.members.vo.Member;
import marcat.members.vo.MemberImages;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final MemberDAO memberDAO;
    private final KakaoLoginService kakao;

    // 일반 회원 로그인 처리
    @Override
    public Member login(String loginId, String loginPw) {
        List<Member> members = memberDAO.findByUId(loginId);
        if(members == null) {
            return null;
        }
        if(members.get(0).getPasswd().equals(loginPw)) {
            return members.get(0);
        } else {
            return null;
        }
    }

    // PK로 멤버 조회
    @Override
    public Member findById(Long id) {
        try {
            Member member = memberDAO.findById(id);
            return member;
        } catch (Exception e) {
            return null;
        }
    }

    // 유저 아이디로 멤버 조회
    @Override
    public Member findByUid(String loginId) {
        try {
            Member resultMember = null;
            List<Member> resultByPhone = memberDAO.findByUId(loginId);
            if (!resultByPhone.isEmpty()) {
                resultMember = resultByPhone.get(0);
            }
            return resultMember;
        } catch (Exception e) {
            return null;
        }
    }

    // 핸드폰 번호로 멤버 조회
    @Override
    public Member findByPhone(String phoneNum) {
        try {
            Member resultMember = null;
            List<Member> resultByPhone = memberDAO.findByPhone(phoneNum);
            if (!resultByPhone.isEmpty()) {
                resultMember = resultByPhone.get(0);
            }
            return resultMember;
        } catch (Exception e) {
            return null;
        }
    }

    // 동일한 닉네임 있는지 확인
    @Override
    public int checkNick(String nickName) {
        return memberDAO.checkNick(nickName);
    }

    // 회원가입
    @Override
    @Transactional
    public int sigunUpMember(Member member) {
        int result = memberDAO.signUp(member);
        return result;
    }

    // 주소 검색
    @Override
    public List<JusoDTO> findJuso(String juso) {
        return memberDAO.findJuso(juso);
    }

    // 카카오유저 회원 조회
    @Override
    public KakaoResponseDTO findKakaoMember(String code) {
        KakaoResponseDTO userInfo = kakao.getUserInfo(code);
        try {
            List<Member> byUidJustMember = memberDAO.findByUidJustMember(userInfo.getId());
            if (!byUidJustMember.isEmpty()) {
                userInfo.setMemberId(byUidJustMember.get(0).getId());
                userInfo.setRoleStatus(byUidJustMember.get(0).getRoleStatus());
                userInfo.setActivated(byUidJustMember.get(0).getActivated());
                userInfo.setSignUpStatus(true);
                return userInfo;
            } else {
                return userInfo;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 비밀번호 업데이트
    @Override
    @Transactional
    public int updatePw(Member member) {
        return memberDAO.updatePw(member);
    }

    // 유저 아이디로 조인 없이 멤버만 조회
    @Override
    public List<Member> findByUidJustMember(String uId) {
        try {
            List<Member> member = memberDAO.findByUidJustMember(uId);
            return member;
        } catch (Exception e) {
            return null;
        }
    }

    // 이미지 등록
    @Override
    @Transactional
    public int insertMemImage(MemberImages memberImages) {
        return memberDAO.insertMemImage(memberImages);
    }

    // 멤버 삭제
    @Override
    @Transactional
    public void delete(Long id) {
        memberDAO.delete(id);
    }
}
