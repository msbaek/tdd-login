import com.google.common.base.Strings;
import org.junit.Test;

import static org.junit.Assert.fail;

public class AuthenticateTest {
    private String id = "id";
    private String pwd = "pwd";

    @Test
    public void whenIdOrPwdIsInvalid_thenThrowException() {
        authenticate_for_invalid_id_or_pwd(null, null);
        authenticate_for_invalid_id_or_pwd("", null);
        authenticate_for_invalid_id_or_pwd(id, null);
        authenticate_for_invalid_id_or_pwd(id, "");
        authenticate_for_invalid_id_or_pwd(id, pwd);
    }

    private void authenticate_for_invalid_id_or_pwd(String id1, String pwd) {
        try {
            authenticate(id1, pwd);
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