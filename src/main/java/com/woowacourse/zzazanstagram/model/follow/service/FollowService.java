package com.woowacourse.zzazanstagram.model.follow.service;

import com.woowacourse.zzazanstagram.model.follow.domain.Follow;
import com.woowacourse.zzazanstagram.model.follow.dto.FollowRequest;
import com.woowacourse.zzazanstagram.model.follow.dto.FollowResponse;
import com.woowacourse.zzazanstagram.model.follow.repository.FollowRepository;
import com.woowacourse.zzazanstagram.model.member.domain.Member;
import com.woowacourse.zzazanstagram.model.member.service.MemberAssembler;
import com.woowacourse.zzazanstagram.model.member.service.MemberService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FollowService {
    private MemberService memberService;
    private FollowRepository followRepository;

    public FollowService(MemberService memberService, FollowRepository followRepository) {
        this.memberService = memberService;
        this.followRepository = followRepository;
    }

    // TODO refactoring
    public void save(FollowRequest followRequest) {
        Member followee = findMember(followRequest.getFolloweeId());
        Member follower = findMember(followRequest.getFollowerId());
        Follow follow = new Follow(followee, follower);

        Optional<Follow> maybeFollow = followRepository.findByFolloweeAndFollower(followee, follower);

        if (maybeFollow.isPresent()) {
            followRepository.delete(maybeFollow.get());
            return;
        }

        followRepository.save(follow);
    }

    public List<FollowResponse> findFollowers(Long id) {
        Member member = findMember(id);
        List<Follow> follows = followRepository.findByFollower(member);
        return follows.stream()
                .map(Follow::getFollowee)
                .map(MemberAssembler::assemble)
                .map(FollowResponse::new)
                .collect(Collectors.toList());
    }

    public List<FollowResponse> findFollowings(Long id) {
        Member member = findMember(id);
        List<Follow> follows = followRepository.findByFollowee(member);
        return follows.stream()
                .map(Follow::getFollower)
                .map(MemberAssembler::assemble)
                .map(FollowResponse::new)
                .collect(Collectors.toList());
    }

    private Member findMember(Long id) {
        return memberService.find(id);
    }
}
