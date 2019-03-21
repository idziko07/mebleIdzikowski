package pl.idzikowski.meble.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.idzikowski.meble.model.Kitchen;

@Repository
public interface KitchenRepository extends JpaRepository<Kitchen,Long>{

}