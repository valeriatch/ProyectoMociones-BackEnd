package cr.ac.una.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mocion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String texto;

    @OneToOne
    @JoinColumn(name = "tipo_mocion_id", referencedColumnName = "id")
    private TipoMocion tipoMocion;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date fecha;
}
