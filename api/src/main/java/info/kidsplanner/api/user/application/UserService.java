package info.kidsplanner.api.user.application;

import info.kidsplanner.api.user.application.dto.UserRequest;
import info.kidsplanner.domain.user.User;
import info.kidsplanner.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Long create(UserRequest userRequest) {
        final User savedUser = userRepository.save(userRequest.toEntity());
        return savedUser.getId();
    }
}
