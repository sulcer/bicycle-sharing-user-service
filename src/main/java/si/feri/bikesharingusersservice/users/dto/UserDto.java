package si.feri.bikesharingusersservice.users.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import si.feri.bikesharingusersservice.users.entity.UserEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String id;
    private String username;
    private String email;
    private int pin;

    public static UserEntity toEntity(UserDto userDto) {
        return UserEntity
                .builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .pin(userDto.getPin())
                .build();
    }
}
