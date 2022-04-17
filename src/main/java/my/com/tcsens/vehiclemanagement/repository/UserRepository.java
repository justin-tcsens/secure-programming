package my.com.tcsens.vehiclemanagement.repository;

import my.com.tcsens.vehiclemanagement.model.UserModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserModel, Integer> {

    @Query(value = "select * from user_profile", nativeQuery = true)
    List<UserModel> getUsers();

    @Query(value = "select * from user_profile a " +
            "where a.login_id = :loginId", nativeQuery = true)
    UserModel getUserByLoginId(String loginId);

}
