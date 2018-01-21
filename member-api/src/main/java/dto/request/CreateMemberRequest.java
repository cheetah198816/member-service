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
@NoArgsConstructor
@JsonClassDescription("Member Dto Request to save the member.")
public class CreateMemberRequest {

    @NonNull
    @JsonPropertyDescription("Member Dto to be saved")
    MemberDto member;
}
