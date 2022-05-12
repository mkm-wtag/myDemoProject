package com.kabir.mydemoproject.repository;

import com.kabir.mydemoproject.models.Seat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends CrudRepository<Seat, Long> {
}
