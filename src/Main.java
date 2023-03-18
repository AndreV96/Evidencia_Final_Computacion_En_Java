import java.util.HashMap;
import java.util.UUID;

public class Main {
    private HashMap<UUID, Administrador> administradores;
    private HashMap<UUID, Doctor> doctores;
    private HashMap<UUID, Paciente> pacientes;
    private HashMap<UUID, Cita> citas;

    public Main() {
        administradores = new HashMap<>();
        doctores = new HashMap<>();
        pacientes = new HashMap<>();
        citas = new HashMap<>();
    }

    public Main(HashMap<UUID, Administrador> administradores, HashMap<UUID, Doctor> doctores,
                HashMap<UUID, Paciente> pacientes, HashMap<UUID, Cita> citas) {
        this.administradores = administradores;
        this.doctores = doctores;
        this.pacientes = pacientes;
        this.citas = citas;
    }

    public void addAdministrador(Administrador administrador) {
        administradores.put(administrador.getId(), administrador);
    }

    public void addDoctor(Doctor doctor) {
        doctores.put(doctor.getId(), doctor);
    }

    public void addPaciente(Paciente paciente) {
        pacientes.put(paciente.getId(), paciente);
    }

    public void addCita(Cita cita) {
        citas.put(cita.getId(), cita);
    }



    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}

