package pl.idzikowski.meble.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.idzikowski.meble.model.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole,Long> {

}
