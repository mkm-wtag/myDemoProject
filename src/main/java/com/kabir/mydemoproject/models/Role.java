package com.kabir.mydemoproject.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
public class Role {

    @Id
    @GenericGenerator(name = "sequence_id", strategy = "com.kabir.mydemoproject.utility.MyGenerator")
    @GeneratedValue(generator = "sequence_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private ERole name;

}