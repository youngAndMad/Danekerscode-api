package danekerscode.api.service;

public interface AESService {
    String encrypt(String value);

    String decrypt(String value);
}
