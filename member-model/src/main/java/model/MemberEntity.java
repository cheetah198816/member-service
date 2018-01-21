package model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chetan on 20.01.2018.
 */
@Data
@Entity
@Table(name = "member")
@EqualsAndHashCode
@NoArgsConstructor
@SequenceGenerator(name = "seq_member", sequenceName = "seq_member")
public class MemberEntity {

    @Id
    @GeneratedValue(generator = "seq_member")
    private Long id;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private Integer postalCode;

    @NonNull
    private Date dateOfBirth;
}
