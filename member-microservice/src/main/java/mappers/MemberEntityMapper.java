package mappers;

import dto.member.MemberDto;
import model.MemberEntity;
import org.springframework.stereotype.Component;

/**
 * Created by chetan on 20.01.2018.
 */
@Component
public class MemberEntityMapper {

    public static MemberEntity dto2entity(MemberDto memberDto) {
        final MemberEntity memberEntity = new MemberEntity();
        memberEntity.setFirstName(memberDto.getFirstName());
        memberEntity.setLastName(memberDto.getLastName());
        memberEntity.setDateOfBirth(memberDto.getDateOfBirth());
        memberEntity.setPostalCode(memberDto.getPostalCode());

        return memberEntity;
    }

    public static MemberDto entity2dto(MemberEntity memberEntity) {
        final MemberDto memberDto = new MemberDto();
        memberDto.setMemberId(memberEntity.getId());
        memberDto.setFirstName(memberEntity.getFirstName());
        memberDto.setLastName(memberEntity.getLastName());
        memberDto.setPostalCode(memberEntity.getPostalCode());
        memberDto.setDateOfBirth(memberEntity.getDateOfBirth());

        return memberDto;
    }
}
