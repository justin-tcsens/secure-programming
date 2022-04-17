package my.com.tcsens.vehiclemanagement.repository;

import my.com.tcsens.vehiclemanagement.model.RoleModel;
import my.com.tcsens.vehiclemanagement.model.UserModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<RoleModel, Integer> {

    @Query(value = "select * from user_role a " +
            "where a.user_profile_id = :userId", nativeQuery = true)
    List<RoleModel> getUserByLoginId(int userId);
    
}
