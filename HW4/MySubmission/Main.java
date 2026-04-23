import java.util.List;
import java.util.Stack;
/**
 * The {@code Main} class provides a simple command-line interface
 * to interact with a planetary system. Users can create a planetary system,
 * add planets and satellites, detect radiation anomalies, trace paths,
 * and print mission reports.
 */
public class Main {
    private static PlanetarySystem system = new PlanetarySystem();

    /**
     * The main entry point of the application.
     *
     * @param args the command-line arguments (not used)
     */
    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.println("Available commands:");
        System.out.println("create planetSystem <starName> <temp> <pressure> <humidity> <radiation>");
        System.out.println("addPlanet <planetName> <parentName> <temp> <pressure> <humidity> <radiation>");
        System.out.println("addSatellite <satelliteName> <parentName> <temp> <pressure> <humidity> <radiation>");
        System.out.println("findRadiationAnomalies <threshold>");
        System.out.println("getPathTo <nodeName>");
        System.out.println("printMissionReport [nodeName]");
        System.out.println("exit");

        while (true) {
            System.out.print("enter command: ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }
            try {
                processCommand(input);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
    }

    /**
     * Processes a user command entered from the command line.
     *
     * @param input the user input command string
     * @throws IllegalArgumentException if the command is invalid or has wrong format
     * @throws NumberFormatException if any numeric parsing fails
     */
    private static void processCommand(String input) {
        String[] parts = input.split(" ");
        String command = parts[0].toLowerCase();

        try {
            switch (command) {
                case "create":
                    if (parts.length < 7 || !parts[1].equalsIgnoreCase("planetSystem")) {
                        throw new IllegalArgumentException("Invalid create command format");
                    }
                    String starName = parts[2];
                    double temp = Double.parseDouble(parts[3]);
                    double pressure = Double.parseDouble(parts[4]);
                    double humidity = Double.parseDouble(parts[5]);
                    if(humidity != 0) {
                        throw new IllegalArgumentException("Humidity must be 0 for stars");
                    }
                    double radiation = Double.parseDouble(parts[6]);
                    system.createPlanetSystem(starName, temp, pressure, radiation);
                    System.out.println("Created planetary system with star: " + starName);
                    break;

                case "addplanet":
                    if (parts.length != 7) {
                        throw new IllegalArgumentException("Invalid addPlanet command format");
                    }
                    String planetName = parts[1];
                    String planetParent = parts[2];
                    double pTemp = Double.parseDouble(parts[3]);
                    double pPressure = Double.parseDouble(parts[4]);
                    double pHumidity = Double.parseDouble(parts[5]);
                    double pRadiation = Double.parseDouble(parts[6]);
                    system.addPlanet(planetName, planetParent, pTemp, pPressure, pHumidity, pRadiation);
                    System.out.println("Added planet " + planetName + " to " + planetParent);
                    break;

                case "addsatellite":
                    if (parts.length != 7) {
                        throw new IllegalArgumentException("Invalid addSatellite command format");
                    }
                    String satelliteName = parts[1];
                    String satelliteParent = parts[2];
                    double sTemp = Double.parseDouble(parts[3]);
                    double sPressure = Double.parseDouble(parts[4]);
                    double sHumidity = Double.parseDouble(parts[5]);
                    double sRadiation = Double.parseDouble(parts[6]);
                    system.addSatellite(satelliteName, satelliteParent, sTemp, sPressure, sHumidity, sRadiation);
                    System.out.println("Added satellite " + satelliteName + " to " + satelliteParent);
                    break;

                case "findradiationanomalies":
                    if (parts.length != 2) {
                        throw new IllegalArgumentException("Invalid findRadiationAnomalies command");
                    }
                    double threshold = Double.parseDouble(parts[1]);
                    List<PlanetaryNode> anomalies = system.findRadiationAnomalies(threshold);
                    System.out.println("Radiation anomalies (>" + threshold + " Sieverts):");
                    for (PlanetaryNode node : anomalies) {
                        System.out.printf("%s (%s): %.2f Sieverts%n", 
                                         node.getName(), node.getType(), node.getSensorData().getRadiation());
                    }
                    break;

                case "getpathto":
                    if (parts.length != 2) {
                        throw new IllegalArgumentException("Invalid getPathTo command");
                    }
                    String targetNode = parts[1];
                    Stack<String> path = system.getPathTo(targetNode);
                    System.out.println("Path to " + targetNode + ":");
                    while (!path.isEmpty()) {
                        System.out.print(path.pop());
                        if (!path.isEmpty()) System.out.print(" -> ");
                    }
                    System.out.println();
                    break;

                case "printmissionreport":
                    if(parts.length > 2) {
                        throw new IllegalArgumentException("Invalid printMissionReport command");
                    }
                    if (parts.length == 2 ) {
                        system.printMissionReport(parts[1]);
                    } else {
                        system.printMissionReport(null);
                    }
                    break;

                default:
                    System.out.println("Unknown command: " + command);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format");
        }
    }
}
