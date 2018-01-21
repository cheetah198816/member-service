import dto.request.CreateMemberRequest;
import dto.request.UpdateMemberRequest;
import dto.response.*;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.Charset;
import java.text.ParseException;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by chetan on 20.01.2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = MemberApplication.class)
public class MemberIntegrationTest {

    @Autowired
    TestRestTemplate restTemplate;

    private String username = "user";

    private String password = "abcd1234";

    @Test
    public void create_member_test() throws ParseException {

        CreateMemberResponse createMemberResponse = getCreateMemberResponse();

        assertThat(createMemberResponse.getMemberId()).isNotZero();
    }

    private CreateMemberResponse getCreateMemberResponse() throws ParseException {
        CreateMemberRequest createMemberRequest = TestHelper.createSampleMemberRequest();
        HttpEntity<CreateMemberRequest> createMemberRequestHttpEntity = new HttpEntity<>(createMemberRequest, createHeaders(username, password));
        ResponseEntity<CreateMemberResponse> createMemberResponseResponseEntity = restTemplate.exchange
                ("/api/createMember", HttpMethod.POST, createMemberRequestHttpEntity, CreateMemberResponse.class);
        return createMemberResponseResponseEntity.getBody();
    }

    @Test
    public void listAllMembers_test() throws ParseException {
        getCreateMemberResponse();

        ResponseEntity<GetAllMembersResponse> getAllMembersResponseResponseEntity = restTemplate.exchange
                ("/api/listMembers", HttpMethod.GET, new HttpEntity<Object>(createHeaders(username, password)), GetAllMembersResponse.class);
        GetAllMembersResponse getAllMembersResponse = getAllMembersResponseResponseEntity.getBody();

        assertThat(getAllMembersResponse).isNotNull();
        assertThat(getAllMembersResponse.getMemberList()).isNotEmpty();
    }

    @Test
    public void getMemberById_test() throws ParseException {

        CreateMemberResponse createMemberResponse = getCreateMemberResponse();
        Long memberId = createMemberResponse.getMemberId();

        GetMemberResponse getMemberResponse = getGetMemberResponse(memberId);

        assertThat(getMemberResponse).isNotNull();
        assertThat(getMemberResponse.getMember()).isNotNull();
        assertThat(getMemberResponse.getMember().getMemberId()).isEqualTo(memberId);
    }

    @Test
    public void getMemberById_Id_Not_Found_test() throws ParseException {
        Long memberId = 3l;

        ResponseEntity<Object> getMemberResponseResponseEntity = restTemplate.exchange
                ("/api/listMember/" + memberId, HttpMethod.GET, new HttpEntity<Object>(createHeaders(username, password)), Object.class);

        assertThat(getMemberResponseResponseEntity.getStatusCodeValue()).isEqualTo(404);
    }

    private GetMemberResponse getGetMemberResponse(Long memberId) {
        ResponseEntity<GetMemberResponse> getMemberResponseResponseEntity = restTemplate.exchange
                ("/api/listMember/" + memberId, HttpMethod.GET, new HttpEntity<Object>(createHeaders(username, password)), GetMemberResponse.class);
        return getMemberResponseResponseEntity.getBody();
    }

    @Test
    public void delete_member_test() throws ParseException {
        CreateMemberResponse createMemberResponse = getCreateMemberResponse();
        Long memberId = createMemberResponse.getMemberId();

        ResponseEntity<DeleteMemberResponse> deleteMemberResponseResponseEntity = restTemplate.exchange
                ("/api/deleteMember/" + memberId, HttpMethod.DELETE, new HttpEntity<Object>(createHeaders(username, password)), DeleteMemberResponse.class);

        DeleteMemberResponse deleteMemberResponse = deleteMemberResponseResponseEntity.getBody();

        assertThat(deleteMemberResponse.getMessage()).isEqualTo("OK");

        GetMemberResponse getMemberResponse = getGetMemberResponse(memberId);

        assertThat(getMemberResponse.getMember()).isNull();
    }

    @Test
    public void delete_member_id_not_found_test() {
        Long memberId = 3l;
        ResponseEntity<Object> deleteMemberResponseResponseEntity = restTemplate.exchange
                ("/api/deleteMember/" + memberId, HttpMethod.DELETE, new HttpEntity<Object>(createHeaders(username, password)), Object.class);

        assertThat(deleteMemberResponseResponseEntity.getStatusCodeValue()).isEqualTo(502);
    }

    @Test
    public void update_member_id_not_found_test() throws ParseException {

        Long memberId = 3l;
        UpdateMemberRequest updateMemberRequest = TestHelper.createSampleUpdateMemberRequest();
        HttpEntity<UpdateMemberRequest> updateMemberRequestHttpEntity = new HttpEntity<>(updateMemberRequest, createHeaders(username, password));

        ResponseEntity<Object> updateMemberResponseResponseEntity = restTemplate.exchange
                ("/api/updateMember/" + memberId, HttpMethod.PUT, updateMemberRequestHttpEntity, Object.class);

        assertThat(updateMemberResponseResponseEntity.getStatusCodeValue()).isEqualTo(404);
    }

    @Test
    public void update_member_test() throws ParseException {
        CreateMemberResponse createMemberResponse = getCreateMemberResponse();
        Long memberId = createMemberResponse.getMemberId();

        UpdateMemberRequest updateMemberRequest = TestHelper.createSampleUpdateMemberRequest();
        HttpEntity<UpdateMemberRequest> updateMemberRequestHttpEntity = new HttpEntity<>(updateMemberRequest, createHeaders(username, password));

        ResponseEntity<UpdateMemberResponse> updateMemberResponseResponseEntity = restTemplate.exchange
                ("/api/updateMember/" + memberId, HttpMethod.PUT, updateMemberRequestHttpEntity, UpdateMemberResponse.class);

        UpdateMemberResponse updateMemberResponse = updateMemberResponseResponseEntity.getBody();

        assertThat(updateMemberResponse.getMessage()).isEqualTo("OK");

        GetMemberResponse getMemberResponse = getGetMemberResponse(memberId);

        assertThat(getMemberResponse.getMember().getFirstName()).isEqualTo(updateMemberRequest.getMember().getFirstName());
        assertThat(getMemberResponse.getMember().getLastName()).isEqualTo(updateMemberRequest.getMember().getLastName());
        assertThat(getMemberResponse.getMember().getMemberId()).isEqualTo(memberId);
        assertThat(getMemberResponse.getMember().getDateOfBirth()).isEqualTo(updateMemberRequest.getMember().getDateOfBirth());
        assertThat(getMemberResponse.getMember().getPostalCode()).isEqualTo(updateMemberRequest.getMember().getPostalCode());
    }

    HttpHeaders createHeaders(String username, String password) {
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("US-ASCII")));
            String authHeader = "Basic " + new String(encodedAuth);
            set("Authorization", authHeader);
        }};
    }
}
