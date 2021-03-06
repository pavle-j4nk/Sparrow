package com.sparrow.backend.repository;

import com.sparrow.backend.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    @Lock(LockModeType.PESSIMISTIC_READ)
    Hotel save(Hotel hotel);

    Optional<Hotel> findByName(String name);

    Optional<Hotel> findByAddress(String address);

    @Query("SELECT h FROM Hotel h WHERE UPPER(h.name) LIKE CONCAT('%', :name, '%')")
    List<Hotel> findAllByName(@Param("name") String name);

}
