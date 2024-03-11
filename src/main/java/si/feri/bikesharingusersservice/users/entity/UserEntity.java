package si.feri.bikesharingusersservice.users.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import si.feri.bikesharingusersservice.users.dto.UserDto;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String username;

    public UserDto toDto() {
        return UserDto
                .builder()
                .id(id)
                .username(username)
                .build();
    }
}
