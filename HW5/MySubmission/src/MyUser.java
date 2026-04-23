/**
 * Represents a user with an ID and a priority value.
 */
public class MyUser {
    /**
     * The unique identifier for the user.
     * @uml.property name="id"
     */
    private Integer id;

    /**
     * The priority level associated with the user.
     * @uml.property name="priority"
     */
    private Integer priority;

    /**
     * Constructs a MyUser instance with the specified ID and priority.
     *
     * @param id the ID of the user
     * @param priority the priority of the user
     */
    public MyUser(Integer id, Integer priority) {
        this.id = id;
        this.priority = priority;
    }

    /**
     * Returns the ID of the user.
     *
     * @return the user's ID
     */
    public Integer getID() {
        return this.id;
    }

    /**
     * Returns the priority of the user.
     *
     * @return the user's priority
     */
    public Integer getPriority() {
        return this.priority;
    }
}

