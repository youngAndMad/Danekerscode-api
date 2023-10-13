package danekerscode.api.repository;

import danekerscode.api.model.Customer;
import danekerscode.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}