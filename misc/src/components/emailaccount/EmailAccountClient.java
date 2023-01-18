package components.emailaccount;

import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class EmailAccountClient {

    private EmailAccountClient() {

    }

    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        EmailAccount ea1 = new EmailAccount1("Nathan", "Thompson");
        EmailAccount ea2 = new EmailAccount1("David", "Thompson");
        out.println(EmailAccount1.numberOfAccounts());
        out.println(ea1.emailAddress());
        out.println(ea2.emailAddress());
        out.close();
    }
}
