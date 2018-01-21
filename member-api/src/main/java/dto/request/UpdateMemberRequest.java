package dto.request;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import dto.member.MemberDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Created by chetan on 20.01.2018.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonClassDescription("Update member request")
@NoArgsConstructor
public class UpdateMemberRequest {

    @NonNull
    @JsonPropertyDescription("Member dto to be updated")
    MemberDto member;
}
