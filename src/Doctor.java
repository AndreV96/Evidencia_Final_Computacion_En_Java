import java.util.UUID;
public class Doctor {
    private UUID id;
    private String nombre;
    private String especialidad;

    public Doctor(String nombre, String especialidad) {
        this.id = UUID.randomUUID();
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }
}
