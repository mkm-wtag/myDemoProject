package com.kabir.mydemoproject.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Seat {

    @Id
    @GenericGenerator(name = "sequence_id", strategy = "com.kabir.mydemoproject.utility.MyGenerator")
    @GeneratedValue(generator = "sequence_id")
    private Long id;
    
    private boolean booked;
}
