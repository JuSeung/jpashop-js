package jpabook.jpashop.service;

import java.util.List;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.MemberRepositoryOld;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor // 이 롬복 스타일로 권장
public class MemberService {

    private final MemberRepository memberRepository;

    // 생성자 인젝션
    // 장점 : 생성시 주입 해야 하기 때문에 챙길 수 있다.
    // 생성자가 하나이면 AutoWired가 없어도 된다.
    // final를 붙인다. 컴파일 시점에 생성 했는지 확인 가능ㅎ다.
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    /**
     * 회원가입
     *
     */
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId(); // 영속성 때문에 Id가 보장이됨
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원 입니다.");
        }
    }

    /**
     * 회원전체조회
     *
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 회원조회
     *
     */
    public Member findMember(Long id) {
        return memberRepository.findById(id).get();
    }

    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findById(id).get();
        member.setName(name);
    }

    public Member findOne(Long id) {
        return memberRepository.findById(id).get();
    }
}
