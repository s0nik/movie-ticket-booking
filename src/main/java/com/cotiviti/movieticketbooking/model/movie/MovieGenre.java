package com.cotiviti.movieticketbooking.model.movie;

import com.cotiviti.movieticketbooking.generic.BaseEntity;
import com.cotiviti.movieticketbooking.model.audit.AuditAbstract;
import com.cotiviti.movieticketbooking.model.user.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "movie_genre")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieGenre extends AuditAbstract implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_genre_gen")
    @SequenceGenerator(name = "movie_genre_gen", sequenceName = "movie_genre_seq", allocationSize = 1, initialValue = 1)
    private Integer id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean status;

    public MovieGenre(Integer id) {
        this.id = id;
    }
}
