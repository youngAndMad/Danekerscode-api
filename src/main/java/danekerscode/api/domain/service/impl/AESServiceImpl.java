package danekerscode.api.domain.service.impl;

import danekerscode.api.domain.service.AESService;
import danekerscode.api.common.exception.AESException;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

@Service
public class AESServiceImpl implements AESService {

    @Value("${aes.secret}")
    private String aesSecret;

    @Value("${aes.transformation}")
    private String transformation;

    @Value("${aes.algorithm}")
    private String algorithm;

    private static final String EMPTY_STRING = "";

    public String encrypt(String value) {
        try {

            var secretKey = new SecretKeySpec(aesSecret.getBytes(), algorithm);
            var cipher = Cipher.getInstance(transformation);

            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            var encryptedValue = cipher.doFinal(value.getBytes());

            return Base64.encodeBase64String(encryptedValue);
        } catch (Exception e) {
            throw new AESException(e);
        }
    }

    public String decrypt(String encryptedValue) {
        if (encryptedValue.isEmpty()) {
            return EMPTY_STRING;
        }

        try {
            var secretKey = new SecretKeySpec(aesSecret.getBytes(), algorithm);
            var cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            var decodedValue = Base64.decodeBase64(encryptedValue);
            var decryptedValue = cipher.doFinal(decodedValue);

            return new String(decryptedValue);
        } catch (Exception e) {
            throw new AESException(e);
        }

    }
}
