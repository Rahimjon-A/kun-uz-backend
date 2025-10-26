package dasturlash.uz.kunuz.entity;


import dasturlash.uz.kunuz.enums.ProfileStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "profile")
public class ProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "username", nullable = false)
    private String username;// login,email,phone

    @Column(name = "password", nullable = false)
    private String password; //

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ProfileStatus status = ProfileStatus.ACTIVE;

    @Column(name = "photo_id")
    private String photoId; // Will do it later (in attach topic)

    @Column(name = "visible", nullable = false)
    private Boolean visible = true;

    @CreationTimestamp
    @Column(name = "created_date")
    private LocalDateTime createdDate;

//    @OneToMany(mappedBy = "profile")
//    private List<ProfileRoleEntity> roleList;
}

