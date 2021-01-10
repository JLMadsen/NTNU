package team07.vikingwars.Model.MethodWrapping;

/**
 * MethodWrapper can be used to pass a set of instructions to a separate thread.
 */
public interface MethodWrapper {
    /**
     * Runs the instructions provided
     */
    public void invoke();
}