package si.feri.bikesharingusersservice;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import si.feri.bikesharingusersservice.users.UserController;
import si.feri.bikesharingusersservice.users.UserRepository;
import si.feri.bikesharingusersservice.users.UserService;
import si.feri.bikesharingusersservice.users.dto.CreateUserDto;
import si.feri.bikesharingusersservice.users.dto.UserDto;
import si.feri.bikesharingusersservice.users.entity.UserEntity;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class UserControllerTests {

    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @AfterEach
    public void tearDown() {
        userRepository.deleteAll();
    }

    CreateUserDto createUserDto = CreateUserDto.builder()
            .username("test")
            .email("test@mail.com")
            .pin(1234)
            .build();

    UserDto userDto = UserDto.builder()
            .id("test")
            .username("test")
            .email("test@mail.com")
            .pin(1234)
            .build();

    @Test
    void testCreateUser() {
        when(userService.save(any(UserEntity.class))).thenReturn(userDto);

        var user = userController.create(createUserDto);

        assert user != null;
        assertEquals(Objects.requireNonNull(user.getBody()).getUsername(), createUserDto.getUsername());
        assertEquals(Objects.requireNonNull(user.getBody()).getEmail(), createUserDto.getEmail());
        assertEquals(Objects.requireNonNull(user.getBody()).getPin(), createUserDto.getPin());
    }

    @Test
    void testGetUser() {
        when(userService.findById("test")).thenReturn(Optional.ofNullable(userDto));

        var user = userController.findOne("test");

        assert user != null;
        assertEquals(Objects.requireNonNull(user.getBody()).getUsername(), userDto.getUsername());
        assertEquals(Objects.requireNonNull(user.getBody()).getEmail(), userDto.getEmail());
        assertEquals(Objects.requireNonNull(user.getBody()).getPin(), userDto.getPin());
    }

    @Test
    void testUpdateUser() {
        when(userService.save(any(UserEntity.class))).thenReturn(userDto);
        when(userService.existsById("test")).thenReturn(true);

        var user = userController.update("test", createUserDto);

        assert user != null;
        assertEquals(Objects.requireNonNull(user.getBody()).getUsername(), createUserDto.getUsername());
        assertEquals(Objects.requireNonNull(user.getBody()).getEmail(), createUserDto.getEmail());
        assertEquals(Objects.requireNonNull(user.getBody()).getPin(), createUserDto.getPin());
    }

    @Test
    void testDeleteUser() {
        when(userService.existsById("test")).thenReturn(true);

        var user = userController.delete("test");

        assert user != null;
        assertEquals(user.getStatusCode().value(), 200);
    }

    @Test
    void testGetAllUsers() {
        var users = new ArrayList<UserDto>();
        users.add(userDto);

        when(userService.findAll()).thenReturn(users);

        var allUsers = userController.findAll();

        assert allUsers != null;
        assertEquals(users.spliterator().getExactSizeIfKnown(), 1);
    }

    @Test
    void testGetUserNotFound() {
        when(userService.findById("test")).thenReturn(Optional.empty());

        var user = userController.findOne("test");

        assert user != null;
        assertEquals(user.getStatusCode().value(), 404);
    }

    @Test
    void testUpdateUserNotFound() {
        when(userService.existsById("test")).thenReturn(false);

        var user = userController.update("test", createUserDto);

        assert user != null;
        assertEquals(user.getStatusCode().value(), 404);
    }

    @Test
    void testDeleteUserNotFound() {
        when(userService.existsById("test")).thenReturn(false);

        var user = userController.delete("test");

        assert user != null;
        assertEquals(user.getStatusCode().value(), 404);
    }

    @Test
    void testGetAllUsersEmpty() {
        var users = new ArrayList<UserDto>();

        when(userService.findAll()).thenReturn(users);

        var allUsers = userController.findAll();

        assert allUsers != null;
        assertEquals(users.spliterator().getExactSizeIfKnown(), 0);
    }
}
