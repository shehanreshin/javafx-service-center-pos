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

    public UserDto getLoggedInUser() {
        return loggedInUser;
    }

    public boolean isUserLoggedIn() {
        return loggedInUser == null;
    }

    public void setLoggedInUser(UserDto user) {
        loggedInUser = user;
    }
}
