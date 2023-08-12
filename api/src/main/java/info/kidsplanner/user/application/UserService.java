package info.kidsplanner.user.application;

import info.kidsplanner.user.Parent;
import info.kidsplanner.user.User;
import info.kidsplanner.user.UserRepository;
import info.kidsplanner.user.application.dto.UserRequest;
import info.kidsplanner.user.application.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Mono<UserResponse> createUser(UserRequest userRequest) {
        return Mono.just(toUser(userRequest))
                .map(userRepository::save)
                .map(UserResponse::of);
    }

    private User toUser(UserRequest userRequest) {
        if (userRequest.getUserType().isParent()) {
            return userRequest.toParent();
        }

        final Parent parent = (Parent) userRepository.findById(userRequest.getParentId())
                .orElseThrow(() -> new IllegalArgumentException("부모 사용자 ID를 잘못 입력하였습니다."));
        return userRequest.toChild(parent);
    }
}
