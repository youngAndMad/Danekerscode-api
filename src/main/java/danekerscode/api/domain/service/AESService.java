package danekerscode.api.domain.service;

/**
 * This interface provides methods for AES encryption and decryption.
 */
public interface AESService {
    /**
     * Encrypts the given value using AES encryption.
     *
     * @param value the value to encrypt
     * @return the encrypted value
     */
    String encrypt(String value);

    /**
     * Decrypts the given value using AES decryption.
     *
     * @param value the value to decrypt
     * @return the decrypted value
     */
    String decrypt(String value);
}