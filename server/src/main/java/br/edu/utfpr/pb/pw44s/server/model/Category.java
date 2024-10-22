package br.edu.utfpr.pb.pw44s.server.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "category_tb")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, max = 50)
    @Column(length = 50, nullable = false)
    private String name;

    //Determina a igualdade entre dois objetos Category.
    @Override
    public boolean equals(Object o) {
        //verifica se o objeto atual (this) é o mesmo que o objeto passado (o)
        if (this == o) return true;
        //verifica se o objeto passado é null ou de uma classe diferente.
        if (o == null || getClass() != o.getClass()) return false;
        //compara os campos id de ambos os objetos para determinar a igualdade e retorna
        Category category = (Category) o;
        return Objects.equals(id, category.id);
    }

    //Fornece um código hash para o objeto, que é usado em coleções como HashSet e HashMap
    @Override
    public int hashCode() {
        //Utiliza o metodo estático hash da classe Objects para gerar um código hash
        //baseado no campo id
        return Objects.hash(id);

        //Complementa o metodo equals. Quando equals é sobrescrito,
        //hashCode também deve ser sobrescrito para manter o contrato entre ambos,
        //garantindo que objetos iguais tenham o mesmo código hash.
    }
}
