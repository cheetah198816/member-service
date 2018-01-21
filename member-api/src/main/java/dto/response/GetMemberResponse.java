package dto.response;

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
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonClassDescription("Get member by id response.")
public class GetMemberResponse {

    @NonNull
    @JsonPropertyDescription("member dto of the existing member")
    MemberDto member;
}
