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
public class CreateUserDto {
    private String username;

    public UserEntity toEntity() {
        return UserEntity
                .builder()
                .username(username)
                .build();
    }
}
