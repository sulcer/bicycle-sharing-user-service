package si.feri.bikesharingusersservice.users;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import si.feri.bikesharingusersservice.users.dto.CreateUserDto;
import si.feri.bikesharingusersservice.users.dto.UserDto;
import java.util.logging.Logger;

@RestController
@CrossOrigin
@RequestMapping
@Tag(name = "Users")
public class UserController {
    private static final Logger log = Logger.getLogger(UserController.class.getName());
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public Iterable<UserDto> findAll() {
        log.info("Finding all users");

        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> findOne(@PathVariable String id) {
        log.info(STR."Finding user by id: \{id}");

        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/users")
    public ResponseEntity<UserDto> create(@RequestBody CreateUserDto userDto) {
        var user = userService.save(userDto.toEntity());

        log.info(STR."Creating user with id: \{user.getId()}");

        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserDto> update(@PathVariable String id, @RequestBody CreateUserDto userDto) {
        if (!userService.existsById(id)) {
            log.info(STR."User with id: \{id} not found");

            return ResponseEntity.notFound().build();
        }

        var user = userDto.toEntity();
        user.setId(id);

        log.info(STR."Updating user with id: \{id}");

        return ResponseEntity.ok(userService.save(user));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if (!userService.existsById(id)) {
            log.info(STR."User with id: \{id} not found");

            return ResponseEntity.notFound().build();
        }

        userService.deleteById(id);

        log.info(STR."Deleting user with id: \{id}");

        return ResponseEntity.ok().build();
    }
}
