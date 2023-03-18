import java.util.UUID;

public class Paciente {

    private UUID id;
    private String nombre;

    public Paciente(String nombre, String especialidad) {
        this.id = UUID.randomUUID();
        this.nombre = nombre;
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}
