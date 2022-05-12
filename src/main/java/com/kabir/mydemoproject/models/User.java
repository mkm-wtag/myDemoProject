package com.kabir.mydemoproject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GenericGenerator(name = "sequence_id", strategy = "com.kabir.mydemoproject.utility.MyGenerator")
    @GeneratedValue(generator = "sequence_id")
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Ticket> tickets = new ArrayList<>();

}
