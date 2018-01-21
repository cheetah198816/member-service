package dto.response;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Created by chetan on 20.01.2018.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@JsonClassDescription("Delete Member Response.")
public class DeleteMemberResponse {

    @NonNull
    @JsonPropertyDescription("Status message")
    private String message;
}
