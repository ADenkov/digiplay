package digiplayauthservice.util;

public class Base64Util {


    public static String decodeBytes(byte[] array) {
        StringBuilder byteString = new StringBuilder();
        for (byte decodedByte : array) {
            char c = (char) decodedByte;
            byteString.append(c);
        }

        return byteString.toString();
    }
}
