package bo.custom;

import dto.UserDto;
import dto.wrapper.UserDtoWrapper;
import jakarta.mail.MessagingException;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

public interface UserBo {
    boolean saveUser(UserDto dto) throws SQLException, ClassNotFoundException;
    List<UserDto> allUsers() throws SQLException, ClassNotFoundException;
    boolean updateUser(UserDto dto);
    boolean deleteUser(String id);
    UserDto searchUser(String id);
    UserDto searchUserByEmail(String email) throws SQLException, ClassNotFoundException;
    boolean isUserCredentialsValid(String email, String password) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException;
    public void sendOtpByEmail(String recepient) throws MessagingException;
    public int getCurrentOtp();
    public boolean updatePassword(String email, String password) throws NoSuchAlgorithmException, SQLException, ClassNotFoundException;
}
