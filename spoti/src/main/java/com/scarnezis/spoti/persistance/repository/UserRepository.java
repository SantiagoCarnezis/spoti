package com.scarnezis.spoti.persistance.repository;

import com.scarnezis.spoti.persistance.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
