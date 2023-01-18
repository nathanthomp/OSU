import components.set.Set;
import components.set.Set1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Blank program (clear of Checkstyle and FindBugs warnings).
 *
 * @author N. Thompson
 */
public final class Final {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private Final() {
        // no code needed here
    }

    private static void recursiveRemove(Set<Integer> s, Set<Integer> t) {

        if (s.size() > 0) {
            int x = s.removeAny();
            t.add(x);
            recursiveRemove(s, t);

        }
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        Set<Integer> s = new Set1L<>();
        Set<Integer> t = new Set1L<>();
        for (int i = 0; i < 5; i++) {
            s.add(i);
        }
        recursiveRemove(s, t);
        out.println("s: " + s);
        out.println("t: " + t);

        out.close();
    }

}
