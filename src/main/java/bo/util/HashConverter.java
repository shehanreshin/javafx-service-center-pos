package bo.util;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashConverter {
    private static HashConverter hashConverter;
    private MessageDigest messageDigest;

    private HashConverter() throws NoSuchAlgorithmException {
        messageDigest = MessageDigest.getInstance("SHA-256");
    }

    public static HashConverter getInstance() throws NoSuchAlgorithmException {
        return hashConverter == null ? (hashConverter = new HashConverter()) : hashConverter;
    }

    public MessageDigest getMessageDigest() {
        return messageDigest;
    }

    public byte[] getHash(String string) {
        return messageDigest.digest(string.getBytes(StandardCharsets.UTF_8));
    }

    public String toHexString(byte[] hash) {
        BigInteger digest = new BigInteger(1, hash); //the 1 is a signum - an indicator that the array is to be interpreted as a positive number by ensuring that the leading zeros are not treated as a sign bit.
        StringBuilder hexString = new StringBuilder(digest.toString(16)); //the 16 is an indicator the biginteger should be converted to a hexadecimal
        while (hexString.length() < 32) {
            hexString.insert(0, '0'); //padding with leading zeros to ensure flexbility if the hashing algorithm is changed at a later stage in development
        }
        return hexString.toString();
    }
}
