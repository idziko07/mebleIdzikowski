package pl.idzikowski.meble.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.idzikowski.meble.model.Producer;

public interface ProducerRepository extends JpaRepository<Producer,Long> {

}
