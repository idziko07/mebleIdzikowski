package pl.idzikowski.meble.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.idzikowski.meble.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {

}
