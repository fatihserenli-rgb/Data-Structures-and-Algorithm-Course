import java.util.List;
import java.util.ArrayList;
import java.util.Stack;
/**
 * Represents the planetary system tree
 */
public class PlanetarySystem {
    private PlanetaryNode root;

    /**
     * Creates a new planetary system with a star as root
     * @param starName Name of the star
     * @param temperature Temperature in Kelvin
     * @param pressure Pressure in Pascals
     * @param radiation Radiation in Sieverts
     * @throws IllegalArgumentException if humidity is not 0 for star
     */
    public void createPlanetSystem(String starName, double temperature, 
                                 double pressure, double radiation) {
        if (root != null) {
            throw new IllegalStateException("Planetary system already exists");
        }
        SensorData starData = new SensorData(temperature, pressure, 0, radiation);
        root = new PlanetaryNode(starName, "Star", starData);
    }

    /**
     * Adds a planet to the planetary system
     * @param planetName Name of the planet
     * @param parentName Name of the parent (star or planet)
     * @param temperature Temperature in Kelvin
     * @param pressure Pressure in Pascals
     * @param humidity Humidity percentage
     * @param radiation Radiation in Sieverts
     * @throws IllegalStateException if planetary system doesn't exist
     * @throws IllegalArgumentException if parent not found
     */
    public void addPlanet(String planetName, String parentName, double temperature, 
                         double pressure, double humidity, double radiation) {
        if (root == null) {
            throw new IllegalStateException("Planetary system not created yet");
        }
        PlanetaryNode P = root.findNode(planetName);
        if(P != null) {
            throw new IllegalStateException("An object named as \"" + planetName + "\" already exist.");
        }
        PlanetaryNode parent = root.findNode(parentName);
        if (parent == null) {
            throw new IllegalArgumentException("Parent node not found: " + parentName);
        }
        if("Moon".equals(parent.getType())) {
            throw new IllegalArgumentException("Moon type objects can't have childs");
        }
        SensorData planetData = new SensorData(temperature, pressure, humidity, radiation);
        PlanetaryNode planet = new PlanetaryNode(planetName, "Planet", planetData);
        parent.addChild(planet);
    }

    /**
     * Adds a satellite (moon) to a planet
     * @param satelliteName Name of the satellite
     * @param parentName Name of the parent planet
     * @param temperature Temperature in Kelvin
     * @param pressure Pressure in Pascals
     * @param humidity Humidity percentage
     * @param radiation Radiation in Sieverts
     * @throws IllegalStateException if planetary system doesn't exist
     * @throws IllegalArgumentException if parent not found
     */
    public void addSatellite(String satelliteName, String parentName, double temperature, 
                            double pressure, double humidity, double radiation) {
        if (root == null) {
            throw new IllegalStateException("Planetary system not created yet");
        }
        PlanetaryNode P = root.findNode(satelliteName);
        if(P != null) {
            throw new IllegalStateException("An object named as \"" + satelliteName + "\" already exist.");
        }
        PlanetaryNode parent = root.findNode(parentName);
        if (parent == null) {
            throw new IllegalArgumentException("Parent node not found: " + parentName);
        }
        if("Moon".equals(parent.getType())) {
            throw new IllegalArgumentException("Moon type objects can't have childs");
        }
        if("Star".equals(parent.getType())) {
            throw new IllegalArgumentException("Star type objects can't have Moon type childs");
        }
        SensorData satelliteData = new SensorData(temperature, pressure, humidity, radiation);
        PlanetaryNode satellite = new PlanetaryNode(satelliteName, "Moon", satelliteData);
        parent.addChild(satellite);
    }

    /**
     * Finds nodes with radiation above threshold
     * @param threshold Radiation threshold
     * @return List of nodes with high radiation
     * @throws IllegalStateException if planetary system doesn't exist
     */
    public List<PlanetaryNode> findRadiationAnomalies(double threshold) {
        if (root == null) {
            throw new IllegalStateException("Planetary system not created yet");
        }
        List<PlanetaryNode> anomalies = new ArrayList<>();
        root.findRadiationAnomalies(threshold, anomalies);
        return anomalies;
    }

    /**
     * Gets path from root to a node
     * @param nodeName Name of the target node
     * @return Stack representing the path
     * @throws IllegalStateException if planetary system doesn't exist
     * @throws IllegalArgumentException if node not found
     */
    public Stack<String> getPathTo(String nodeName) {
        if (root == null) {
            throw new IllegalStateException("Planetary system not created yet");
        }
        Stack<String> path = new Stack<>();
        if (!root.buildPathTo(nodeName, path)) {
            throw new IllegalArgumentException("Node not found: " + nodeName);
        }
        return path;
    }

    /**
     * Prints mission report for entire system or specific node
     * @param nodeName Name of node to report (null for full system)
     * @throws IllegalStateException if planetary system doesn't exist
     * @throws IllegalArgumentException if specific node not found
     */
    public void printMissionReport(String nodeName) {
        if (root == null) {
            throw new IllegalStateException("Planetary system not created yet");
        }
        if (nodeName == null) {
            root.printMissionReport();
        } else {
            PlanetaryNode node = root.findNode(nodeName);
            if (node == null) {
                throw new IllegalArgumentException("Node not found: " + nodeName);
            }
            System.out.printf("%s (%s): %s%n", node.getName(), node.getType(), node.getSensorData());
        }
    }
}
