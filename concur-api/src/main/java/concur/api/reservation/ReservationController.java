package concur.api.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/users/{userName}/reservations")
    public String reservations(@PathVariable String userName) {
        return reservationService.reserve(userName).toString();
    }

    @DeleteMapping("/users/{userName}/reservations")
    public String cancelReservation(@PathVariable String userName) {
        return reservationService.cancel(userName).toString();
    }
}
