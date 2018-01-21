package dto.member;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

/**
 * Created by chetan on 20.01.2018.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@JsonClassDescription("Member class")
public class MemberDto {

    @JsonPropertyDescription("Id of the member")
    private Long memberId;

    @NonNull
    @JsonPropertyDescription("First Name")
    private String firstName;

    @NonNull
    @JsonPropertyDescription("Last Name")
    private String lastName;

    @NonNull
    @JsonPropertyDescription("Postal Code")
    private Integer postalCode;

    @NonNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonPropertyDescription("Date Of Birth")
    private Date dateOfBirth;
}

