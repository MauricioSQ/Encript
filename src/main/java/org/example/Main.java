package org.example;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class Main {
    public static void main(String[] args) {
        try {
            // Añadir el proveedor de Bouncy Castle
            Security.addProvider(new BouncyCastleProvider());

            // Clave pública en formato PEM, es decir, si
            String publicKeyPEM = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAz23fgSUHIuoYjWRYvSMI"
                    + "IP+x8MpsjDcpxzaIPg4mjtrKmq8kfyQGUWorV2kcwkyIfwBngq5UsXFbO5bOoroz"
                    + "qzcxSpmfjw2uZFGtBNMZf7OYStMKBf54UtkoO47v929+50eFV6sfqF5SaoUV1ls/"
                    + "e6TDGyzZh4vbos1StgPYXnoTgSV26gyQTj5Kmu4pvx2RjSRP3l5Z6HUCAGK3vft6"
                    + "IEEIK6bbdOs05Gvoub1/Kl4ojQVq9LTfzEr+fhE1oGt0ZgsefkMz4aWkHG8hRvUm"
                    + "kipzcf/21sv/Dxv2kWD9AHUH3nEV9D2bYrD4oa5Vc1+mrY3p4c42pymh6mfFpGuG"
                    + "SQIDAQAB";

            // Decodificar la clave pública
            byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyPEM);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(keySpec);

            // Datos a encriptar
            String id = "D9383832";

            // Encriptar los datos
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encryptedId = cipher.doFinal(id.getBytes());

            // Imprimir los datos encriptados en base64
            String encodedId = Base64.getEncoder().encodeToString(encryptedId);
            System.out.println("Datos encriptados: " + encodedId);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}