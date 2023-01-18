package components.emailaccount;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of {@code EmailAccount}.
 *
 * @author Nathan Thompson
 *
 */
public final class EmailAccount1 implements EmailAccount {

    /*
     * Private members --------------------------------------------------------
     */

    private static int emailCount;
    private static Map<String, Integer> nameData;

    static {
        emailCount = 0;
        nameData = new HashMap<String, Integer>();
    }

    private String firstName;
    private String lastName;
    private String emailAddress;

    private static String createAddress(String lastName) {
        String result = lastName.toLowerCase() + ".";
        if (nameData.containsKey(lastName.toLowerCase())) {
            Integer n = nameData.remove(lastName.toLowerCase());
            n++;
            nameData.put(lastName, n);
            result += n.toString();
        } else {
            nameData.put(lastName.toLowerCase(), 1);
            result += "1";
        }
        result += "@osu.edu";
        return result;
    }

    /*
     * Constructor ------------------------------------------------------------
     */

    /**
     * Constructor.
     *
     * @param firstName
     *            the first name
     * @param lastName
     *            the last name
     */
    public EmailAccount1(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = createAddress(lastName);
        EmailAccount1.emailCount++;
    }

    /*
     * Methods ----------------------------------------------------------------
     */

    @Override
    public String name() {
        return this.firstName + " " + this.lastName;
    }

    @Override
    public String emailAddress() {
        return this.emailAddress;
    }

    @Override
    public String toString() {
        return "Name: " + this.firstName + " " + this.lastName + ", Email: "
                + this.emailAddress;
    }

    public static int numberOfAccounts() {
        return EmailAccount1.emailCount;
    }

}
