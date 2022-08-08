package com.challenge.user.persistence;

import com.challenge.user.exceptions.UserDoesNotExist;
import com.challenge.user.exceptions.UserExistsException;
import com.challenge.user.model.UserRequestSto;
import com.challenge.user.model.UserCreationResponseAto;
import com.challenge.user.persistence.domain.UpgradeUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository){
        this.repository = repository;
    }

    @Transactional
    @Override
    public UserCreationResponseAto createUser(UserRequestSto userRequest) {
        log.info("CREATE_RECORD: {}", userRequest);

        if (userExists(userRequest.getEmail())) {
            throw new UserExistsException("User [" + userRequest.getEmail() + "] already exists.");
        }

        LocalDate creationDate = LocalDate.now();

        UpgradeUser upgradeUser = UpgradeUser.builder()
                .email(userRequest.getEmail())
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .createDate(creationDate)
                .build();

        UUID id = repository.save(upgradeUser).getId();
        return new UserCreationResponseAto(id, creationDate);
    }

    @Override
    public UpgradeUser findUserById(UUID uuid) {
        Optional<UpgradeUser> user = repository.findAll().stream().filter(u -> u.getId().equals(uuid)).findFirst();
        return user.orElseThrow(() -> new UserDoesNotExist("User [" + uuid + "] does not exist."));
    }


    private boolean userExists(String email) {
        return !repository.findUser(email).isEmpty();
    }


}
