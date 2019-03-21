package pl.idzikowski.meble.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.idzikowski.meble.model.Wardrobes;

@Repository
public interface WadrobesRepository extends JpaRepository<Wardrobes,Long> {

}
