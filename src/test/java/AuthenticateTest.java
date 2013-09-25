import com.google.common.base.Strings;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticateTest {
    private String id = "id";
    private String pwd = "pwd";
    private String nonExistingId = "nonExistingId";

    @Mock private UserRepository userRepository;

    @Before
    public void setUp() {
        when(userRepository.findById(nonExistingId)).thenReturn(null);
    }

    @Test
    public void whenIdOrPwdIsInvalid_thenThrowException() {
        authenticate_for_invalid_id_or_pwd(null, null);
        authenticate_for_invalid_id_or_pwd("", null);
        authenticate_for_invalid_id_or_pwd(id, null);
        authenticate_for_invalid_id_or_pwd(id, "");
    }

    private void authenticate_for_invalid_id_or_pwd(String id1, String pwd) {
        try {
            authenticate(id1, pwd);
            fail("InvalidIdOrPwd expected");
        } catch (InvalidIdOrPwd e) {
        }
    }

    @Test
    public void whenIdIsNotExisting_thenThrowException() {
        try {
            authenticate(nonExistingId, pwd);
            fail();
        } catch(IdNotFound ex) {
        }
    }

    private void authenticate(String id, String pwd) {
        if (Strings.isNullOrEmpty(id))
            throw new InvalidIdOrPwd();
        if (Strings.isNullOrEmpty(pwd))
            throw new InvalidIdOrPwd();
        User user = userRepository.findById(id);
        if(user == null)
            throw new IdNotFound();
    }

    private class InvalidIdOrPwd extends RuntimeException {
    }

    private class IdNotFound extends RuntimeException {
    }
}