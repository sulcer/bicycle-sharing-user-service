package si.feri.bikesharingusersservice.users;
import org.springframework.stereotype.Service;
import si.feri.bikesharingusersservice.users.dto.UserDto;
import si.feri.bikesharingusersservice.users.entity.UserEntity;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserDto> findById(String id) {
        return userRepository.findById(id).map(UserEntity::toDto);
    }

    public UserDto save(UserEntity user) {
        return userRepository.save(user).toDto();
    }

    public void deleteById(String id) {
        userRepository.deleteById(id);
    }

    public Iterable<UserDto> findAll() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(UserEntity::toDto)
                .collect(Collectors.toList());
    }

    public boolean existsById(String id) {
        return userRepository.existsById(id);
    }
}
