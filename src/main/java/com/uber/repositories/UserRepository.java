package com.uber.repositories;

import com.uber.entities.User;
import org.hibernate.annotations.RowId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
