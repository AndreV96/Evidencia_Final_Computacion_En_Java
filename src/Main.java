import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
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
                    Date fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(values[3]);
                    String motivo = values[4];
                    Cita cita = new Cita(id, doctorId, pacienteId, fecha, motivo);
                    citas.put(id, cita);
                }
            }
            reader.close();
        } catch (IOException | ParseException e) {
            System.out.println("Ha ocurrido un error al cargar la lista de citas del archivo seleccionado:" + e.getMessage());
        }
    }

    public void saveCitas(String file) {
        try {
            FileWriter writer = new FileWriter(file);
            for (UUID id : citas.keySet()) {
                Cita cita = citas.get(id);
                String fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(cita.getFecha());
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

    public void saveAdministradores(String file) {
        try {
            FileWriter writer = new FileWriter(file);
            for (UUID id : administradores.keySet()) {
                Administrador administrador = administradores.get(id);
                writer.write(id.toString() + "," + administrador.getUsuario() + "," + administrador.getContraseña() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error al guardar la lista de administradores en el archivo seleccionado: " + e.getMessage());
        }
    }




        public void run() {
        Scanner scanner = new Scanner(System.in);
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
                    System.out.println("New doctor added.");
                    System.out.println("Presione enter para continuar");
                    scanner.nextLine();
                    break;
                case "2":
                    System.out.println("New patient added.");
                    System.out.println("Presione enter para continuar");
                    scanner.nextLine();
                    break;
                case "3":
                    System.out.println("Appointment created.");
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

