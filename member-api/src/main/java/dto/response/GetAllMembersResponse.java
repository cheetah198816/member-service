package dto.response;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import dto.member.MemberDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

/**
 * Created by chetan on 20.01.2018.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@JsonClassDescription("Fetch All the Member response.")
public class GetAllMembersResponse {

    @NonNull
    @JsonPropertyDescription("List of existing members.")
    List<MemberDto> memberList;
}
