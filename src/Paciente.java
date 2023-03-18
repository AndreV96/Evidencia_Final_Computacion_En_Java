import java.util.UUID;

public class Paciente {

    private UUID id;
    private String nombre;

    public Paciente(String nombre) {
        this.id = UUID.randomUUID();
        this.nombre = nombre;
    }

    public Paciente(UUID id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}
