package pl.idzikowski.meble.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.idzikowski.meble.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

}
