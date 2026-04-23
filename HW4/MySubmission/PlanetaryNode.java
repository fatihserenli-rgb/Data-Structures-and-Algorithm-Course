import java.util.List;
import java.util.ArrayList;
import java.util.Stack;
/**
 * Represents a node in the planetary system tree
 */
public class PlanetaryNode {
    private String name;
    private String type;
    private SensorData sensorData;
    private List<PlanetaryNode> children;

    /**
     * Constructor for PlanetaryNode
     * @param name Name of the celestial body
     * @param type Type ("Star", "Planet", "Moon")
     * @param sensorData Sensor data for the body
     * @throws IllegalArgumentException if type is invalid
     */
    public PlanetaryNode(String name, String type, SensorData sensorData) {
        if (!type.equals("Star") && !type.equals("Planet") && !type.equals("Moon")) {
            throw new IllegalArgumentException("Invalid type. Must be Star, Planet, or Moon");
        }
        this.name = name;
        this.type = type;
        this.sensorData = sensorData;
        this.children = new ArrayList<>();
    }

    // Getters
    public String getName() { return name; }
    public String getType() { return type; }
    public SensorData getSensorData() { return sensorData; }
    public List<PlanetaryNode> getChildren() { return children; }

    /**
     * Adds a child node to this node
     * @param child The child node to add
     */
    public void addChild(PlanetaryNode child) {
        children.add(child);
    }

    /**
     * Finds a node by name recursively in the subtree
     * @param name Name of the node to find
     * @return The found node or null if not found
     */
    public PlanetaryNode findNode(String name) {
        if (this.name.equals(name)) {
            return this;
        }
        for (PlanetaryNode child : children) {
            PlanetaryNode found = child.findNode(name);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    /**
     * Recursively finds nodes with radiation above threshold
     * @param threshold Radiation threshold
     * @param anomalies List to store anomalies
     */
    public void findRadiationAnomalies(double threshold, List<PlanetaryNode> anomalies) {
        if (sensorData.getRadiation() > threshold) {
            anomalies.add(this);
        }
        for (PlanetaryNode child : children) {
            child.findRadiationAnomalies(threshold, anomalies);
        }
    }

    /**
     * Recursively builds a path stack to the target node
     * @param targetName Name of the target node
     * @param path Current path stack
     * @return true if path was found, false otherwise
     */
    public boolean buildPathTo(String targetName, Stack<String> path) {
        path.push(name);
        if (name.equals(targetName)) {
            return true;
        }
        for (PlanetaryNode child : children) {
            if (child.buildPathTo(targetName, path)) {
                return true;
            }
        }
        path.pop();
        return false;
    }

    /**
     * Prints mission report for this node and all children recursively
     */
    public void printMissionReport() {
        System.out.printf("%s (%s): %s%n", name, type, sensorData);
        for (PlanetaryNode child : children) {
            child.printMissionReport();
        }
    }
}
