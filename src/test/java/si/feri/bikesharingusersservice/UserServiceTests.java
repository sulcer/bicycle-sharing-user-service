package si.feri.bikesharingusersservice;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import si.feri.bikesharingusersservice.users.UserRepository;
import si.feri.bikesharingusersservice.users.UserService;
import si.feri.bikesharingusersservice.users.dto.CreateUserDto;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    CreateUserDto createUserDto = CreateUserDto.builder()
            .username("test")
            .email("test@mail.com")
            .pin(1234)
            .build();

    @AfterEach
    public void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void testSaveUser() {
        var user = userService.save(createUserDto.toEntity());

        assert user != null;
        assertEquals(user.getUsername(), createUserDto.getUsername());
        assertEquals(user.getEmail(), createUserDto.getEmail());
        assertEquals(user.getPin(), createUserDto.getPin());
    }

    @Test
    void testDeleteUser() {
        var user = userService.save(createUserDto.toEntity());

        assert user != null;
        assertEquals(user.getUsername(), createUserDto.getUsername());
        assertEquals(user.getEmail(), createUserDto.getEmail());
        assertEquals(user.getPin(), createUserDto.getPin());

        userService.deleteById(user.getId());

        assert userRepository.findById(user.getId()).isEmpty();
    }

    @Test
    void testExistsById() {
        var user = userService.save(createUserDto.toEntity());

        assert user != null;
        assertEquals(user.getUsername(), createUserDto.getUsername());
        assertEquals(user.getEmail(), createUserDto.getEmail());
        assertEquals(user.getPin(), createUserDto.getPin());

        assert userService.existsById(user.getId());
    }

    @Test
    void testFindAll() {
        var user = userService.save(createUserDto.toEntity());

        assert user != null;
        assertEquals(user.getUsername(), createUserDto.getUsername());
        assertEquals(user.getEmail(), createUserDto.getEmail());
        assertEquals(user.getPin(), createUserDto.getPin());

        var users = userService.findAll();

        assert users.iterator().hasNext();
    }

    @Test
    void testFindById() {
        var user = userService.save(createUserDto.toEntity());

        assert user != null;
        assertEquals(user.getUsername(), createUserDto.getUsername());
        assertEquals(user.getEmail(), createUserDto.getEmail());
        assertEquals(user.getPin(), createUserDto.getPin());

        var userDto = userService.findById(user.getId());

        assert userDto.isPresent();
        assertEquals(userDto.get().getUsername(), createUserDto.getUsername());
        assertEquals(userDto.get().getEmail(), createUserDto.getEmail());
        assertEquals(userDto.get().getPin(), createUserDto.getPin());
    }

}
