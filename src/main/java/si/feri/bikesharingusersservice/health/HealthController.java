package si.feri.bikesharingusersservice.health;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.InetAddress;
import java.util.logging.Logger;

@CrossOrigin
@RestController
@Tag(name = "Health")
public class HealthController {
    private static final Logger log = Logger.getLogger(HealthController.class.toString());

    @GetMapping("/health")
    public ResponseEntity<String> getServiceInfo() {
        String location="??";

        try {
            location= STR."\{InetAddress.getLocalHost().getHostName()}:\{InetAddress.getLocalHost().getHostAddress()}";
        } catch (Exception e) {
            log.info(()-> STR."Unable to get local address: \{e.getMessage()}");
        }

        return ResponseEntity.ok(STR."\{location}->\{this.getClass().toString()}");
    }
}

