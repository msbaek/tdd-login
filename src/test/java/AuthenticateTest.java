import org.junit.Test;

public class AuthenticateTest {
    @Test
    public void whenIdOrPwdIsInvalid_thenThrowException() {
        try {
            authenticate(null, null);
            fail("InvalidIdOrPwd expected");
        } catch (InvalidIdOrPwd e) {
        }
    }
}