package com.challenge.user.persistence;

import com.challenge.user.model.UserRequestSto;
import com.challenge.user.model.UserCreationResponseAto;
import com.challenge.user.persistence.domain.UpgradeUser;

import java.util.UUID;

public interface UserService {

    UserCreationResponseAto createUser(UserRequestSto user);

    UpgradeUser findUserById(UUID uuid);

}
