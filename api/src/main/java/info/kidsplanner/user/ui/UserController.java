package info.kidsplanner.user.ui;

import info.kidsplanner.user.application.UserService;
import info.kidsplanner.user.application.dto.UserRequest;
import info.kidsplanner.user.application.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody @Valid UserRequest userRequest) {
        final UserResponse userResponse = userService.create(userRequest);
        final URI uri = URI.create("/users/" + userResponse.getId());
        return ResponseEntity.created(uri).body(userResponse);
    }
}
