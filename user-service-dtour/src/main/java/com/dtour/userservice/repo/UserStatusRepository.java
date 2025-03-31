package com.dtour.userservice.repo;

import com.dtour.userservice.model.UserStatus;
import com.dtour.userservice.typelist.UserStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;


public interface UserStatusRepository extends JpaRepository<UserStatus, UUID> {

    @Query(nativeQuery = true,value ="select * from user_status where code= :code" )
    public UserStatus getUserStatusByCode(@Param("code") int status);

}
