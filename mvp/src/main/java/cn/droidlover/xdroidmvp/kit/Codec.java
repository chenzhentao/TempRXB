package cn.droidlover.xdroidmvp.kit;

import android.util.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;


/**
 * Created by XU on 2018/5/7.
 */


public class Codec {
    public static class MD5 {

        public static String getMessageDigest(byte[] buffer) {
            char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            try {
                MessageDigest mdTemp = MessageDigest.getInstance(Algorithm.MD5.getType());
                mdTemp.update(buffer);
                byte[] md = mdTemp.digest();
                int j = md.length;
                char[] str = new char[j * 2];
                int k = 0;
                for (int i = 0; i < j; i++) {
                    byte byte0 = md[i];
                    str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                    str[k++] = hexDigits[byte0 & 0xf];
                }
                return new String(str);
            } catch (Exception e) {
                return null;
            }
        }

        /**
         * @param buffer
         * @return
         */
        public static byte[] getRawDigest(byte[] buffer) {
            try {
                MessageDigest mdTemp = MessageDigest.getInstance(Algorithm.MD5.getType());
                mdTemp.update(buffer);
                return mdTemp.digest();

            } catch (Exception e) {
                return null;
            }
        }


        private static String getMD5(final InputStream is, final int bufLen) {
            if (is == null || bufLen <= 0) {
                return null;
            }
            try {
                MessageDigest md = MessageDigest.getInstance(Algorithm.MD5.getType());
                StringBuilder md5Str = new StringBuilder(32);

                byte[] buf = new byte[bufLen];
                int readCount = 0;
                while ((readCount = is.read(buf)) != -1) {
                    md.update(buf, 0, readCount);
                }

                byte[] hashValue = md.digest();

                for (int i = 0; i < hashValue.length; i++) {
                    md5Str.append(Integer.toString((hashValue[i] & 0xff) + 0x100, 16).substring(1));
                }
                return md5Str.toString();
            } catch (Exception e) {
                return null;
            }
        }

        /**
         * @param filePath
         * @return
         */
        public static String getMD5(final String filePath) {
            if (filePath == null) {
                return null;
            }

            File f = new File(filePath);
            if (f.exists()) {
                return getMD5(f, 1024 * 100);
            }
            return null;
        }

        /**
         * @param file
         * @return
         */
        public static String getMD5(final File file) {
            return getMD5(file, 1024 * 100);
        }


        private static String getMD5(final File file, final int bufLen) {
            if (file == null || bufLen <= 0 || !file.exists()) {
                return null;
            }

            FileInputStream fin = null;
            try {
                fin = new FileInputStream(file);
                String md5 = getMD5(fin, (int) (bufLen <= file.length() ? bufLen : file.length()));
                fin.close();
                return md5;

            } catch (Exception e) {
                return null;

            } finally {
                try {
                    if (fin != null) {
                        fin.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    public static class BASE64 {

        public static byte[] encode(byte[] plain) {
            return Base64.encode(plain, Base64.DEFAULT);
        }

        public static String encodeToString(byte[] plain) {
            return Base64.encodeToString(plain, Base64.DEFAULT);
        }

        public static byte[] decode(String text) {
            return Base64.decode(text, Base64.DEFAULT);
        }

        public static byte[] decode(byte[] text) {
            return Base64.decode(text, Base64.DEFAULT);
        }
    }


    public static class SHA {

        public static byte[] encrypt(byte[] data) throws Exception {
            MessageDigest sha = MessageDigest.getInstance(Algorithm.SHA.getType());
            sha.update(data);
            return sha.digest();
        }

    }


    public static class MAC {
        public static String initMacKey(Algorithm algorithm) throws Exception {
            if (algorithm == null) algorithm = Algorithm.Hmac_MD5;
            KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm.getType());
            SecretKey secretKey = keyGenerator.generateKey();

            return BASE64.encodeToString(secretKey.getEncoded());
        }

        public static byte[] encrypt(byte[] plain, String key, Algorithm algorithm) throws Exception {
            if (algorithm == null) algorithm = Algorithm.Hmac_MD5;
            SecretKey secretKey = new SecretKeySpec(BASE64.decode(key), algorithm.getType());
            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);

            return mac.doFinal(plain);
        }
    }


    public static class DES {

        private static Key toKey(byte[] key) throws Exception {
            DESKeySpec dks = new DESKeySpec(key);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(Algorithm.DES.getType());
            return keyFactory.generateSecret(dks);
        }

        public static byte[] decrypt(byte[] plain, String key) throws Exception {
            Key k = toKey(BASE64.decode(key));

            Cipher cipher = Cipher.getInstance(Algorithm.DES.getType());
            cipher.init(Cipher.DECRYPT_MODE, k);

            return cipher.doFinal(plain);
        }

        public static byte[] encrypt(byte[] data, String key) throws Exception {
            Key k = toKey(BASE64.decode(key));
            Cipher cipher = Cipher.getInstance(Algorithm.DES.getType());
            cipher.init(Cipher.ENCRYPT_MODE, k);

            return cipher.doFinal(data);
        }

        public static String initKey() throws Exception {
            return initKey(null);
        }

        public static String initKey(String seed) throws Exception {
            SecureRandom secureRandom = null;

            if (seed != null) {
                secureRandom = new SecureRandom(BASE64.decode(seed));
            } else {
                secureRandom = new SecureRandom();
            }

            KeyGenerator kg = KeyGenerator.getInstance(Algorithm.DES.getType());
            kg.init(secureRandom);

            SecretKey secretKey = kg.generateKey();

            return BASE64.encodeToString(secretKey.getEncoded());
        }
    }


    public static class RSA {

        public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

        private static final String PUBLIC_KEY = "RSAPublicKey";
        private static final String PRIVATE_KEY = "RSAPrivateKey";


        /**
         *
         * @param data
         * @param privateKey
         * @return
         * @throws Exception
         */
        public static String sign(byte[] data, String privateKey) throws Exception {
            byte[] keyBytes = BASE64.decode(privateKey);
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(Algorithm.RSA.getType());
            PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initSign(priKey);
            signature.update(data);

            return BASE64.encodeToString(signature.sign());
        }

        /**
         * @return
         * @throws Exception
         */
        public static boolean verify(byte[] data, String publicKey, String sign)
                throws Exception {

            byte[] keyBytes = BASE64.decode(publicKey);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(Algorithm.RSA.getType());
            PublicKey pubKey = keyFactory.generatePublic(keySpec);

            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initVerify(pubKey);
            signature.update(data);

            return signature.verify(BASE64.decode(sign));
        }

        /**
         * @param data
         * @param key
         * @return
         * @throws Exception
         */
        public static byte[] decryptByPrivateKey(byte[] data, String key)
                throws Exception {
            byte[] keyBytes = BASE64.decode(key);

            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(Algorithm.RSA.getType());
            Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            return cipher.doFinal(data);
        }

        /**
         * @param data
         * @param key
         * @return
         * @throws Exception
         */
        public static byte[] decryptByPublicKey(byte[] data, String key)
                throws Exception {
            byte[] keyBytes = BASE64.decode(key);


            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(Algorithm.RSA.getType());
            Key publicKey = keyFactory.generatePublic(x509KeySpec);


            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, publicKey);

            return cipher.doFinal(data);
        }

        /**
         *
         * @param data
         * @param key
         * @return
         * @throws Exception
         */
        public static byte[] encryptByPublicKey(byte[] data, String key)
                throws Exception {
            byte[] keyBytes = BASE64.decode(key);


            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(Algorithm.RSA.getType());
            Key publicKey = keyFactory.generatePublic(x509KeySpec);

            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            return cipher.doFinal(data);
        }

        /**
         *
         * @param data
         * @param key
         * @return
         * @throws Exception
         */
        public static byte[] encryptByPrivateKey(byte[] data, String key)
                throws Exception {

            byte[] keyBytes = BASE64.decode(key);


            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(Algorithm.RSA.getType());
            Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);

            return cipher.doFinal(data);
        }

        /**
         *
         * @param keyMap
         * @return
         * @throws Exception
         */
        public static String getPrivateKey(Map<String, Object> keyMap)
                throws Exception {
            Key key = (Key) keyMap.get(PRIVATE_KEY);

            return BASE64.encodeToString(key.getEncoded());
        }

        /**
         *
         * @param keyMap
         * @return
         * @throws Exception
         */
        public static String getPublicKey(Map<String, Object> keyMap)
                throws Exception {
            Key key = (Key) keyMap.get(PUBLIC_KEY);

            return BASE64.encodeToString(key.getEncoded());
        }

        /**
         *
         * @return
         * @throws Exception
         */
        public static Map<String, Object> initKey() throws Exception {
            KeyPairGenerator keyPairGen = KeyPairGenerator
                    .getInstance(Algorithm.RSA.getType());
            keyPairGen.initialize(1024);

            KeyPair keyPair = keyPairGen.generateKeyPair();
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
            Map<String, Object> keyMap = new HashMap<String, Object>(2);

            keyMap.put(PUBLIC_KEY, publicKey);
            keyMap.put(PRIVATE_KEY, privateKey);
            return keyMap;
        }

    }

    public static boolean checkNull(String text) {
        return null == text || text.length() == 0;
    }

    public enum Algorithm {
        SHA("SHA"),
        MD5("MD5"),
        Hmac_MD5("HmacMD5"),
        Hmac_SHA1("HmacSHA1"),
        Hmac_SHA256("HmacSHA256"),
        Hmac_SHA384("HmacSHA384"),
        Hmac_SHA512("HmacSHA512"),
        DES("DES"),
        RSA("RSA");

        private String type;

        Algorithm(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }
}
