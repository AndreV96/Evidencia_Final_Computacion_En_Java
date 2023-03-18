import java.util.Date;
import java.util.UUID;

public class Cita {
    private UUID id;
    private UUID doctorId;
    private UUID pacienteId;
    private Date fecha;
    private String motivo;

    public Cita(UUID doctorId, UUID pacienteId, Date fecha, String motivo) {
        this.id = UUID.randomUUID();
        this.doctorId = doctorId;
        this.pacienteId = pacienteId;
        this.fecha = fecha;
        this.motivo = motivo;
    }

    public UUID getId() {
        return id;
    }

    public UUID getDoctorId() {
        return doctorId;
    }

    public UUID getPacienteId() {
        return pacienteId;
    }

    public Date getFecha() {
        return  fecha;
    }

    public String getMotivo() {
        return  motivo;
    }

}