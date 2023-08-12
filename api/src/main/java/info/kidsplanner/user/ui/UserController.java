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
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    public static final String USER_URI = "/users";
    private final UserService userService;

    @PostMapping
    public Mono<ResponseEntity<UserResponse>> createUser(@RequestBody @Valid UserRequest userRequest) {
        return userService.createUser(userRequest)
                        .map(UserController::createResponseEntity);
    }

    private static ResponseEntity<UserResponse> createResponseEntity(UserResponse userResponse) {
        final URI location = UriComponentsBuilder.fromUriString(USER_URI)
                .pathSegment("{userId}")
                .build(userResponse.getId());
        return ResponseEntity.created(location).body(userResponse);
    }
}
