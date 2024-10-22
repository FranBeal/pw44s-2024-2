package br.edu.utfpr.pb.pw44s.server.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "product_tb")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @NotNull
    @Getter @Setter
    private String name;

    @NotNull
    @Column(length = 1024)
    @Getter @Setter
    private String description;

    @NotNull
    @Getter @Setter
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @Getter @Setter
    private Category category;
}
