package marcat.members.service;

import lombok.extern.slf4j.Slf4j;
import marcat.members.vo.Activated;
import marcat.members.vo.Member;
import marcat.members.vo.RoleStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"})
class MemberServiceImplTest {

    @Autowired
    private MemberService memberService;

    @Test
    void login() {

    }

    @Test
    void findById() {
        Member byId = memberService.findById(28L);
        System.out.println("byId = " + byId);
    }

    @Test
    void findByUidJustMember() {
        List<Member> findByUidJustMember = memberService.findByUidJustMember("adminaa");
        System.out.println("findByUidJustMember = " + findByUidJustMember.isEmpty());
    }

    @Test
    void findByUid() {
        Member user = memberService.findByUid("test");
        System.out.println("user = " + user);
    }

    @Test
    void findByPhone() {
        Member byPhone = memberService.findByPhone("01036563350");
        System.out.println("byPhone = " + byPhone);
    }

    @Test
    void deleteMember() {
        memberService.delete(6L);
    }

    @Test
    void checkNick() {
    }

    @Test
    void sigunUpMember() {
        Member member = new Member("11120740", "a1234", "a1234", "lee", "leetest", "01011111113", LocalDateTime.now(), RoleStatus.USER.getValue(), Activated.ACTIVITY.getValue(), null, null, null);
        log.info("member={}", member);
        int resultNum = memberService.sigunUpMember(member);
        log.info("resultNum={}", resultNum);
        log.info("member={}", member);
    }

    @Test
    void findJuso() {
    }

    @Test
    void findKakaoMember() {
    }

    @Test
    void updatePw() {
    }
}