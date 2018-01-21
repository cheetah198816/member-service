package service.impl;

import dto.member.MemberDto;
import dto.request.CreateMemberRequest;
import dto.request.UpdateMemberRequest;
import dto.response.*;
import mappers.MemberEntityMapper;
import model.MemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import repository.MemberRepository;
import service.MemberService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by chetan on 20.01.2018.
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public CreateMemberResponse createMember(CreateMemberRequest createMemberRequest) {
        // Validations for the first name, last name, postal code and date of birth can be done on the frontend.
        final CreateMemberResponse createMemberResponse = new CreateMemberResponse();
        final MemberEntity memberEntity = MemberEntityMapper.dto2entity(createMemberRequest.getMember());
        final MemberEntity savedMemberEntity = memberRepository.save(memberEntity);
        createMemberResponse.setMemberId(savedMemberEntity.getId());

        return createMemberResponse;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public GetAllMembersResponse listMembers() {
        final GetAllMembersResponse getAllMembersResponse = new GetAllMembersResponse();
        final List<MemberDto> memberDtoList = memberRepository.findAll().stream().map(memberEntity -> MemberEntityMapper.entity2dto(memberEntity)).collect(Collectors.toList());
        getAllMembersResponse.setMemberList(memberDtoList);

        return getAllMembersResponse;
    }

    @Transactional(readOnly = true)
    public MemberEntity findById(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException("Member Entity not found for the id " + memberId));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public GetMemberResponse getMemberById(Long memberId) {
        final GetMemberResponse getMemberResponse = new GetMemberResponse();
        final MemberDto memberDto = MemberEntityMapper.entity2dto(findById(memberId));
        getMemberResponse.setMember(memberDto);

        return getMemberResponse;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public UpdateMemberResponse updateMember(Long memberId, UpdateMemberRequest updateMemberRequest) {
        final UpdateMemberResponse updateMemberResponse = new UpdateMemberResponse();
        final MemberEntity memberEntity = findById(memberId);
        final MemberDto memberDto = updateMemberRequest.getMember();
        memberEntity.setFirstName(memberDto.getFirstName());
        memberEntity.setLastName(memberDto.getLastName());
        memberEntity.setPostalCode(memberDto.getPostalCode());
        memberEntity.setDateOfBirth(memberDto.getDateOfBirth());
        memberRepository.save(memberEntity);
        updateMemberResponse.setMessage("OK");

        return updateMemberResponse;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public DeleteMemberResponse deleteMember(Long memberId) {
        final DeleteMemberResponse deleteMemberResponse = new DeleteMemberResponse();
        memberRepository.delete(memberId);
        deleteMemberResponse.setMessage("OK");

        return deleteMemberResponse;
    }
}
