import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;


/**
 * Tasks Methods
 * @author Oscar Faure
 */
public class TaskManager {
    private List<Task> tasks;

    // Constructor
    public TaskManager(List<Task> tasks) {
        this.tasks = tasks;
    }

    // AddTask function 
    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Tâche ajoutée avec succès.");
    }

    // RemoveTask function
    public void removeTask(int taskNumber) {
        boolean removed = false;
        for (Task task : tasks) {
            if (task.getNumber() == taskNumber) {
                tasks.remove(task);
                removed = true;
                break;
            }
        }
        if (removed) {
            System.out.println("Tâche supprimée avec succès.");
        } else {
            System.out.println("Numéro de tâche invalide.");
        }
    }

    // Display the list of all finished tasks
    public void displayFinishedTasks() {
        int totalTasks = 0;
        int unfinishedTasks = 0;
    
        System.out.println("Liste des tâches terminées : ");
        for (Task task : tasks) {
            totalTasks++;
            if (task.getStatus() == 1) {
                System.out.println("Numéro : " + task.getNumber());
                System.out.println("Description : " + task.getDescription());
                System.out.println("Date limite : " + task.getDeadline());
                System.out.println();
            } else {
                unfinishedTasks++;
            }
        }
    
        System.out.println("Nombre de tâches non terminées sur le total des tâches : " + unfinishedTasks + "/" + totalTasks);
    }

    // Save tasks to .txt file
    public void saveToFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            for (Task task : tasks) {
                String formattedDate = dateFormat.format(task.getDeadline());
                writer.println(task.getNumber() + "," + task.getDescription() + "," + task.getStatus() + "," + formattedDate);
            }
            System.out.println("Liste des tâches enregistrée dans " + fileName + ".");
        } catch (IOException e) {
            System.out.println("Erreur lors de l'enregistrement dans le fichier.");
        }
    }

    // Load tasks from a file
    private List<Task> loadFromFile(String fileName) throws IOException {
        List<Task> loadedTasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int number = Integer.parseInt(parts[0]);
                String description = parts[1];
                int status = Integer.parseInt(parts[2]);
                String deadlineStr = parts[3];
                Date deadline = dateFormat.parse(deadlineStr);
                Task task = new Task(number, description, status, deadline);
                loadedTasks.add(task);
            }
        } catch (ParseException e) {
            System.out.println("Format de date invalide dans le fichier.");
            return null;
        }
        return loadedTasks;
    }
    
    // Display list of tasks between dates
    public void displayTasksBetweenDates(Date startDate, Date endDate) {
        System.out.println("Liste des tâches entre " + startDate + " et " + endDate + " : ");
        for (Task task : tasks) {
            if (task.getDeadline().after(startDate) && task.getDeadline().before(endDate)) {
                System.out.println("Numéro : " + task.getNumber());
                System.out.println("Description : " + task.getDescription());
                System.out.println("Date limite : " + task.getDeadline());
                System.out.println();
            }
        }
    }

    // Give parametters values for addTask function
    public void addTaskFromInput() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Description de la tâche : ");
            String description = scanner.nextLine();
            System.out.println("Numéro de la tâche : ");
            int number = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Statut de la tâche (0 pour non terminée, 1 pour terminée) : ");
            int status = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Date limite de la tâche (format dd/MM/yyyy) : ");
            String deadlineStr = scanner.nextLine();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date deadline = dateFormat.parse(deadlineStr);
            addTask(new Task(number, description, status, deadline));
        } catch (ParseException e) {
            System.out.println("Format de date invalide.");
        }
    }

    // Give parametters values for removeTask function
    public void removeTaskFromInput() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Liste des tâches : ");
            for (Task task : tasks) {
                System.out.println("Numéro : " + task.getNumber() + ", Description : " + task.getDescription());
            }
            
            System.out.println("Numéro de la tâche à supprimer ou tapez 'retour' pour revenir au menu : ");
            String input = scanner.nextLine();
            if ("retour".equalsIgnoreCase(input)) {
                return;
            }

            int taskNumber = Integer.parseInt(input);
            removeTask(taskNumber);
        } catch (NumberFormatException e) {
            System.out.println("Numéro de tâche invalide.");
        }
    }

    // Give parametters values for saveToFile function
    public void saveToFileFromInput() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Nom du fichier pour enregistrer les tâches : ");
            String currentDirectory = System.getProperty("user.dir");
            System.out.println("Fichiers disponibles dans " + currentDirectory + " :");

            File[] files = new File(currentDirectory).listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        System.out.println(file.getName());
                    }
                }
            } else {
                System.out.println("Aucun fichier disponible dans le répertoire.");
            }

            String fileName = scanner.nextLine();
            saveToFile(fileName);
        } catch (Exception e) {
            System.out.println("Erreur lors de l'enregistrement dans le fichier.");
        }
    }

    // Give parametters values for loadFromFile function
    public void loadFromFileFromInput() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Fichiers disponibles à charger : ");
            String currentDirectory = System.getProperty("user.dir");
            File[] files = new File(currentDirectory).listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        System.out.println(file.getName());
                    }
                }
            } else {
                System.out.println("Aucun fichier disponible dans le répertoire.");
            }

            System.out.println("Nom du fichier à charger : ");
            String fileName = scanner.nextLine();
            List<Task> loadedTasks = loadFromFile(fileName);
            if (loadedTasks != null) {
                tasks.addAll(loadedTasks);
                System.out.println("Tâches chargées avec succès depuis le fichier " + fileName + ".");
            } else {
                System.out.println("Aucune tâche chargée.");
            }
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement du fichier.");
        }
    }

    // Give parametters values for displayTasksBetweenDates function
    public void displayTasksBetweenDatesFromInput() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Entrez la date de début (format dd/MM/yyyy) : ");
            String startDateStr = scanner.nextLine();
            System.out.println("Entrez la date de fin (format dd/MM/yyyy) : ");
            String endDateStr = scanner.nextLine();

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date startDate = dateFormat.parse(startDateStr);
            Date endDate = dateFormat.parse(endDateStr);

            displayTasksBetweenDates(startDate, endDate);
        } catch (ParseException e) {
            System.out.println("Format de date invalide.");
        }
    }
}
