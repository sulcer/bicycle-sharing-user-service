package si.feri.bikesharingusersservice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.logging.Logger;

@SpringBootApplication
public class BikeSharingUsersServiceApplication {
    private static final Logger log = Logger.getLogger(BikeSharingUsersServiceApplication.class.toString());

    public static void main(String[] args) {
        SpringApplication.run(BikeSharingUsersServiceApplication.class, args);
        log.info("BikeSharingUsersServiceApplication started");
    }

}
