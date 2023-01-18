package software2;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Blank program (clear of Checkstyle and FindBugs warnings).
 *
 * @author N. Thompson
 */
public final class JCF {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private JCF() {
        // no code needed here
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("nathan", 1);
        map.put("mary", 1);
        giveRaise(map, 'n', 4);
        out.println(map.values());
        out.close();
    }

    /**
     * Raises the salary of all the employees in {@code map} whose name starts
     * with the given {@code initial} by the given {@code raisePercent}.
     *
     * @param map
     *            the name to salary map
     * @param initial
     *            the initial of names of employees to be given a raise
     * @param raisePercent
     *            the raise to be given as a percentage of the current salary
     * @updates map
     * @requires [the salaries in map are positive] and raisePercent > 0
     * @ensures <pre>
     * DOMAIN(map) = DOMAIN(#map)  and
     * [the salaries of the employees in map whose names start with the given
     *  initial have been increased by raisePercent percent (and truncated to
     *  the nearest integer); all other employees have the same salary]
     * </pre>
     */
    private static void giveRaise(components.map.Map<String, Integer> map,
            char initial, int raisePercent) {
        for (components.map.Map.Pair<String, Integer> employee : map) {
            String name = employee.key();
            if (name.charAt(0) == initial) {
                int newSalary = employee.value() * raisePercent;
                map.replaceValue(name, newSalary);
            }
        }
    }

    /**
     * Raises the salary of all the employees in {@code map} whose name starts
     * with the given {@code initial} by the given {@code raisePercent}.
     *
     * @param map
     *            the name to salary map
     * @param initial
     *            the initial of names of employees to be given a raise
     * @param raisePercent
     *            the raise to be given as a percentage of the current salary
     * @updates map
     * @requires <pre>
     * [the salaries in map are positive]  and  raisePercent > 0  and
     * [the dynamic types of map and of all objects reachable from map
     *  (including any objects returned by operations (such as entrySet() and,
     *  from there, iterator()), and so on, recursively) support all
     *  optional operations]
     * </pre>
     * @ensures <pre>
     * DOMAIN(map) = DOMAIN(#map)  and
     * [the salaries of the employees in map whose names start with the given
     *  initial have been increased by raisePercent percent (and truncated to
     *  the nearest integer); all other employees have the same salary]
     * </pre>
     */
    private static void giveRaise(java.util.Map<String, Integer> map,
            char initial, int raisePercent) {

        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
        for (Map.Entry<String, Integer> employee : entrySet) {
            String name = employee.getKey();
            if (name.charAt(0) == initial) {
                int newSalary = employee.getValue() * raisePercent;
                map.replace(name, newSalary);
            }
        }
    }

}
