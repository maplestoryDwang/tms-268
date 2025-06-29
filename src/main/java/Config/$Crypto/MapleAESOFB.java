package Config.$Crypto;

import tools.BitTools;
import tools.HexTool;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author TMS-HERTZ
 * @version 262.5
 * @return key以及加解密處理
 */
public class MapleAESOFB {

    private static final String[] skeys = new String[]{"2923BE84E16CD6AE529049F1F1BBE9EBB3A6DB3C870C3E99245E0D1C06B747DE", "B3124DC843BB8BA61F035A7D0938251F5DD4CBFC96F5453B130D890A1CDBAE32", "888138616B681262F954D0E7711748780D92291D86299972DB741CFA4F37B8B5", "209A50EE407836FD124932F69E7D49DCAD4F14F2444066D06BC430B7323BA122", "F622919DE18B1FDAB0CA9902B9729D492C807EC599D5E980B2EAC9CC53BF67D6", "BF14D67E2DDC8E6683EF574961FF698F61CDD11E9D9C167272E61DF0844F4A77", "02D7E8392C53CBC9121E33749E0CF4D5D49FD4A4597E35CF3222F4CCCFD3902D", "48D38F75E6D91D2AE5C0F72B788187440E5F5000D4618DBE7B0515073B33821F", "187092DA6454CEB1853E6915F8466A0496730ED9162F6768D4F74A4AD0576876", "5B628A8A8F275CF7E5874A3B329B614084C6C3B1A7304A10EE756F032F9E6AEF", "762DD0C2C9CD68D4496A792508614014B13B6AA51128C18CD6A90B87978C2FF1", "10509BC8814329288AF6E99E47A18148316CCDA49EDE81A38C9810FF9A43CDCF", "5E4EE1309CFED9719FE2A5E20C9BB44765382A4689A982797A7678C263B126DF", "DA296D3E62E0961234BF39A63F895EF16D0EE36C28A11E201DCBC2033F410784", "0F1405651B2861C9C5E72C8E463608DCF3A88DFEBEF2EB71FFA0D03B75068C7E", "8778734DD0BE82BEDBC246412B8CFA307F70F0A754863295AA5B68130BE6FCF5", "CABE7D9F898A411BFDB84F68F6727B1499CDD30DF0443AB4A66653330BCBA110", "5E4CEC034C73E605B4310EAAADCFD5B0CA27FFD89D144DF4792759427C9CC1F8", "CD8C87202364B8A687954CB05A8D4E2D99E73DB160DEB180AD0841E96741A5D5", "9FE4189F15420026FE4CD12104932FB38F735340438AAF7ECA6FD5CFD3A195CE"};


    /**
     * @author by Taiwan twms and SyncTwMs team [ Hertz ]  fix...
     * @version TMS v245
     * 註記: KEY ZLZ.DLL
     * 時間: 2023-8
     */

    private static SecretKeySpec skey = new SecretKeySpec(new byte[]{90, 0, 0, 0, 42, 0, 0, 0, -95, 0, 0, 0, -76, 0, 0, 0, 50, 0, 0, 0, 77, 0, 0, 0, 86, 0, 0, 0, -56, 0, 0, 0}, "AES");



    private static final byte[] funnyBytes = new byte[]{-20, 63, 119, -92, 69, -48, 113, -65, -73, -104, 32, -4, 75, -23, -77, -31, 92, 34, -9, 12, 68, 27, -127, -67, 99, -115, -44, -61, -14, 16, 25, -32, -5, -95, 110, 102, -22, -82, -42, -50, 6, 24, 78, -21, 120, -107, -37, -70, -74, 66, 122, 42, -125, 11, 84, 103, 109, -24, 101, -25, 47, 7, -13, -86, 39, 123, -123, -80, 38, -3, -117, -87, -6, -66, -88, -41, -53, -52, -110, -38, -7, -109, 96, 45, -35, -46, -94, -101, 57, 95, -126, 33, 76, 105, -8, 49, -121, -18, -114, -83, -116, 106, -68, -75, 107, 89, 19, -15, 4, 0, -10, 90, 53, 121, 72, -113, 21, -51, -105, 87, 18, 62, 55, -1, -99, 79, 81, -11, -93, 112, -69, 20, 117, -62, -72, 114, -64, -19, 125, 104, -55, 46, 13, 98, 70, 23, 17, 77, 108, -60, 126, 83, -63, 37, -57, -102, 28, -120, 88, 44, -119, -36, 2, 100, 64, 1, 93, 56, -91, -30, -81, 85, -43, -17, 26, 124, -89, 91, -90, 111, -122, -97, 115, -26, 10, -34, 43, -103, 74, 71, -100, -33, 9, 118, -98, 48, 14, -28, -78, -108, -96, 59, 52, 29, 40, 15, 54, -29, 35, -76, 3, -40, -112, -56, 60, -2, 94, 50, 36, 80, 31, 58, 67, -118, -106, 65, 116, -84, 82, 51, -16, -39, 41, -128, -79, 22, -45, -85, -111, -71, -124, 127, 97, 30, -49, -59, -47, 86, 61, -54, -12, 5, -58, -27, 8, 73};


    private final short mapleVersion;
    /**
     * Logger for this class.
     */
    private byte[] iv;
    private Cipher cipher;

    public MapleAESOFB(byte[] iv, short mapleVersion, boolean bIsSend) {
        // TwMS規律金鑰處理
        if (mapleVersion >= 176) {
            byte[] skeyBytes = HexTool.getByteArrayFromHexString(skeys[mapleVersion % 20]);
            for (int i = 0; i < skeyBytes.length; i = i + 4) {
                for (int n = 1; n <= 3; n++) {
                    skeyBytes[i + n] = 0;
                }
            }
            skey = new SecretKeySpec(skeyBytes, "AES");
        }

        try {
            cipher = Cipher.getInstance("AES");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            System.err.print("錯誤 " + e);
        }
        try {
            cipher.init(Cipher.ENCRYPT_MODE, skey);
        } catch (InvalidKeyException e) {
            System.err.print("啟動加密計算工具錯誤.請確實你是否使用了無限制加固型密碼系統的(.jar)文件." + e);
        }
        this.setIv(iv);

        if (bIsSend) {
            mapleVersion = (short) (0xFFFF - mapleVersion);
        }

        this.mapleVersion = (short) (((mapleVersion >> 8) & 0xFF) | ((mapleVersion << 8) & 0xFF00));
    }

    public MapleAESOFB(byte[] key, byte[] iv, short mapleVersion) {
        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        try {
            cipher = Cipher.getInstance("AES");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            System.err.print("错误 " + e);
        }
        try {
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        } catch (InvalidKeyException e) {
            System.err.print("启动加密计算工具错误.请确实你是否使用了无限制加固型密码系统的(.jar)文件." + e);
        }
        this.setIv(iv);
        this.mapleVersion = (short) (((mapleVersion >> 8) & 0xFF) | ((mapleVersion << 8) & 0xFF00));
    }

    /**
     * Gets the packet length from a header.
     *
     * @param packetHeader The header as an integer.
     * @return The length of the packet.
     */
    public static int getPacketLength(int packetHeader) {
        int ivBytes = ((packetHeader >>> 24) & 0xFF) | ((packetHeader >>> 8) & 0xFF00);
        int xorredSize = ((packetHeader >>> 8) & 0xFF) | ((packetHeader << 8) & 0xFF00);
        return (xorredSize ^ ivBytes) & 0xFFFF;
    }

    public static int getLongPacketLength(int hword, int lword) {
        int ivBytes = ((hword >>> 24) & 0xFF) | ((hword >>> 8) & 0xFF00);
        return lword ^ ivBytes;
    }

    /**
     * Gets the packet length from a header.
     *
     * @param packetHeader The header as a byte array.
     * @return The length of the packet.
     */
    public static int getPacketLength(byte[] packetHeader) {
        if (packetHeader.length < 4) {
            return -1;
        }
        return (((packetHeader[0] ^ packetHeader[2]) & 0xFF) | (((packetHeader[1] ^ packetHeader[3]) << 8) & 0xFF00));
    }

    /**
     * Gets a new IV from
     * <code>oldIv</code>
     *
     * @param oldIv The old IV to get a new IV from.
     * @return The new IV.
     */
    public static byte[] getNewIv(byte[] oldIv) {
        byte[] in = {(byte) 0xf2, 0x53, (byte) 0x50, (byte) 0xc6}; // magic
        for (int x = 0; x < 4; x++) {
            funnyShit(oldIv[x], in);
            //System.out.println(HexTool.toString(in));
        }
        return in;
    }

    /**
     * Does funny stuff.
     * <code>this.OldIV</code> must not equal
     * <code>in</code> Modifies
     * <code>in</code> and returns it for
     * convenience.
     *
     * @param inputByte The byte to apply the funny stuff to.
     * @param in        Something needed for all this to occur.
     */
    public static void funnyShit(byte inputByte, byte[] in) {
        byte elina = in[1];
        byte moritz = funnyBytes[elina & 0xFF];
        moritz -= inputByte;
        in[0] += moritz;
        moritz = in[2];
        moritz ^= funnyBytes[inputByte & 0xFF];
        elina -= moritz & 0xFF;
        in[1] = elina;
        elina = in[3];
        moritz = elina;
        elina -= in[0] & 0xFF;
        moritz = funnyBytes[moritz & 0xFF];
        moritz += inputByte;
        moritz ^= in[2];
        in[2] = moritz;
        elina += funnyBytes[inputByte & 0xFF] & 0xFF;
        in[3] = elina;

        int merry = (in[0]) & 0xFF;
        merry |= (in[1] << 8) & 0xFF00;
        merry |= (in[2] << 16) & 0xFF0000;
        merry |= (in[3] << 24) & 0xFF000000;
        int ret_value = merry >>> 0x1d;
        merry <<= 3;
        ret_value |= merry;

        in[0] = (byte) (ret_value & 0xFF);
        in[1] = (byte) ((ret_value >> 8) & 0xFF);
        in[2] = (byte) ((ret_value >> 16) & 0xFF);
        in[3] = (byte) ((ret_value >> 24) & 0xFF);
    }

    /**
     * Credits to @Diamondo25 / @mapleshark
     *
     * @param pValue = value
     * @param pIv    = old Iv
     */
    public static void Morph(byte pValue, byte[] pIv) {
        byte tableInput = funnyBytes[pValue];
        pIv[0] += (byte) (funnyBytes[pIv[1]] - pValue);
        pIv[1] -= (byte) (pIv[2] ^ tableInput);
        pIv[2] ^= (byte) (funnyBytes[pIv[3]] + pValue);
        pIv[3] -= (byte) (pIv[0] - tableInput);

        int Value = (int) (pIv[0]) & 0xFF | (pIv[1] << 8) & 0xFF00 | (pIv[2] << 16) & 0xFF0000 | (pIv[3] << 24) & 0xFF000000;
        Value = (Value >> 0x1D | Value << 0x03);

        pIv[0] = (byte) (Value & 0xFF);
        pIv[1] = (byte) ((Value >> 8) & 0xFF);
        pIv[2] = (byte) ((Value >> 16) & 0xFF);
        pIv[3] = (byte) ((Value >> 24) & 0xFF);
    }


    /**
     * For debugging/testing purposes only.
     *
     * @return The IV.
     */
    public byte[] getIv() {
        return this.iv;
    }

    /**
     * Sets the IV of this instance.
     *
     * @param iv The new IV.
     */
    private void setIv(byte[] iv) {
        this.iv = iv;
    }

    /**
     * Encrypts
     * <code>data</code> and generates a new IV.
     *
     * @param data The bytes to encrypt.
     * @return The encrypted bytes.
     */
    public byte[] crypt(byte[] data) {
        int remaining = data.length;
        int llength = 0x5B0;
        int start = 0;

        try {
            while (remaining > 0) {
                byte[] myIv = BitTools.multiplyBytes(this.iv, 4, 4);
                if (remaining < llength) {
                    llength = remaining;
                }
                for (int x = start; x < (start + llength); x++) {
                    if ((x - start) % myIv.length == 0) {
                        byte[] newIv = cipher.doFinal(myIv);
                        System.arraycopy(newIv, 0, myIv, 0, myIv.length);
                        // System.out.println("Iv is now " + HexTool.toString(this.iv));
                    }
                    data[x] ^= myIv[(x - start) % myIv.length];
                }
                start += llength;
                remaining -= llength;
                llength = 0x5B4;
            }
            updateIv();
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return data;
    }

    public byte[] crypt(byte[] data, boolean second) {
        if (!second) {
            return crypt(data);
        }
        final byte b = this.iv[0];
        int min;
        for (int length = data.length, i = 0; i < length; i += min) {
            min = Math.min((i == 0) ? 1456 : 1460, length - i);
            for (int j = 0; j < min; ++j) {
                final int n = i + j;
                data[n] += b;
            }
        }
        this.updateIv();
        return data;
    }

    public byte[] recvCrypt(byte[] data) {
        final byte b = this.iv[0];
        int min;
        for (int length = data.length, i = 0; i < length; i += min) {
            min = Math.min((i == 0) ? 1456 : 1460, length - i);
            for (int j = 0; j < min; ++j) {
                final int n = i + j;
                data[n] -= b;
            }
        }
        this.updateIv();
        return data;
    }

    /**
     * Generates a new IV.
     */
    private void updateIv() {
        this.iv = getNewIv(this.iv);
    }

    /**
     * Generates a packet header for a packet that is
     * <code>length</code>
     * long.
     *
     * @param length How long the packet that this header is for is.
     * @return The header.
     */
    public byte[] getPacketHeader(long length) {
        ByteBuffer buffer;
        long uSeqSnd = ((long)(this.iv[3] ^ mapleVersion) << 8) & 0xFF00 | (this.iv[2] ^ (mapleVersion >> 8)) & 0xFF;
        if (length >= 0xFF00) {
            buffer = ByteBuffer.allocate(8);
            buffer.order(ByteOrder.LITTLE_ENDIAN);
            buffer.putShort((short)uSeqSnd);
            buffer.putShort((short)(0xFF00 ^ uSeqSnd));
            buffer.putInt((int) (length ^ uSeqSnd));
        } else {
            buffer = ByteBuffer.allocate(4);
            buffer.order(ByteOrder.LITTLE_ENDIAN);
            buffer.putShort((short)uSeqSnd);
            buffer.putShort((short)(length ^ uSeqSnd));
        }
        return buffer.array();
    }

//    public static byte[] getNewIv(byte oldIv[]) {
//        byte[] newIv = {(byte) 0xf2, 0x53, (byte) 0x50, (byte) 0xc6};
//        for (int x = 0; x < 4; x++) {
//            Morph(oldIv[x], newIv);
//        }
//        return newIv;
//    }

    /**
     * Check the packet to make sure it has a header.
     *
     * @param packet The packet to check.
     * @return <code>True</code> if the packet has a correct header,
     * <code>false</code> otherwise.
     */
    public boolean checkPacket(byte[] packet) {
        return ((((packet[0] ^ iv[2]) & 0xFF) == ((mapleVersion >> 8) & 0xFF)) && (((packet[1] ^ iv[3]) & 0xFF) == (mapleVersion & 0xFF)));
    }

    /**
     * Check the header for validity.
     *
     * @param packetHeader The packet header to check.
     * @return <code>True</code> if the header is correct,
     * <code>false</code>
     * otherwise.
     */
    public boolean checkPacket(int packetHeader) {
        return checkPacket(new byte[]{(byte) ((packetHeader >> 24) & 0xFF), (byte) ((packetHeader >> 16) & 0xFF)});
    }

    /**
     * Returns the IV of this instance as a string.
     *
     * @return
     */
    @Override
    public String toString() {
        return "IV: " + HexTool.toString(this.iv);
    }

    /* 演算法 */
    private byte[] generateKeyFromVersion(short mapleVersion) throws NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        byte[] versionBytes = new byte[2];
        versionBytes[0] = (byte) ((mapleVersion >> 8) & 0xFF);
        versionBytes[1] = (byte) (mapleVersion & 0xFF);
        byte[] combined = new byte[versionBytes.length + salt.length];
        System.arraycopy(versionBytes, 0, combined, 0, versionBytes.length);
        System.arraycopy(salt, 0, combined, versionBytes.length, salt.length);
        return java.security.MessageDigest.getInstance("SHA-256").digest(combined);
    }
}
