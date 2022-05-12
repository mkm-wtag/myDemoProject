package com.kabir.mydemoproject.repository;

import com.kabir.mydemoproject.models.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {

}
