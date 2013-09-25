import org.junit.Test;

import static org.junit.Assert.fail;

public class AuthenticateTest {
    @Test
    public void whenIdOrPwdIsInvalid_thenThrowException() {
        try {
            authenticate(null, null);
            fail("InvalidIdOrPwd expected");
        } catch (InvalidIdOrPwd e) {
        }
    }

    private void authenticate(String id, String pwd) {
        throw new IllegalStateException("AuthenticateTest#authenticate not implemented yet");
    }

    private class InvalidIdOrPwd extends RuntimeException {
    }
}