package com.asset.repo;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.asset.model.UserInfo;

@Repository
public interface UserRepository extends JpaRepository<UserInfo,Integer> {
	@Query("select u from UserInfo u where u.username=?1")
    Optional<UserInfo> findByUsername(String username);
}