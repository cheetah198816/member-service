package api;

import dto.request.CreateMemberRequest;
import dto.request.UpdateMemberRequest;
import dto.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.MemberService;

/**
 * Created by chetan on 20.01.2018.
 */
@RestController
@RequestMapping("api/")
public class MemberApi {

    @Autowired
    private MemberService memberService;

    /**
     * Creates a new member.
     *
     * @param createMemberRequest request dto containing the member details.
     * @return response containing member Id of the saved response.
     */
    @RequestMapping(path = "createMember", consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
    public CreateMemberResponse createMember(@RequestBody CreateMemberRequest createMemberRequest) {
        return memberService.createMember(createMemberRequest);
    }

    /**
     * Lists all the existing members.
     *
     * @return response containing the list of existing members.
     */
    @RequestMapping(path = "listMembers", method = RequestMethod.GET, produces = "application/json")
    public GetAllMembersResponse listAllMembers() {
        return memberService.listMembers();
    }

    /**
     * Lists a member by id.
     *
     * @param memberId id of the member.
     * @return response containing the member per id.
     */
    @RequestMapping(path = "listMember/{memberId}", produces = "application/json", method = RequestMethod.GET)
    public GetMemberResponse listMemberById(@PathVariable("memberId") Long memberId) {
        return memberService.getMemberById(memberId);
    }

    /**
     * Updates an existing member.
     *
     * @param memberId            id of the member to be updated.
     * @param updateMemberRequest reqpeust dto containing the updated details of the member.
     * @return response containing the status of updation.
     */
    @RequestMapping(path = "updateMember/{memberId}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public UpdateMemberResponse updateMember(@PathVariable("memberId") Long memberId, @RequestBody UpdateMemberRequest updateMemberRequest) {
        return memberService.updateMember(memberId, updateMemberRequest);
    }

    /**
     * Deletes an existing member.
     *
     * @param memberId id of the member to be deleted.
     * @return response containing the status.
     */
    @RequestMapping(path = "deleteMember/{memberId}", produces = "application/json", method = RequestMethod.DELETE)
    public DeleteMemberResponse deleteMember(@PathVariable("memberId") Long memberId) {
        return memberService.deleteMember(memberId);
    }
}
