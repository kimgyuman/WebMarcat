package marcat.members.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import marcat.members.dto.JusoDTO;
import marcat.members.vo.Member;
import marcat.members.vo.MemberImages;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberDAOImpl implements MemberDAO {

    private final SqlSessionTemplate sqlSessionTemplate;
    private final String mapperLocation = "marcat.members.dao.MemberDAO.";

    @Override
    public List<Member> allList() {
        return sqlSessionTemplate.selectList(mapperLocation+"AllList");
    }

    @Override
    public Member findById(Long id) {
        return sqlSessionTemplate.selectOne(mapperLocation+"FindMemberWithId",id);
    }

    @Override
    public List<Member> findByUId(String loginId) {
        try {
            List<Member> result = sqlSessionTemplate.selectList(mapperLocation + "Login", loginId);
            if (result.isEmpty()) {
                return null;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Member> findByPhone(String phoneNum) {
        try {
            List<Member> result = sqlSessionTemplate.selectList(mapperLocation + "PhoneNum", phoneNum);
            if (result.isEmpty()) {
                return null;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int checkNick(String nickName) {
        int o = sqlSessionTemplate.selectOne(mapperLocation + "NickName", nickName);
        return o;
    }

    @Override
    public int signUp(Member member) {
        int result = sqlSessionTemplate.insert(mapperLocation + "SignUp", member);
        return result;
    }

    @Override
    public List<JusoDTO> findJuso(String juso) {
        return sqlSessionTemplate.selectList(mapperLocation + "FindJuso", juso);
    }

    @Override
    public Member findByKakaoToken(String code) {
        return null;
    }

    @Override
    public int updatePw(Member member) {
        int updateResult = sqlSessionTemplate.update(mapperLocation + "UpdatePw", member);
        return updateResult;
    }

    @Override
    public List<Member> findByUidJustMember(String uId) {
        return sqlSessionTemplate.selectList(mapperLocation+"FindJustMember",uId);
    }

    @Override
    public int insertMemImage(MemberImages memberImages) {
        return sqlSessionTemplate.insert(mapperLocation+"InsertImages", memberImages);
    }

    @Override
    public void delete(Long id) {
        sqlSessionTemplate.delete(mapperLocation+"DeleteMember",id);
    }
}
