package vj.com.androidmvpsample.login;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import vj.com.androidmvpsample.R;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by vivekjain on 5/31/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest {
  @Mock
  private LoginView view;
  @Mock
  private LoginService service;
  private LoginPresenter presenter;

  @Before
  public void setUp() throws Exception {
    //gettig/calling reference to the presenter class
    presenter = new LoginPresenter(view, service);
  }

  @Test
  //1st test conducting that username shouldnot be empty if empty please show error message
  public void shouldShowErrorMessageWhenUsernameIsEmpty() throws Exception {
    //excepting that username is empty
    when(view.getUsername()).thenReturn("");
    presenter.onLoginClicked();

    verify(view).showUsernameError(R.string.username_error);
  }

  @Test
  //2nd test conducting that password shouldnot be empty if empty please show error message and should contain username
  public void shouldShowErrorMessageWhenPasswordIsEmpty() throws Exception {
    when(view.getUsername()).thenReturn("james");
    when(view.getPassword()).thenReturn("");
    presenter.onLoginClicked();

    verify(view).showPasswordError(R.string.password_error);
  }

  @Test
  //3rd test conducting that username and pasword is james and bond then go to main activity class
  public void shouldStartMainActivityWhenUsernameAndPasswordAreCorrect() throws Exception {
    when(view.getUsername()).thenReturn("james");
    when(view.getPassword()).thenReturn("bond");
    //when service is true then return main activity
    when(service.login("james", "bond")).thenReturn(true);
    presenter.onLoginClicked();

    verify(view).startMainActivity();
  }

  @Test
  public void shouldShowLoginErrorWhenUsernameAndPasswordAreInvalid() throws Exception {
    when(view.getUsername()).thenReturn("james");
    when(view.getPassword()).thenReturn("bond");
    //when service is fasle show error mssage
    when(service.login("james", "bond")).thenReturn(false);
    presenter.onLoginClicked();

    verify(view).showLoginError(R.string.login_failed);
  }
}