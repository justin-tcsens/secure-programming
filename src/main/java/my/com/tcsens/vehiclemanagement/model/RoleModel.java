package my.com.tcsens.vehiclemanagement.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_role")
public class RoleModel {
    @Id
    private int id;

    @Column(name = "role_name")
    private String roleName;

}
