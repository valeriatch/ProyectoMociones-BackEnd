package cr.ac.una.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaMocion implements Serializable {

    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "persona_id", referencedColumnName = "id")
    private Persona persona;

    @OneToOne
    @JoinColumn(name = "mocion_id", referencedColumnName = "id")
    private Mocion mocion;


}
