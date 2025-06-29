//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package connection.crypto;

import SwordieX.util.Util;

public class MapleCrypto {
    private final AES cipher = new AES();
    private static short gVersion;
    private static short sVersion;
    private static short rVersion;
    public static final int[] SHUFFLE_BYTES = new int[]{236, 63, 119, 164, 69, 208, 113, 191, 183, 152, 32, 252, 75, 233, 179, 225, 92, 34, 247, 12, 68, 27, 129, 189, 99, 141, 212, 195, 242, 16, 25, 224, 251, 161, 110, 102, 234, 174, 214, 206, 6, 24, 78, 235, 120, 149, 219, 186, 182, 66, 122, 42, 131, 11, 84, 103, 109, 232, 101, 231, 47, 7, 243, 170, 39, 123, 133, 176, 38, 253, 139, 169, 250, 190, 168, 215, 203, 204, 146, 218, 249, 147, 96, 45, 221, 210, 162, 155, 57, 95, 130, 33, 76, 105, 248, 49, 135, 238, 142, 173, 140, 106, 188, 181, 107, 89, 19, 241, 4, 0, 246, 90, 53, 121, 72, 143, 21, 205, 151, 87, 18, 62, 55, 255, 157, 79, 81, 245, 163, 112, 187, 20, 117, 194, 184, 114, 192, 237, 125, 104, 201, 46, 13, 98, 70, 23, 17, 77, 108, 196, 126, 83, 193, 37, 199, 154, 28, 136, 88, 44, 137, 220, 2, 100, 64, 1, 93, 56, 165, 226, 175, 85, 213, 239, 26, 124, 167, 91, 166, 111, 134, 159, 115, 230, 10, 222, 43, 153, 74, 71, 156, 223, 9, 118, 158, 48, 14, 228, 178, 148, 160, 59, 52, 29, 40, 15, 54, 227, 35, 180, 3, 216, 144, 200, 60, 254, 94, 50, 36, 80, 31, 58, 67, 138, 150, 65, 116, 172, 82, 51, 240, 217, 41, 128, 177, 22, 211, 171, 145, 185, 132, 127, 97, 30, 207, 197, 209, 86, 61, 202, 244, 5, 198, 229, 8, 73};
    private static byte[] keyBytes = new byte[0];

    public MapleCrypto() {
        this.cipher.setKey(keyBytes);
    }

    public static void initialize(short v) {
        String[] skeys = new String[]{"2923BE84E16CD6AE529049F1F1BBE9EBB3A6DB3C870C3E99245E0D1C06B747DE", "B3124DC843BB8BA61F035A7D0938251F5DD4CBFC96F5453B130D890A1CDBAE32", "888138616B681262F954D0E7711748780D92291D86299972DB741CFA4F37B8B5", "209A50EE407836FD124932F69E7D49DCAD4F14F2444066D06BC430B7323BA122", "F622919DE18B1FDAB0CA9902B9729D492C807EC599D5E980B2EAC9CC53BF67D6", "BF14D67E2DDC8E6683EF574961FF698F61CDD11E9D9C167272E61DF0844F4A77", "02D7E8392C53CBC9121E33749E0CF4D5D49FD4A4597E35CF3222F4CCCFD3902D", "48D38F75E6D91D2AE5C0F72B788187440E5F5000D4618DBE7B0515073B33821F", "187092DA6454CEB1853E6915F8466A0496730ED9162F6768D4F74A4AD0576876", "5B628A8A8F275CF7E5874A3B329B614084C6C3B1A7304A10EE756F032F9E6AEF", "762DD0C2C9CD68D4496A792508614014B13B6AA51128C18CD6A90B87978C2FF1", "10509BC8814329288AF6E99E47A18148316CCDA49EDE81A38C9810FF9A43CDCF", "5E4EE1309CFED9719FE2A5E20C9BB44765382A4689A982797A7678C263B126DF", "DA296D3E62E0961234BF39A63F895EF16D0EE36C28A11E201DCBC2033F410784", "0F1405651B2861C9C5E72C8E463608DCF3A88DFEBEF2EB71FFA0D03B75068C7E", "8778734DD0BE82BEDBC246412B8CFA307F70F0A754863295AA5B68130BE6FCF5", "CABE7D9F898A411BFDB84F68F6727B1499CDD30DF0443AB4A66653330BCBA110", "5E4CEC034C73E605B4310EAAADCFD5B0CA27FFD89D144DF4792759427C9CC1F8", "CD8C87202364B8A687954CB05A8D4E2D99E73DB160DEB180AD0841E96741A5D5", "9FE4189F15420026FE4CD12104932FB38F735340438AAF7ECA6FD5CFD3A195CE"};
        if (v >= 176) {
            keyBytes = Util.getByteArrayByString(skeys[v % 20]);

            for(int i = 0; i < keyBytes.length; i += 4) {
                for(int n = 1; n <= 3; ++n) {
                    keyBytes[i + n] = 0;
                }
            }
        } else {
            keyBytes = new byte[]{90, 0, 0, 0, 42, 0, 0, 0, -95, 0, 0, 0, -76, 0, 0, 0, 50, 0, 0, 0, 77, 0, 0, 0, 86, 0, 0, 0, -56, 0, 0, 0};
        }

        gVersion = v;
        sVersion = (short)('\uffff' - v >>> 8 & 255 | '\uffff' - v << 8 & '\uff00');
        rVersion = (short)(v >>> 8 & 255 | v << 8 & '\uff00');
    }

    public static void encInit(int seqSnd, byte[] delta, boolean decrypt) {
        for(int i = 0; i < delta.length; ++i) {
            if (decrypt) {
                delta[i] -= (byte)seqSnd;
            } else {
                delta[i] += (byte)seqSnd;
            }
        }

    }

    public byte[] crypt(byte[] delta, byte[] gamma) {
        int a = delta.length;
        int b = 1456;

        for(int c = 0; a > 0; b = 1460) {
            byte[] d = BitTools.multiplyBytes(gamma, 4, 4);
            if (a < b) {
                b = a;
            }

            for(int e = c; e < c + b; ++e) {
                if ((e - c) % d.length == 0) {
                    try {
                        this.cipher.encrypt(d);
                    } catch (Exception var9) {
                        Exception ex = var9;
                        ex.printStackTrace();
                    }
                }

                delta[e] ^= d[(e - c) % d.length];
            }

            c += b;
            a -= b;
        }

        return delta;
    }

    public static byte[] getHeader(int delta, byte[] gamma) {
        if (delta >= 65280) {
            return getHeaderNew(delta, gamma);
        } else {
            int a = (gamma[3] ^ sVersion) & 255;
            a |= (gamma[2] << 8 ^ sVersion) & '\uff00';
            int b = delta << 8 & '\uff00' | delta >>> 8;
            int c = a ^ b;
            byte[] ret = new byte[]{(byte)(a >>> 8 & 255), (byte)(a & 255), (byte)(c >>> 8 & 255), (byte)(c & 255)};
            return ret;
        }
    }

    public static byte[] getHeaderNew(int delta, byte[] gamma) {
        int iv = (gamma[3] & 255 | gamma[2] << 8 & '\uff00') ^ sVersion;
        byte[] ret = new byte[8];
        ret[0] = (byte)(iv >>> 8 & 255);
        ret[1] = (byte)(iv & 255);
        ret[2] = ret[0];
        ret[3] = (byte)(ret[1] ^ 255);
        ret[4] = (byte)(delta & 255 ^ ret[0]);
        ret[5] = (byte)(delta >>> 8 & 255 ^ ret[1]);
        ret[6] = (byte)(delta >>> 16 & 255);
        ret[7] = (byte)(delta >>> 24 & 255);
        return ret;
    }

    public static int getLength(int delta) {
        int a = delta >>> 16 ^ delta & '\uffff';
        a = a << 8 & '\uff00' | a >>> 8 & 255;
        return a;
    }

    public static boolean checkPacket(byte[] delta, byte[] gamma) {
        return ((delta[0] ^ gamma[2]) & 255) == (rVersion >>> 8 & 255) && ((delta[1] ^ gamma[3]) & 255) == (rVersion & 255);
    }

    public static boolean checkPacket(int delta, byte[] gamma) {
        byte[] a = new byte[]{(byte)(delta >>> 24 & 255), (byte)(delta >>> 16 & 255)};
        return checkPacket(a, gamma);
    }

    public static byte[] getNewIv(byte[] delta) {
        byte[] ret = delta;
        int[] nIv = new int[]{242, 83, 80, 198};

        int i;
        for(i = 0; i < 4; ++i) {
            int a = ret[i] & 255;
            int b = SHUFFLE_BYTES[a];
            nIv[0] += SHUFFLE_BYTES[nIv[1]] - a;
            nIv[1] -= nIv[2] ^ b;
            nIv[2] ^= SHUFFLE_BYTES[nIv[3]] + a;
            nIv[3] -= nIv[0] - b;
            int c = nIv[0] & 255;
            c |= nIv[1] << 8 & '\uff00';
            c |= nIv[2] << 16 & 16711680;
            c |= nIv[3] << 24 & -16777216;
            int d = c << 3 | c >>> 29;
            nIv[0] = d & 255;
            nIv[1] = d >>> 8 & 255;
            nIv[2] = d >>> 16 & 255;
            nIv[3] = d >>> 24 & 255;
        }

        for(i = 0; i < 4; ++i) {
            ret[i] = (byte)nIv[i];
        }

        return ret;
    }
}
