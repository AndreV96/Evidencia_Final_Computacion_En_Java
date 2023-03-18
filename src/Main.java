import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    private HashMap<UUID, Administrador> administradores;
    private HashMap<UUID, Doctor> doctores;
    private HashMap<UUID, Paciente> pacientes;
    private HashMap<UUID, Cita> citas;

    Scanner scanner = new Scanner(System.in);

    public Main() {
        administradores = new HashMap<>();
        doctores = new HashMap<>();
        pacientes = new HashMap<>();
        citas = new HashMap<>();
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

    public UUID getDoctorIdFromName() {
        while (true) {
            System.out.println("Ingrese el nombre del doctor:");
            String nombre = scanner.nextLine();

            for (Doctor doctor : doctores.values()) {
                if (doctor.getNombre().equals(nombre)) {
                    return doctor.getId();
                }
            }

            System.out.println("No se encontró ningún doctor con ese nombre, por favor intente de nuevo.");
        }
    }

    public UUID getPacienteIdFromName() {
        while (true) {
            System.out.println("Ingrese el nombre del paciente:");
            String nombre = scanner.nextLine();

            for (Paciente paciente : pacientes.values()) {
                if (paciente.getNombre().equals(nombre)) {
                    return paciente.getId();
                }
            }

            System.out.println("No se encontró ningún doctor con ese nombre, por favor intente de nuevo.");
        }
    }

    public void loadDoctores(String file) {
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 3) {
                    UUID id = UUID.fromString(values[0]);
                    String nombre = values[1];
                    String especialidad = values[2];
                    Doctor doctor = new Doctor(id, nombre, especialidad);
                    doctores.put(id, doctor);
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error al cargar la lista de doctores del archivo seleccionado: " + e.getMessage());
        }
    }

    public void saveDoctores(String file) {
        try {
            FileWriter writer = new FileWriter(file);
            for (UUID id : doctores.keySet()) {
                Doctor doctor = doctores.get(id);
                writer.write(id.toString() + "," + doctor.getNombre() + "," + doctor.getEspecialidad() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error al guardar la lista de doctores en el archivo seleccionado: " + e.getMessage());
        }
    }

    public void loadPacientes(String file) {
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 2) {
                    UUID id = UUID.fromString(values[0]);
                    String nombre = values[1];
                    Paciente paciente = new Paciente(id, nombre);
                    pacientes.put(id, paciente);
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error al cargar la lista de pacientes del archivo seleccionado:" + e.getMessage());
        }
    }

    public void savePacientes(String file) {
        try {
            FileWriter writer = new FileWriter(file);
            for (UUID id : pacientes.keySet()) {
                Paciente paciente = pacientes.get(id);
                writer.write(id.toString() + "," + paciente.getNombre() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error al guardar la lista de pacientes en el archivo seleccionado: " + e.getMessage());
        }
    }

    public void loadCitas(String file) {
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 5) {
                    UUID id = UUID.fromString(values[0]);
                    UUID doctorId = UUID.fromString(values[1]);
                    UUID pacienteId = UUID.fromString(values[2]);
                    String fecha = values[3];
                    String motivo = values[4];
                    Cita cita = new Cita(id, doctorId, pacienteId, fecha, motivo);
                    citas.put(id, cita);
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error al cargar la lista de citas del archivo seleccionado:" + e.getMessage());
        }
    }

    public void saveCitas(String file) {
        try {
            FileWriter writer = new FileWriter(file);
            for (UUID id : citas.keySet()) {
                Cita cita = citas.get(id);
                String fecha = cita.getFecha();
                writer.write(id.toString() + "," + cita.getDoctorId().toString() + "," + cita.getPacienteId().toString() + "," + fecha + "," + cita.getMotivo() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error al guardar la lista de citas en el archivo seleccionado: " + e.getMessage());
        }
    }

    public void loadAdministradores(String file) {
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 3) {
                    UUID id = UUID.fromString(values[0]);
                    String usuario = values[1];
                    String contraseña = values[2];
                    Administrador administrador = new Administrador(usuario, contraseña);
                    administradores.put(id, administrador);
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error al cargar la lista de administradores del archivo seleccionado:" + e.getMessage());
        }
    }

    public void userCreateDoctor() {
        System.out.print("Escribe el nombre del doctor: ");
        String nombre = scanner.nextLine();
        System.out.print("Escribe la especialidad del doctor: ");
        String especialidad = scanner.nextLine();
        Doctor doctor = new Doctor(nombre, especialidad);
        addDoctor(doctor);
        System.out.println("Nuevo doctor agregado.");
    }

    public void userCreatePaciente() {
        System.out.print("Escribe el nombre del paciente: ");
        String nombre = scanner.nextLine();
        Paciente paciente = new Paciente(nombre);
        addPaciente(paciente);
        System.out.println("Nuevo paciente agregado.");
    }

    public void userCreateCita() {
        UUID doctorId = getDoctorIdFromName();
        UUID pacienteId = getPacienteIdFromName();

        System.out.print("Escribe la fecha de la cita: ");
        String fecha = scanner.nextLine();
        System.out.print("Escribe el motivo de la cita: ");
        String motivo = scanner.nextLine();
        Cita cita = new Cita(doctorId, pacienteId,fecha, motivo);
        addCita(cita);
        System.out.println("Nueva cita creada.");
    }

    public void run() {
    boolean access = false;
    boolean running = true;

    String doctoresFile = "/Users/andrevega/IdeaProjects/Evidencia_Computacion_En_Java/DB/doctores.csv";
    String pacientesFile = "/Users/andrevega/IdeaProjects/Evidencia_Computacion_En_Java/DB/pacientes.csv";
    String citasFile = "/Users/andrevega/IdeaProjects/Evidencia_Computacion_En_Java/DB/citas.csv";
    String administradoresFile = "/Users/andrevega/IdeaProjects/Evidencia_Computacion_En_Java/DB/administradores.csv";

    loadDoctores(doctoresFile);
    loadPacientes(pacientesFile);
    loadCitas(citasFile);
    loadAdministradores(administradoresFile);

    while (access != true) {
        System.out.println("Ingrese su nombre de usuario:");
        String username = scanner.nextLine();
        System.out.println("Ingrese su contraseña:");
        String password = scanner.nextLine();

        for (Administrador admin : administradores.values()) {
            if (admin.getUsuario().equals(username) && admin.getContraseña().equals(password)) {
                System.out.println("¡Inicio de sesión exitoso!");
                access = true;
            } else {
                System.out.println("Usuario o contraseña incorrecta, intente de nuevo");
            }
        }
    }

    System.out.println("Bienvenido al menú del hospital");
    while (running) {
        System.out.println("Por favor escribe el número de opción a ejecutar: ");
        System.out.println("1. Agregar un nuevo doctor");
        System.out.println("2. Agregar un nuevo paciente");
        System.out.println("3. Crear una nueva cita");
        System.out.println("4. Salir del programa");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                userCreateDoctor();
                System.out.println("Presione enter para continuar");
                scanner.nextLine();
                break;
            case "2":
                userCreatePaciente();
                System.out.println("Presione enter para continuar");
                scanner.nextLine();
                break;
            case "3":
                userCreateCita();
                System.out.println("Presione enter para continuar");
                scanner.nextLine();
                break;
            case "4":
                running = false;
                saveDoctores(doctoresFile);
                savePacientes(pacientesFile);
                saveCitas(citasFile);
                System.out.println("Cambios guardados");
                break;
            default:
                System.out.println("Opción inválida, presione enter para intentar de nuevo");
                scanner.nextLine();
                break;
            }
        }
    }

    public static void main(String[] args) {
        Main Program = new Main();
        Program.run();
    }
}

