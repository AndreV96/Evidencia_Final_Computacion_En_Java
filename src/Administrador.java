import java.util.UUID;

public class Administrador {
    private UUID id;
    private String usuario;
    private String contraseña;

    public Administrador(String usuario, String contraseña) {
        this.id = UUID.randomUUID();
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    public UUID getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

}
