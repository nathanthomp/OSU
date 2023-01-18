import components.stack.Stack;
import components.stack.Stack2;

/**
 * Customized JUnit test fixture for {@code Stack3}.
 */
public class Stack2Test extends StackTest {

    /**
     * Invokes the appropriate {@code Stack} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new stack
     * @ensures constructorTest = <>
     */
    @Override
    protected final Stack<String> constructorTest() {
        Stack<String> result = new Stack2<>();
        return result;
    }

    /**
     * Invokes the appropriate {@code Stack} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new stack
     * @ensures constructorRef = <>
     */
    @Override
    protected final Stack<String> constructorRef() {
        Stack<String> result = new Stack2<>();
        return result;
    }

}
