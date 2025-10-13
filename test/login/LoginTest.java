/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login;

/**
 *
 * @author RC_Student_lab
 */

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class LoginTest {

    private Login $login;

    @Before
    public void setup() {
        $login = new Login();
    }

    @Test

    public void usernameCorrectlyFormatted_and_loginProducesWelcomeMessage() {
        String $username = "kyl_1";
        String $password = "Ch&&sec@ke99!";
        String $cell = "+27838968976";
        String $regResult = $login.registerUser($username, $password, $cell);
        assertEquals("User successfully registered.", $regResult);

        boolean $loginOk = $login.loginUser($username, $password);
        assertTrue($loginOk);

        String $expectedWelcome = "Welcome kyl,1 it is great to see you.";
        assertEquals($expectedWelcome, $login.returnLoginStatus());
    }

    @Test
    public void usernameIncorrectlyFormatted_returnsSpecificMessage() {
        String $username = "kyle!!!!!!";
        String $password = "Ch&&sec@ke99!";
        String $cell = "+27838968976";
        String $regResult = $login.registerUser($username, $password, $cell);
        assertEquals(Login.USERNAME_FAILURE, $regResult);
    }

    @Test
    public void passwordMeetsComplexity_returnsTrueFromChecker() {
        String $password = "Ch&&sec@ke99!";
        assertTrue($login.checkPasswordComplexity($password));
    }

    @Test
    public void passwordDoesNotMeetComplexity_returnsSpecificMessage() {
        String $username = "abc_1";
        String $badPassword = "password";
        String $cell = "+27838968976";
        String $regResult = $login.registerUser($username, $badPassword, $cell);
        assertEquals(Login.PASSWORD_FAILURE, $regResult);
    }

    @Test
    public void cellPhoneCorrectlyFormatted_returnsSuccess() {
        String $username = "abc_1";
        String $password = "Ch&&sec@ke99!";
        String $cell = "+27838968976";
        String $regResult = $login.registerUser($username, $password, $cell);
        assertEquals("User successfully registered.", $regResult);
        assertTrue($login.checkCellPhoneNumber($cell));
    }

    @Test
    public void cellPhoneIncorrectlyFormatted_returnsSpecificMessage() {
        String $username = "abc_1";
        String $password = "Ch&&sec@ke99!";
        String $badCell = "08966553";
        String $regResult = $login.registerUser($username, $password, $badCell);
        assertEquals(Login.CELL_FAILURE, $regResult);
    }

    @Test
    public void loginSuccessful_returnsTrue() {
        String $username = "abc_1";
        String $password = "Ch&&sec@ke99!";
        String $cell = "+27838968976";
        $login.registerUser($username, $password, $cell);
        assertTrue($login.loginUser($username, $password));
    }

    @Test
    public void loginFailed_returnsFalse() {
        String $username = "abc_1";
        String $password = "Ch&&sec@ke99!";
        String $cell = "+27838968976";
        $login.registerUser($username, $password, $cell);
        assertFalse($login.loginUser($username, "wrongPassword1!"));
    }

    @Test
    public void usernameCorrectlyFormatted_returnsTrueFromChecker() {
        assertTrue($login.checkUserName("kyl_1"));
    }

    @Test
    public void usernameIncorrectlyFormatted_returnsFalseFromChecker() {
        assertFalse($login.checkUserName("kyle!!!!"));
    }

    @Test
    public void passwordDoesNotMeetComplexity_returnsFalseFromChecker() {
        assertFalse($login.checkPasswordComplexity("password"));
    }

    @Test
    public void cellPhoneNumberIncorrectlyFormatted_returnsFalseFromChecker() {
        assertFalse($login.checkCellPhoneNumber("08966553"));
    }
}



