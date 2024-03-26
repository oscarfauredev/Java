import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    // MainClass who call displayMenu function and all function from TaskManager
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager(new ArrayList<>());

        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    taskManager.addTaskFromInput();
                    break;
                case 2:
                    taskManager.removeTaskFromInput();
                    break;
                case 3:
                    taskManager.displayFinishedTasks();
                    break;
                case 4:
                    taskManager.saveToFileFromInput();
                    break;
                case 5:
                    taskManager.loadFromFileFromInput();
                    break;
                case 6:
                    taskManager.displayTasksBetweenDatesFromInput();
                    break;
                case 7:
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        }
    }

    // Menu message interface for the user
    private static void displayMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Ajouter une tache");
        System.out.println("2. Supprimer une tache");
        System.out.println("3. Afficher la liste des tâches finis");
        System.out.println("4. Enregistrer la liste des tâches dans un fichier");
        System.out.println("5. Charger la liste des tâches depuis un fichier");
        System.out.println("6. Afficher la liste des taches dont la date est comprise entre 2 dates saisies par l`utilisateur");
        System.out.println("7. Quitter");
        System.out.print("Choix: ");
    }
}
