package my.com.tcsens.vehiclemanagement.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "user_profile")
public class UserModel {
    @Id
    private int id;

    @Column(name = "login_id")
    private String loginId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "hash_pwd")
    private String userPassword;

}
