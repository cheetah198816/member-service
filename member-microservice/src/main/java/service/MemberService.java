package service;

import dto.request.CreateMemberRequest;
import dto.request.UpdateMemberRequest;
import dto.response.*;

/**
 * Created by chetan on 20.01.2018.
 */
public interface MemberService {

    /**
     * Creates a new member.
     *
     * @param createMemberRequest request containing the member details.
     * @return response containg the new member id.
     */
    CreateMemberResponse createMember(CreateMemberRequest createMemberRequest);

    /**
     * lists all the existing members.
     *
     * @return response dto containing the list of all existing members.
     */
    GetAllMembersResponse listMembers();

    /**
     * Fetches a member per id.
     *
     * @param memberId id of the member.
     * @return response containinf the member per id.
     */
    GetMemberResponse getMemberById(Long memberId);

    /**
     * Updates a particular member
     *
     * @param memberId            id of the member to be updated.
     * @param updateMemberRequest update request dto contaning the details of the member to be updated.
     * @return response containing the status.
     */
    UpdateMemberResponse updateMember(Long memberId, UpdateMemberRequest updateMemberRequest);

    /**
     * Deletes an existing member.
     *
     * @param memberId id of the member to be deleted.
     * @return response containing the status.
     */
    DeleteMemberResponse deleteMember(Long memberId);
}
