package info.kidsplanner.user.application;

import info.kidsplanner.user.Parent;
import info.kidsplanner.user.User;
import info.kidsplanner.user.UserRepository;
import info.kidsplanner.user.application.dto.UserRequest;
import info.kidsplanner.user.application.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponse create(UserRequest userRequest) {
        final User user = toUser(userRequest);
        final User savedUser = userRepository.save(user);
        return UserResponse.of(savedUser);
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
