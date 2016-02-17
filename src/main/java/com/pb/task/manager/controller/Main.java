package com.pb.task.manager.controller;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Mednikov on 16.02.2016.
 */
public class Main {

    private static final char[] kDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String s = "<ROOT><OKPO><LIST><TIN id_ekb='1004410776'>31435622</TIN><TIN id_ekb='1004410776'>31435622</TIN><TIN id_ekb='289226'>31451288</TIN><TIN id_ekb='289049'>32591606</TIN><TIN id_ekb='288985'>21504070</TIN><TIN id_ekb='1004410776'>31435622</TIN><TIN id_ekb='51785722'>3277715508</TIN><TIN id_ekb='1028308143'>34554355</TIN><TIN id_ekb='89555'>14001368</TIN><TIN id_ekb='29846438'>1505407621</TIN><TIN id_ekb='70886168'>33564306</TIN><TIN id_ekb='1004410776'>31435622</TIN><TIN id_ekb='12243442'>2924203694</TIN><TIN id_ekb='51785722'>3277715508</TIN><TIN id_ekb='126275'>31737159</TIN><TIN id_ekb='0'>14360570</TIN><TIN id_ekb='12243442'>2924203694</TIN></LIST></OKPO><USER_ID_P24>5264334</USER_ID_P24><LOGIN_P24>dn270989rsi8</LOGIN_P24><USER_ID_EKB>51785722</USER_ID_EKB><CHANNEL></CHANNEL><BANK>PB</BANK><LOCALE>UK</LOCALE><URL_BASE>https://p24jut2.ceb.loc/p24/</URL_BASE><ACCOUNTS_DEBET><ACC crf='3277715508' mfo='305299' ccy='UAH' main='0'>26205050003231</ACC><ACC crf='2931216249' mfo='305299' ccy='UAH' main='1'>26042050220266</ACC><ACC crf='31435622' mfo='305299' ccy='UAH' main='1'>26009000461342</ACC><ACC crf='31435622' mfo='305299' ccy='EUR' main='1'>26008050010915</ACC><ACC crf='31435622' mfo='305299' ccy='UAH' main='1'>26008000955763</ACC><ACC crf='31435622' mfo='305299' ccy='UAH' main='1'>26008000771981</ACC><ACC crf='31435622' mfo='305299' ccy='UAH' main='1'>26007050229912</ACC><ACC crf='31435622' mfo='305299' ccy='UAH' main='1'>26007050013247</ACC><ACC crf='31435622' mfo='305299' ccy='USD' main='1'>26007050011454</ACC><ACC crf='31435622' mfo='305299' ccy='UAH' main='1'>26007000486802</ACC><ACC crf='31435622' mfo='302689' ccy='UAH' main='1'>26006055314061</ACC><ACC crf='31435622' mfo='323583' ccy='UAH' main='1'>26006052909310</ACC><ACC crf='2931216249' mfo='305299' ccy='UAH' main='1'>26006050266181</ACC><ACC crf='31737159' mfo='305299' ccy='EUR' main='1'>26006050002174</ACC><ACC crf='31435622' mfo='305299' ccy='UAH' main='1'>26006000909346</ACC><ACC crf='2931216249' mfo='305750' ccy='UAH' main='1'>26005057001255</ACC><ACC crf='31435622' mfo='305299' ccy='UAH' main='1'>26005050014516</ACC><ACC crf='2931216249' mfo='384436' ccy='UAH' main='1'>26004057001757</ACC><ACC crf='31435622' mfo='305299' ccy='UAH' main='1'>26004050012360</ACC><ACC crf='31435622' mfo='305299' ccy='UAH' main='1'>26004000599327</ACC><ACC ccy='UAH' main='1'>26002053506456</ACC><ACC crf='31435622' mfo='305750' ccy='UAH' main='1'>26001050262158</ACC><ACC crf='31435622' mfo='305299' ccy='UAH' main='1'>26001050241811</ACC><ACC crf='2931216249' mfo='305750' ccy='UAH' main='1'>26001000514094</ACC><ACC crf='31435622' mfo='305750' ccy='UAH' main='1'>26000053507349</ACC><ACC crf='31435622' mfo='305299' ccy='UAH' main='1'>26000050252823</ACC><ACC crf='31435622' mfo='305299' ccy='RUR' main='1'>26000050008981</ACC><ACC crf='31435622' mfo='305299' ccy='CAD' main='1'>26000050007487</ACC><ACC crf='2924203694' mfo='305299' ccy='UAH' main='1'>26000050006682</ACC><ACC crf='31435622' mfo='305299' ccy='UAH' main='1'>26000000127652</ACC><ACC crf='31435622' mfo='305299' ccy='UAH' main='1'>26000000075555</ACC><ACC crf='31435622' mfo='305299' ccy='UAH' main='1'>20629050009389</ACC><ACC crf='2924203694' mfo='305299' ccy='UAH' main='1'>20629050008421</ACC><ACC crf='2924203694' mfo='305299' ccy='UAH' main='1'>20628050008563</ACC><ACC crf='31435622' mfo='305299' ccy='UAH' main='1'>20624050242732</ACC><ACC crf='31435622' mfo='305299' ccy='UAH' main='1'>20620050240556</ACC></ACCOUNTS_DEBET><ROLE>169</ROLE><PHONE></PHONE><ID_SIGN>8b7bc65e4abd1a700f1dd390973b40e837e9d117</ID_SIGN><CURRENT_TIME>1455698150429</CURRENT_TIME></ROOT>";
        //s = s.replace(" ", "");
        String rus = "<ROOT><OKPO><LIST><TIN id_ekb='1004410776'>31435622</TIN></LIST></OKPO><USER_ID_P24>13658004</USER_ID_P24><LOGIN_P24>тест_блокед</LOGIN_P24><USER_ID_EKB>19059530</USER_ID_EKB><CHANNEL></CHANNEL><BANK>PB</BANK><LOCALE>UK</LOCALE><URL_BASE>https://p24jut2.ceb.loc/p24/</URL_BASE><ACCOUNTS_DEBET><ACC crf='31435622' mfo='305299' ccy='UAH' main='1'>26009000461342</ACC><ACC crf='31435622' mfo='305299' ccy='UAH' main='1'>26008000955763</ACC><ACC crf='31435622' mfo='305299' ccy='UAH' main='1'>26007050229912</ACC><ACC crf='31435622' mfo='333391' ccy='UAH' main='1'>26005054711408</ACC><ACC crf='31435622' mfo='305750' ccy='UAH' main='1'>26001050262158</ACC><ACC crf='31435622' mfo='305750' ccy='UAH' main='1'>26000053507349</ACC><ACC crf='31435622' mfo='305299' ccy='UAH' main='1'>26000050252823</ACC><ACC crf='31435622' mfo='305299' ccy='UAH' main='1'>20629050009389</ACC></ACCOUNTS_DEBET><ROLE>145</ROLE><PHONE></PHONE><ID_SIGN>16238b2180ec20eb2c0c22602ee76298fd3beb81</ID_SIGN><CURRENT_TIME>1455698900319</CURRENT_TIME></ROOT>";
        String pass = "_haeK6Xeehieha9lo_";
//654e1216b88c381765c8190a9c1988d5d9c3a7b4
        byte[] bytes = SHA1("sdf", "UTF-8");
        System.out.println(s);
        System.out.println(bytesToHexStr(bytes));
        StringBuffer hexString = new StringBuffer();
        for (int i=0;i<bytes.length;i++) {
            String hex=Integer.toHexString(0xff & bytes[i]);
            if(hex.length()==1) hexString.append('0');
            hexString.append(hex);
        }
        System.out.println("Digest(in hex format):: " + hexString.toString());
    }
//51df0ecffb667abeac41db997cc63aa2b27fea14
    public static byte[] SHA1(String Param, String Encode) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest SHA = MessageDigest.getInstance("SHA-1");
        SHA.reset();
        System.out.println("Param.length()" + Param.length());
        System.out.println("Param.getBytes(Encode)" + Param.getBytes(Encode).length);
        SHA.update(Param.getBytes(Encode), 0, Param.length());
        return SHA.digest();
    }
//f3f16810721fade775a4e97d2f8f02e509f27b61
    public static String bytesToHexStr(byte[] raw) {
        int length = raw.length;
        char[] hex = new char[length * 2];
        for (int i = 0; i < length; i++) {
            int value = (raw[i] + 256) % 256;
            int highIndex = value >> 4;
            int lowIndex = value & 0x0f;
            hex[i * 2] = kDigits[highIndex];
            hex[i * 2 + 1] = kDigits[lowIndex];
        }
        return new String(hex);
    }


}
