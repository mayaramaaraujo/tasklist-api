package com.tasklist.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tasklist.api.utils.DateFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@EqualsAndHashCode
@Entity
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    private String title;

    private String description;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    private boolean done = false;

    private String createdDate;

    private String updatedDate;

    public Task(String title, String description, Boolean done, User user) {
        this.title = title;
        this.description = description;
        this.user = user;
        this.createdDate = DateFormat.dateFormat(LocalDateTime.now());
        this.done = done;
    }

}
