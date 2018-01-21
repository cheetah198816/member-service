import dto.member.MemberDto;
import dto.request.CreateMemberRequest;
import dto.request.UpdateMemberRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chetan on 20.01.2018.
 */
public class TestHelper {

    public static CreateMemberRequest createSampleMemberRequest() throws ParseException {
        CreateMemberRequest createMemberRequest = new CreateMemberRequest();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date dateOfBirth = simpleDateFormat.parse("16-12-1988 01:00:00");
        MemberDto memberDto = new MemberDto();
        memberDto.setFirstName("Raj");
        memberDto.setLastName("Sura");
        memberDto.setPostalCode(81669);
        memberDto.setDateOfBirth(dateOfBirth);
        createMemberRequest.setMember(memberDto);

        return createMemberRequest;
    }

    public static UpdateMemberRequest createSampleUpdateMemberRequest() throws ParseException {
        UpdateMemberRequest updateMemberRequest = new UpdateMemberRequest();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date dateOfBirth = simpleDateFormat.parse("16-12-1987 01:00:00");
        MemberDto memberDto = new MemberDto();
        memberDto.setFirstName("Raj1234");
        memberDto.setLastName("Sura1234");
        memberDto.setPostalCode(81645);
        memberDto.setDateOfBirth(dateOfBirth);
        updateMemberRequest.setMember(memberDto);

        return updateMemberRequest;
    }
}
