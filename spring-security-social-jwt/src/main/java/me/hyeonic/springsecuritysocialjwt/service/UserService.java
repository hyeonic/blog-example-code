package me.hyeonic.springsecuritysocialjwt.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.hyeonic.springsecuritysocialjwt.domain.user.User;
import me.hyeonic.springsecuritysocialjwt.domain.user.UserRepository;
import me.hyeonic.springsecuritysocialjwt.dto.user.UserMainResponseDto;
import me.hyeonic.springsecuritysocialjwt.dto.user.UserSaveOrUpdateRequestDto;
import me.hyeonic.springsecuritysocialjwt.util.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserMainResponseDto saveOrUpdate(UserSaveOrUpdateRequestDto requestDto) {
        User user = userRepository.findByEmail(requestDto.getEmail()).orElse(null);

        if (user == null) {
            return new UserMainResponseDto(userRepository.save(requestDto.toEntity()));
        }

        user.update(requestDto.toEntity());

        return new UserMainResponseDto(user);
    }

    public UserMainResponseDto findById(Long id) {
        return userRepository.findById(id)
                .map(user -> new UserMainResponseDto(user))
                .orElseThrow(() -> new RuntimeException("존재하지 않는 user 입니다. id=" + id));
    }

    public UserMainResponseDto findMyInfo() {
        String email = SecurityUtil.getCurrentEmail().orElseThrow(() ->
                new RuntimeException("Security Context에 인증 정보가 없습니다."));

        return userRepository.findByEmail(email)
                .map(user -> new UserMainResponseDto(user))
                .orElseThrow(() -> new RuntimeException("존재하지 않는 user 입니다. email=" + email));
    }
}