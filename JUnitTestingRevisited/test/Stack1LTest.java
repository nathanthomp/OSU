import components.stack.Stack;
import components.stack.Stack1L;

/**
 * Customized JUnit test fixture for {@code Stack1L}.
 */
public class Stack1LTest extends StackTest {

    /**
     * Invokes the appropriate {@code Stack} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new stack
     * @ensures constructorTest = <>
     */
    @Override
    protected final Stack<String> constructorTest() {
        Stack<String> result = new Stack1L<>();
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
        Stack<String> result = new Stack1L<>();
        return result;
    }

}
