package com.apptivedev.sns.domain.user.repository;

import com.apptivedev.sns.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
