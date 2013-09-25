import com.google.common.base.Strings;
import org.junit.Test;

import static org.junit.Assert.fail;

public class AuthenticateTest {
    private String id = "id";

    @Test
    public void whenIdOrPwdIsInvalid_thenThrowException() {
        try {
            authenticate(null, null);
            fail("InvalidIdOrPwd expected");
        } catch (InvalidIdOrPwd e) {
        }
        try {
            authenticate("", null);
            fail("InvalidIdOrPwd expected");
        } catch (InvalidIdOrPwd e) {
        }
        try {
            authenticate(id, null);
            fail("InvalidIdOrPwd expected");
        } catch (InvalidIdOrPwd e) {
        }
        try {
            authenticate(id, "");
            fail("InvalidIdOrPwd expected");
        } catch (InvalidIdOrPwd e) {
        }
    }

    private void authenticate(String id, String pwd) {
        if(Strings.isNullOrEmpty(id))
            throw new InvalidIdOrPwd();
    }

    private class InvalidIdOrPwd extends RuntimeException {
    }
}