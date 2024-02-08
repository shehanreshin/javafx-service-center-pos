package bo.custom.impl;

import bo.custom.UserBo;
import bo.util.HashConverter;
import bo.util.Mailer;
import dao.custom.UserDao;
import dao.custom.impl.UserDaoImpl;
import dto.UserDto;
import dto.wrapper.UserDtoWrapper;
import entity.User;
import jakarta.mail.MessagingException;
import lombok.Getter;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserBoImpl implements UserBo {
    private final UserDao userDao = new UserDaoImpl();
    @Getter
    private int currentOtp;

    @Override
    public boolean saveUser(UserDto dto) throws SQLException, ClassNotFoundException {
        return userDao.save(
                new User(
                        dto.getUserId(),
                        dto.getName(),
                        dto.getEmail(),
                        dto.getPassword(),
                        dto.getRole()
                )
        );
    }

    @Override
    public List<UserDto> allUsers() {
        return null;
    }

    @Override
    public boolean updateUser(UserDto dto) {
        return false;
    }

    @Override
    public boolean deleteUser(String id) {
        return false;
    }

    @Override
    public UserDto searchUser(String id) {
        return null;
    }

    @Override
    public UserDto searchUserByEmail(String email) throws SQLException, ClassNotFoundException {
        User selectedUser = getUserEntityByEmail(email);
        return selectedUser == null ? null : new UserDto(
                selectedUser.getId(),
                selectedUser.getName(),
                selectedUser.getEmail(),
                selectedUser.getPassword(),
                selectedUser.getRole()
        );
    }

    private User getUserEntityByEmail(String email) throws SQLException, ClassNotFoundException {
        List<User> userList = userDao.getAll();
        User selectedUser = null;
        for (User user : userList) {
            if (email.equals(user.getEmail())) {
                selectedUser = user;
                break;
            }
        }
        return selectedUser;
    }

    @Override
    public boolean isUserCredentialsValid(String email, String password) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
        Optional<UserDto> storedUser = Optional.ofNullable(searchUserByEmail(email));
        if (!storedUser.isPresent()) {
            return false;
        }
        String hashedPassword = HashConverter.getInstance().toHexString(
                        HashConverter.getInstance().getHash(password)
        );
        return storedUser.get().getPassword().equals(hashedPassword);
    }

    private void generateOtp() {
        currentOtp = (int) (Math.random() * 10000) + 10000;
    }

    @Override
    public void sendOtpByEmail(String recepient) throws MessagingException {
        generateOtp();
        String body = "Hi, <br><br> Please use the following One Time Password (OTP) to reset your password: <b>" + currentOtp + "</b>. Do not share this OTP with anyone. Thank you! <br><br>E&E Service Center";
        Mailer mailer = Mailer.getInstance();
        mailer.sendEmail(mailer.draftEmail(recepient, "E&E Service Center Account Password Reset", body));
    }

    @Override
    public boolean updatePassword(String email, String password) throws NoSuchAlgorithmException, SQLException, ClassNotFoundException {
        HashConverter hashConverter = HashConverter.getInstance();
        String hashedPassword = hashConverter.toHexString(hashConverter.getHash(password));
        User user = getUserEntityByEmail(email);
        user.setPassword(hashedPassword);
        return userDao.update(user);
    }
}
