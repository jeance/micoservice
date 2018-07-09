package com.te.micoservice.common.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

/**
 * Created by cxj on 2017/9/25.
 */

public class DESUtil {
    private static final byte[] DESkey = "basecrys".getBytes();// 设置密钥、
    private static final byte[] DESIV = {116, 99, 98, 97, 115, 101, 112, 97};
    private static byte[] Encryptiv = {0x12, 0x34, 0x56, 0x78, (byte) 0x90, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF};
    private static String Encryptkey = "2tRvm0Bdhok=";

    //private static final String CommonInterfaceEncryptIv = "dgg6EzYSQOc=" ;    //;{100, 103, 103, 54, 69, 122, 89, 83};  //.getBytes();
    private static final byte[] CommonInterfaceEncryptIv = {0x76, 0x8, 0x3A, 0x13, 0x36, 0x12, 0x40, (byte) 0xE7};// {(byte)0x96}   ;//"lhXnwpzdaPe+bg69oG3Fln3gC274Chxc";
    private static final String CommonInterfaceEncryptKey = "lhXnwpzdaPe+bg69oG3Fln3gC274Chxc";
    private static final String ALGORITHM = "DESede";
    private static SecretKey deskey = null;
    private static final String CIPHER_ALGORITHM_CBC = "DESede/CBC/PKCS5Padding";

    private static AlgorithmParameterSpec iv = null;// 加密算法的参数接口，IvParameterSpec是它的一个实现
    private static Key key = null;
    private static final String PKCS5Padding = "DES/CBC/PKCS5Padding";
    private static final String PKCS7Padding = "DES/CBC/PKCS7Padding";//java 不支持，先采用base64补全，采用PKCS5Padding填充模式

    /**
     * 获取密钥对象
     */
    private static Key GetDESCbcEncodeKey() throws Exception {
        if (key == null) {
            DESKeySpec keySpec = new DESKeySpec(DESkey);// 设置密钥参数
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");// 获得密钥工厂
            key = keyFactory.generateSecret(keySpec);// 得到密钥对象
        }
        return key;
    }

    /**
     * 获取初始化向量
     */
    private static AlgorithmParameterSpec GetDESCbcEncodeIv() {
        if (iv == null) {
            iv = new IvParameterSpec(DESIV);// 设置向量
        }
        return iv;
    }


    /**
     * 获取密钥对象
     */
    private static Key GetEncryptKey() throws Exception {
        if (key == null) {
            byte[] keyBytes = new BASE64Decoder().decodeBuffer(Encryptkey);
            DESKeySpec keySpec = new DESKeySpec(keyBytes);// 设置密钥参数
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");// 获得密钥工厂
            key = keyFactory.generateSecret(keySpec);// 得到密钥对象
        }
        return key;
    }

    /**
     * 获取Commoninterface密钥对象
     */
    private static SecretKey GetCommoninterfaceEncryptKey() throws Exception {
        if (deskey == null) {
            byte[] keyBytes = new BASE64Decoder().decodeBuffer(CommonInterfaceEncryptKey);
            deskey = new SecretKeySpec(keyBytes, ALGORITHM);
        }
        return deskey;
    }

    /**
     * 获取Commoninterface初始化向量
     */
    private static AlgorithmParameterSpec GetCommonInterfaceEncodeIv() throws Exception {
        if (iv == null) {
            //   byte[] commonInterfaceEncryptIv= new BASE64Decoder().decodeBuffer(CommonInterfaceEncryptIv);
            iv = new IvParameterSpec(CommonInterfaceEncryptIv);// 设置向量
        }
        return iv;
    }

    /*
       *获取初始化向量
       */
    private static AlgorithmParameterSpec GetDecryptIv() {
        if (iv == null) {
            iv = new IvParameterSpec(Encryptiv);// 设置向量
        }
        return iv;
    }

    /**
     * 获取DES CBC模式加密采用PKCS5Padding填充模式的加密串
     */
    public static String DesCbcEncode(String data, String encoding) throws Exception {
        Key key = GetDESCbcEncodeKey();
        AlgorithmParameterSpec iv = GetDESCbcEncodeIv();
        return Encode(data, key, iv, PKCS5Padding, encoding);
    }

    /**
     * 获取DES CBC模式加密采用PKCS5Padding填充模式的解密串
     */
    public static String DesCbcDecode(String data, String encoding) throws Exception {
        Key key = GetDESCbcEncodeKey();
        AlgorithmParameterSpec iv = GetDESCbcEncodeIv();
        return Decode(data, key, iv, PKCS5Padding, encoding);
    }

    /**
     * 获取DES CBC模式加密采用PKCS5Padding填充模式的加密串，同等于.net des加密 采用PKCS7Padding填充模式
     */
    public static String Encrypt(String data, String encoding) throws Exception {
        Key key = GetEncryptKey();
        AlgorithmParameterSpec iv = GetDecryptIv();
        return Encode(data, key, iv, PKCS5Padding, encoding);
    }

    /**
     * 获取DES CBC模式加密采用PKCS5Padding填充模式的解密串，同等于.net des解密 采用PKCS7Padding填充模式
     */
    public static String Decrypt(String data, String encoding) throws Exception {
        Key key = GetEncryptKey();
        AlgorithmParameterSpec iv = GetDecryptIv();
        return Decode(data, key, iv, PKCS5Padding, encoding);
    }


    //加密方法
    private static String Encode(String data, Key key, AlgorithmParameterSpec iv, String paddingMode, String encoding) throws Exception {

        Cipher enCipher = Cipher.getInstance(paddingMode);// 得到加密对象Cipher
        enCipher.init(Cipher.ENCRYPT_MODE, key, iv);// 设置工作模式为加密模式，给出密钥和向量
        byte[] pasByte = enCipher.doFinal(data.getBytes(encoding));
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return base64Encoder.encode(pasByte);
    }

    //加密方法
    private static byte[] EncodeToByte(String data, Key key, AlgorithmParameterSpec iv, String paddingMode, String encoding) throws Exception {

        Cipher enCipher = Cipher.getInstance(paddingMode);// 得到加密对象Cipher
        enCipher.init(Cipher.ENCRYPT_MODE, key, iv);// 设置工作模式为加密模式，给出密钥和向量
        byte[] dataBytes = data.getBytes(encoding);
        byte[] pasByte = enCipher.doFinal(dataBytes);
        return pasByte;
    }


    //解密方法
    private static String Decode(String data, Key key, AlgorithmParameterSpec iv, String paddingMode, String encoding) throws Exception {
        Cipher deCipher = Cipher.getInstance(paddingMode);
        deCipher.init(Cipher.DECRYPT_MODE, key, iv);
        BASE64Decoder base64Decoder = new BASE64Decoder();
        byte[] pasByte = deCipher.doFinal(base64Decoder.decodeBuffer(data));

        return new String(pasByte, encoding);
    }


    /**
     * 获取commoninterface的加密数据
     */
    public static String CommonInterfaceEncrypt(String data, String encoding) throws Exception {
        SecretKey key = GetCommoninterfaceEncryptKey();
        AlgorithmParameterSpec iv = GetCommonInterfaceEncodeIv();
        Cipher c1 = Cipher.getInstance(CIPHER_ALGORITHM_CBC);
        c1.init(Cipher.ENCRYPT_MODE, key, iv);
        // byte [] pasByte= EncodeToByte(data, key, iv, PKCS5Padding, encoding);
        byte[] dataBytes = data.getBytes(encoding);
        byte[] pasByte = c1.doFinal(dataBytes);
        BASE64Encoder base64Encoder = new BASE64Encoder();
        // return base64Encoder.encode(pasByte);
        String result = ByteToAsci(pasByte);
        return result;
    }

    /**
     * 获取Commoninterface的解密数据
     */
    public static String CommonInterfaceDecrypt(String data, String encoding) throws Exception {
        SecretKey key = GetCommoninterfaceEncryptKey();
        AlgorithmParameterSpec iv = GetCommonInterfaceEncodeIv();
        Cipher c1 = Cipher.getInstance(CIPHER_ALGORITHM_CBC);
        c1.init(Cipher.DECRYPT_MODE, key, iv);
        byte[] dataBytes = AsciToByte(data,encoding);
        byte[] pasByte = c1.doFinal(dataBytes);
        String result = AsciToString(pasByte, encoding);
        return result;
    }


    /// <summary>
    /// ASCI转字节
    /// </summary>
    /// <param name="asics">ASCI</param>
    /// <returns>字节</returns>
    private static byte[] AsciToByte(String oriString, String encoding) throws Exception {
        int length = oriString.length() / 3;
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++) {
            String subStr = oriString.substring(i * 3,i * 3+3);
            bytes[i] =   GetByteFromAsci(subStr);
        }
        return bytes;
    }

    /// <summary>
    /// 根据ASCI获取字节真实值
    /// </summary>
    /// <param name="asci">ASCI</param>
    /// <returns>字节真实值</returns>
    private static byte GetByteFromAsci(String subStr)
    {
        String zeroArea = subStr.substring(0, 2);
        byte result=(byte)0;
        int temp=0;
        if ("00".equals(zeroArea)) {
            temp=Integer.parseInt(subStr.substring(2, 3));
            result=(byte)temp;
            return result;
        }
        zeroArea = subStr.substring(0, 1);
        if ("0".equals(zeroArea))
        {
            temp=Integer.parseInt(subStr.substring(1, 3));
            result=(byte)temp;
            return result;
        }
        temp=Integer.parseInt(subStr);
        result=(byte) temp;
        return result;
    }
    private static String AsciToString(byte[] resultArray, String encoding) throws Exception {
        int index = 0;
        for (int i = resultArray.length; i > 0; i--) {
            if ((byte) 0 == resultArray[i - 1]) {
                continue;
            }
            index = i;
            break;
        }
        String result = new String(resultArray, 0, index, encoding);
        return result;
    }

    private static String ByteToAsci(byte[] bytes) {
        String result = "";
        for (byte b : bytes) {
            result += GetString(b & 0xFF);
        }
        return result;
    }

    /// <summary>
    /// 根据ASCI，得到加密数据数值
    /// </summary>
    /// <param name="asci">ASCI</param>
    /// <returns>加密数据数值</returns>
    private static String GetString(int asci) {
        String result = String.valueOf(asci);
        switch (result.length()) {
            case 1:
                return "00" + result;
            case 2:
                return "0" + result;
            default:
                return result;
        }
    }


}

