package com.nuzurie.blog.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "listings"
)
public class Listing {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    @Column(nullable = false)
    private String title;
    private String description;
    private String address;
//    @OneToOne(orphanRemoval = true, optional = false, cascade = CascadeType.PERSIST)
//    private House house;
    private int price;

    public String toString() {
        return String.format("%s, %s, %s, %s, %d", this.id, this.title, this.description, this.address, this.price);
    }
}
