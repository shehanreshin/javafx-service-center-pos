package bo.custom;

import dto.UserDto;
import dto.wrapper.UserDtoWrapper;
import entity.util.UserRole;
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
    void sendOtpByEmail(String recepient) throws MessagingException;
    int getCurrentOtp();
    boolean updatePassword(String email, String password) throws NoSuchAlgorithmException, SQLException, ClassNotFoundException;
    boolean updateRole(Long id, UserRole role) throws SQLException, ClassNotFoundException;
    boolean updateStatus(Long id, boolean isActive) throws SQLException, ClassNotFoundException;
}
