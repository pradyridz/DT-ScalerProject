package com.dtour.userservice.repo;

import com.dtour.userservice.model.User;
import com.dtour.userservice.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    public Optional<User> findUserByEmailAddress(String emailAddress);
    public List<User> findUsersByName(String name);

    public List<Object> findUserByRoles(UserRole role);
}
