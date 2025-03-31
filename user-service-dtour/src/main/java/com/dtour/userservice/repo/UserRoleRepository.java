package com.dtour.userservice.repo;

import com.dtour.userservice.model.UserRole;
import com.dtour.userservice.typelist.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UUID> {

    @Query(nativeQuery = true,value ="select * from user_role where code= :code" )
    public Optional<UserRole> getUserRoleByCode(@Param("code") int role);
}
