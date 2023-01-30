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
import java.util.List;

@Entity
@Table(name = "movie")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movie extends AuditAbstract implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_gen")
    @SequenceGenerator(name = "movie_gen", sequenceName = "movie_seq", allocationSize = 1, initialValue = 1)
    private Integer id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String director;

    private String runTime;

    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "movies_genres",
            foreignKey = @ForeignKey(name = "FK_movies_genre_movie_id"),
            joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"),
            inverseForeignKey = @ForeignKey(name = "FK_movies_genres_genre_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id"),
            uniqueConstraints = @UniqueConstraint(name = "UNIQUE_movie_genre", columnNames = {"movie_id", "genre_id"})
    )
    private List<MovieGenre> genre;


}
