import com.google.common.base.Strings;
import org.junit.Test;

import static org.junit.Assert.fail;

public class AuthenticateTest {
    private String id = "id";

    @Test
    public void whenIdOrPwdIsInvalid_thenThrowException() {
        String id1 = null;
        String pwd = null;
        try {
            authenticate(id1, pwd);
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
        if (Strings.isNullOrEmpty(id))
            throw new InvalidIdOrPwd();
        if (Strings.isNullOrEmpty(pwd))
            throw new InvalidIdOrPwd();
    }

    private class InvalidIdOrPwd extends RuntimeException {
    }
}