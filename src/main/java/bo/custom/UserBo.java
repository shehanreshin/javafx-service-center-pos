package bo.custom;

import dto.UserDto;
import dto.wrapper.UserDtoWrapper;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

public interface UserBo {
    boolean saveUser(UserDto dto) throws SQLException, ClassNotFoundException;
    List<UserDto> allUsers();
    boolean updateUser(UserDto dto);
    boolean deleteUser(String id);
    UserDto searchUser(String id);
    UserDto searchUserByEmail(String email) throws SQLException, ClassNotFoundException;
    boolean isUserCredentialsValid(UserDtoWrapper userCredentials) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException;
}
