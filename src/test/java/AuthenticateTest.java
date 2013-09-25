import com.google.common.base.Strings;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticateTest {
    private String id = "id";
    private String pwd = "pwd";
    private String nonExistingId = "nonExistingId";
    private String wrongPwd = "wrongPwd";

    @Mock private UserRepository userRepository;
    private User user = new User();

    @Before
    public void setUp() {
        user = spy(user);
        doThrow(WrongPassword.class).when(user).authenticate(wrongPwd);
        when(userRepository.findById(nonExistingId)).thenReturn(null);
        when(userRepository.findById(id)).thenReturn(user);
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

    @Test
    public void whenIdIsValid_thenReturnUser() {
        User user = authenticate(id, pwd);
        assertThat(user, notNullValue());
    }

    @Test public void whenPwdIsWrong_thenThrowException() {
        try {
            authenticate(id, wrongPwd);
            fail("WrongPassword expected");
        }
        catch(WrongPassword e) {
        }
    }

    private User authenticate(String id, String pwd) {
        if (Strings.isNullOrEmpty(id))
            throw new InvalidIdOrPwd();
        if (Strings.isNullOrEmpty(pwd))
            throw new InvalidIdOrPwd();
        User user = userRepository.findById(id);
        if(user == null)
            throw new IdNotFound();
        user.authenticate(pwd);
        return user;
    }

    private class InvalidIdOrPwd extends RuntimeException {
    }

    private class IdNotFound extends RuntimeException {
    }

    private class WrongPassword extends RuntimeException {
    }
}