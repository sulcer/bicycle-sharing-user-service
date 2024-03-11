package si.feri.bikesharingusersservice.users;
import org.springframework.data.repository.CrudRepository;
import si.feri.bikesharingusersservice.users.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, String> {
}
