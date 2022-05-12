package com.kabir.mydemoproject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    @GenericGenerator(name = "sequence_id", strategy = "com.kabir.mydemoproject.utility.MyGenerator")
    @GeneratedValue(generator = "sequence_id")
    private Long id;

    private Long seatId;

    @JsonIgnore
    @ManyToOne
    private User user;
}
