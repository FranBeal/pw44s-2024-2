package br.edu.utfpr.pb.pw44s.server.dto;

import br.edu.utfpr.pb.pw44s.server.model.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    @NotNull(message = "O atributo usuário não pode ser nulo.")
    @Size(min = 4, max = 50, message = "O tamanho do username deve ser entre 4 e 50")
    private String username;

    @NotNull
    @Size(min = 4, max = 50, message = "O tamanho do displayName deve ser entre 4 e 50")
    private String displayName;

    @NotNull(message = "A senha não pode ser vazia")
    @Size(min = 6)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$")
    private String password;

    public UserDTO(User user) {
        this.id = user.getId();
        this.displayName = user.getDisplayName();
        this.username = user.getUsername();
    }

}
