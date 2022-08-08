package com.challenge.user.persistence;

import com.challenge.user.persistence.domain.UpgradeUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UpgradeUser, UUID> {

    @Query("select u from UpgradeUser u where u.email = :email")
    List<UpgradeUser> findUser(@Param("email") String email);
}
