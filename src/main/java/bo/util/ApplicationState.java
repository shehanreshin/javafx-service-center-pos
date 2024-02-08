package bo.util;

import dto.UserDto;
import entity.util.UserRole;

public class ApplicationState {
    private static ApplicationState applicationState;
    private UserDto loggedInUser;

    private ApplicationState() {}

    public static ApplicationState getInstance() {
        return applicationState == null ? (applicationState = new ApplicationState()) : applicationState;
    }

    private UserDto getLoggedInUser() {
        return loggedInUser;
    }

    private UserRole getRoleOfLoggedInUser() {
        return loggedInUser.getRole();
    }

    private boolean isUserLoggedIn() {
        return loggedInUser == null;
    }
}
