package bo.custom;

import dto.UserDto;

import java.sql.SQLException;
import java.util.List;

public interface UserBo {
    boolean saveUser(UserDto dto) throws SQLException, ClassNotFoundException;
    List<UserDto> allUsers();
    boolean updateUser(UserDto dto);
    boolean deleteUser(String id);
    UserDto searchUser(String id);
    UserDto searchUserByEmail(String email) throws SQLException, ClassNotFoundException;
}
