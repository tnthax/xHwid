package me.tnthax.xhwid;

import lombok.Getter;
import lombok.Setter;
import org.jutils.jhardware.HardwareInfo;

import java.net.InetAddress;
import java.security.MessageDigest;

import static java.lang.System.getProperty;

/**
 * Class responsible for generating
 * hardware IDs from computer specifications.
 * @author tnthax
 */
public class HardwareIdentification {

    /**
     * The format that will be used to generate hardware IDs.
     * Placeholders: %username%, %osname%, %pcname%, %osarch%, %cpumodel%
     */
    @Getter @Setter
    private String identifierFormat = "%username%|%osname%|%pcname%|%osarch%|%cpumodel%";

    /**
     * Replaces the identifier format with the proper information.
     * @return identifierFormat with all placeholders replaced.
     */
    private String getIdentifierRaw() {
        String identifier = "";

        try {
            identifier = identifierFormat
                    .replace("%username%", getProperty("user.name"))
                    .replace("%osname%", getProperty("os.name"))
                    .replace("%pcname%", InetAddress.getLocalHost().getHostName())
                    .replace("%osarch%", getProperty("os.arch"))
                    .replace("%cpumodel%", HardwareInfo.getProcessorInfo().getModel());
        } catch (Exception exception) {
            System.err.println("Error while getting hardware identifier. Information printed below.");
            exception.printStackTrace();
        }

        return identifier;
    }

    /**
     * Hashes the raw identifier with SHA-512.
     * @return the hashed identifier
     */
    public String getIdentifier() {
        String identifier = getIdentifierRaw();

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            messageDigest.update(identifier.getBytes());
            StringBuffer hexString = new StringBuffer();
            byte[] byteData = messageDigest.digest();
            byte[] data = byteData;
            int length = byteData.length;

            for (int index = 0; index < length; index++) {
                byte aByteData = data[index];
                String hex = Integer.toHexString(255 & aByteData);
                if (hex.length() == 1) {
                    hexString.append('0');
                }

                hexString.append(hex);
            }

            identifier = hexString.toString();
        } catch (Exception exception) {
            System.err.println("Error while hashing hardware identifier. Information printed below.");
            exception.printStackTrace();
        }

        return identifier;
    }

    /**
     * Gets the string hash code of the raw identifier.
     * It is better to use an SHA-512 hashed identifier.
     * @return the hash code of the identifier
     */
    public int getIdentifierHashCode() {
        return getIdentifierRaw().hashCode();
    }

}
