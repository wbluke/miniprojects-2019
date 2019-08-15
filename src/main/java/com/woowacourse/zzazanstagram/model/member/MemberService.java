package com.woowacourse.zzazanstagram.model.member;

import com.woowacourse.zzazanstagram.model.member.exception.MemberException;
import com.woowacourse.zzazanstagram.model.member.vo.Email;
import com.woowacourse.zzazanstagram.model.member.vo.NickName;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {
    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    private Optional<Member> findByEmail(String email) {
        return memberRepository.findByEmail(Email.of(email));
    }

    public void save(MemberSignUpRequest memberSignupRequest) {
        checkEnrolledEmail(memberSignupRequest.getEmail());
        checkEnrolledNickName(memberSignupRequest.getNickName());
        Member member = MemberAssembler.toEntity(memberSignupRequest);
        memberRepository.save(member);
    }

    private void checkEnrolledEmail(String email) {
        if (findByEmail(email).isPresent()) {
            throw new MemberException("이미 존재하는 이메일 입니다.");
        }
    }

    private void checkEnrolledNickName(String nickName) {
        if (findByNickName(nickName).isPresent()) {
            throw new MemberException("이미 존재하는 닉네임 입니다.");
        }
    }

    private Optional<Member> findByNickName(String nickName) {
        return memberRepository.findByNickName(NickName.of(nickName));
    }
}
