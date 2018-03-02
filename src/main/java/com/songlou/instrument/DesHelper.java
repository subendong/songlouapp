package com.songlou.instrument;

import java.security.Key;
import java.security.Security;
import javax.crypto.Cipher;

/**
 * Des�ԳƼӽ����㷨
 * http://blog.csdn.net/qq_18870023/article/details/52180768
 * http://blog.csdn.net/xinxin19881112/article/details/46929157������ʹ�õ����URL����Ĵ���
 * @author sbd04462
 *
 */
public class DesHelper {
	//��Կ
	private static String strDefaultKey = "!@m.j1'5a9877@'/@#$%^&*()_+S4<><>'DF3G333S2'/D454?><}|{dddfd'fdaaaaa''";  
    private Cipher encryptCipher = null;
    private Cipher decryptCipher = null;  
    
    /** 
     * Ĭ�Ϲ��췽����ʹ��Ĭ����Կ 
     * @throws Exception 
     */  
    public DesHelper() throws Exception {  
        this(strDefaultKey);  
    }
  
    /** 
     * ָ����Կ���췽�� 
     * @param strKey ָ������Կ 
     * @throws Exception 
     */  
    @SuppressWarnings("restriction")
	public DesHelper(String strKey) throws Exception {  
        Security.addProvider(new com.sun.crypto.provider.SunJCE());  
        Key key = getKey(strKey.getBytes());  
        encryptCipher = Cipher.getInstance("DES");  
        encryptCipher.init(Cipher.ENCRYPT_MODE, key);  
        decryptCipher = Cipher.getInstance("DES");  
        decryptCipher.init(Cipher.DECRYPT_MODE, key);  
    }
  
    /** 
     * �����ַ��� 
     * @param strIn ����ܵ��ַ��� 
     * @return ���ܺ���ַ��� 
     * @throws Exception 
     */  
    public String encrypt(String strIn) throws Exception {  
        return byteArr2HexStr(encrypt(strIn.getBytes()));  
    }
      
    /** 
     * �����ֽ����� 
     * @param arrB ����ܵ��ֽ����� 
     * @return ���ܺ���ֽ����� 
     * @throws Exception 
     */  
    public byte[] encrypt(byte[] arrB) throws Exception {  
        return encryptCipher.doFinal(arrB);  
    }
      
    /** 
     * �����ַ��� 
     * @param strIn ����ܵ��ַ��� 
     * @return ���ܺ���ַ��� 
     * @throws Exception 
     */  
    public String decrypt(String strIn) throws Exception {  
        return new String(decrypt(hexStr2ByteArr(strIn)));  
    }
      
    /** 
     * �����ֽ����� 
     * @param arrB ����ܵ��ֽ����� 
     * @return ���ܺ���ֽ����� 
     * @throws Exception 
     */  
    public byte[] decrypt(byte[] arrB) throws Exception {  
        return decryptCipher.doFinal(arrB);  
    }
      
    /** 
     * ��ָ���ַ���������Կ����Կ������ֽ����鳤��Ϊ8λ 
     * ����8λʱ���油0������8λֻȡǰ8λ 
     * @param arrBTmp ���ɸ��ַ������ֽ����� 
     * @return ���ɵ���Կ 
     * @throws java.lang.Exception 
     */  
    private Key getKey(byte[] arrBTmp) throws Exception {  
        byte[] arrB = new byte[8];  
        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {  
            arrB[i] = arrBTmp[i];  
        }  
        Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");  
        return key;  
    }
      
    /**
     * ��byte����ת��Ϊ��ʾ16����ֵ���ַ����� 
     * �磺byte[]{8,18}ת��Ϊ��0813�� 
     * ��public static byte[] hexStr2ByteArr(String strIn) 
     * ��Ϊ�����ת������ 
     * @param arrB ��Ҫת����byte���� 
     * @return ת������ַ��� 
     * @throws Exception �������������κ��쳣�������쳣ȫ���׳� 
     */
    public static String byteArr2HexStr(byte[] arrB) throws Exception {  
        int iLen = arrB.length;  
        StringBuffer sb = new StringBuffer(iLen * 2);  
        for (int i = 0; i < iLen; i++) {  
            int intTmp = arrB[i];  
            while (intTmp < 0) {  
                intTmp = intTmp + 256;  
            }  
            if (intTmp < 16) {  
                sb.append("0");  
            }  
            sb.append(Integer.toString(intTmp, 16));  
        }  
        return sb.toString();  
    }
  
    /** 
     * ����ʾ16����ֵ���ַ���ת��Ϊbyte���飬 
     * ��public static String byteArr2HexStr(byte[] arrB) 
     * ��Ϊ�����ת������ 
     * @param strIn ��Ҫת�����ַ��� 
     * @return ת�����byte���� 
     * @throws Exception �������������κ��쳣�������쳣ȫ���׳� 
     */  
    public static byte[] hexStr2ByteArr(String strIn) throws Exception {  
        byte[] arrB = strIn.getBytes();  
        int iLen = arrB.length;  
        byte[] arrOut = new byte[iLen / 2];  
        for (int i = 0; i < iLen; i = i + 2) {  
            String strTmp = new String(arrB, i, 2);  
            arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);  
        }  
        return arrOut;  
    }
}
