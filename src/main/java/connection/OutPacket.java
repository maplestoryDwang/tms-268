package connection;

import Config.constants.ServerConstants;
import Opcode.Headler.OutHeader;
import SwordieX.util.FileTime;
import SwordieX.util.Position;
import SwordieX.util.Rect;
import SwordieX.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Arrays;

public class OutPacket extends Packet {

    //    private static boolean dumped = false;
    private byte[] baos;
    private int baosPtr = 0;
    private boolean loopback = false;
    private final boolean encryptedByShanda = false;
    private short op;
    private static final Charset CHARSET = ServerConstants.MapleType.getByType(ServerConstants.MapleRegion).getCharset();
    private static final Logger log = LoggerFactory.getLogger(OutPacket.class);

    /**
     * Creates a new OutPacket with a given op. Immediately encodes the op.
     *
     * @param op The opcode of this OutPacket.
     */
    public OutPacket(short op) {
        super(new byte[]{});
        baos = new byte[16];
        encodeShort(op);
        this.op = op;
    }

    public final void encodeZero(int i) {
        for(int x = 0; x < i; ++x) {
            this.encodeByte((byte)0);
        }

    }

    /**
     * Creates a new OutPacket with a given op. Immediately encodes the op.
     *
     * @param op The opcode of this OutPacket.
     */
    public OutPacket(int op) {
        this((short) op);
    }

    /**
     * Creates a new OutPacket, and initializes the data as empty.
     */
    public OutPacket() {
        this(new byte[16]);
    }

    /**
     * Creates a new OutPacket with given data.
     *
     * @param data The data this packethas to be initialized with.
     */
    public OutPacket(byte[] data) {
        super(data);
        baos = data;
    }

    /**
     * Creates a new OutPacket with a given header. Immediately encodes the header's short value.
     *
     * @param header The header of this OutPacket.
     */
    public OutPacket(OutHeader header) {
        this(header.getValue());
    }

    /**
     * Returns the header of this OutPacket.
     *
     * @return the header of this OutPacket.
     */
    @Override
    public short getHeader() {
        return op;
    }

    /**
     * Encodes a single byte to this OutPacket.
     *
     * @param b The int to encode as a byte. Will be downcast, so be careful.
     */
    public void encodeByte(int b) {
        encodeByte((byte) b);
    }

    /**
     * Encodes a byte to this OutPacket.
     *
     * @param b The byte to encode.
     */
    public void encodeByte(byte b) {
        if (baosPtr >= baos.length) {
            byte[] newBaos = new byte[baos.length * 2];
            System.arraycopy(baos, 0, newBaos, 0, baos.length);
            baos = newBaos;
        }
        baos[baosPtr++] = b;
    }

    /**
     * Encodes a byte array to this OutPacket.
     * Named like this to prevent autocompletion of "by" to "byteArray" or similar names.
     *
     * @param bArr The byte array to encode.
     */
    public void encodeArr(byte[] bArr) {
        for (byte b : bArr) {
            encodeByte(b);
        }
    }

    /**
     * Encodes a byte array to this OutPacket.
     *
     * @param arr the byte array, in string format (may contain '|' and whitespace to seperate bytes)
     */
    public void encodeArr(String arr) {
        encodeArr(Util.getByteArrayByString(arr));
    }

    /**
     * Encodes a character to this OutPacket, UTF-8.
     *
     * @param c The character to encode
     * @return
     */
    public void encodeChar(char c) {
        encodeByte(c);
    }

    /**
     * Encodes a boolean to this OutPacket.
     *
     * @param b The boolean to encode (0/1)
     */
    public void encodeByte(boolean b) {
        encodeByte(b ? 1 : 0);
    }

    public void encodeBoolean(boolean b) {
        encodeByte(b ? 1 : 0);
    }

    /**
     * Encodes a short to this OutPacket, in little endian.
     *
     * @param s The short to encode.
     */
    public void encodeShort(short s) {
        encodeByte(s & 0xFF);
        encodeByte((s >>> 8) & 0xFF);
    }

    public void encodeShortBE(short s) {
        encodeByte((s >>> 8) & 0xFF);
        encodeByte(s & 0xFF);
    }

    public void encodeIntBE(int i) {
        encodeShort((i >>> 16) & 0xFFFF);
        encodeShort(i & 0xFFFF);
    }

    /**
     * Encodes an integer to this OutPacket, in little endian.
     *
     * @param i The integer to encode.
     */
    public void encodeInt(int i) {
        encodeShort(i & 0xFFFF);
        encodeShort((i >>> 16) & 0xFFFF);
    }

    /**
     * Encodes a long to this OutPacket, in little endian.
     *
     * @param l The long to encode.
     */
    public void encodeLong(long l) {
        encodeInt((int) (l & 0xFFFFFFFFL));
        encodeInt((int) ((l >>> 32L) & 0xFFFFFFFFL));
    }

    /**
     * Encodes a String to this OutPacket.
     * Structure: short(size) + char array of <code>s</code>.
     *
     * @param s The String to encode.
     */
    public void encodeString(String s) {
        if (s == null) {
            s = "";
        }
        if (s.length() > Short.MAX_VALUE) {
            log.error("Tried to encode a string that is too big.");
            return;
        }
        byte[] bs = s.getBytes(CHARSET);
        encodeShort((short) bs.length);
        encodeString(s, (short) bs.length);
    }


    /**
     * Writes a String as a character array to this OutPacket.
     * If <code>s.length()</code> is smaller than length, the open spots are filled in with zeros.
     *
     * @param s      The String to encode.
     * @param length The maximum length of the buffer.
     */
    public void encodeString(String s, short length) {
        if (s == null) {
            s = "";
        }
        byte[] bs = s.getBytes(CHARSET);
        if (bs.length > 0) {
            encodeArr(bs);
        }
        for (int i = bs.length; i < length; i++) {
            encodeByte((byte) 0);
        }
    }

    @Override
    public void setData(byte[] nD) {
        super.setData(nD);
        baos = nD;
    }

    @Override
    public byte[] getData() {
        // array might be bigger than what was written
        byte[] retArr = new byte[baosPtr];
        System.arraycopy(baos, 0, retArr, 0, baosPtr);
        return retArr;
    }

    @Override
    public Packet clone() {
        return new OutPacket(getData());
    }

    /**
     * Returns the length of the ByteArrayOutputStream.
     *
     * @return The length of baos.
     */
    @Override
    public int getLength() {
        return getData().length;
    }

    public boolean isLoopback() {
        return loopback;
    }

    public boolean isEncryptedByShanda() {
        return encryptedByShanda;
    }

    @Override
    public String toString() {
        return String.format("%s, %s/0x%s\t| %s", OutHeader.getOpcodeName(op), op, Integer.toHexString(op).toUpperCase()
                , Util.readableByteArray(Arrays.copyOfRange(getData(), 2, getData().length)));
    }

    public void encodeShort(int value) {
        encodeShort((short) value);
    }

    public void encodeString(String name, int length) {
        encodeString(name, (short) length);
    }

    public void encodeFT(FileTime fileTime) {
        if (fileTime == null) {
            encodeLong(0);
        } else {
            fileTime.encode(this);
        }
    }

    public void encodePosition(Position position) {
        if (position != null) {
            encodeShort(position.getX());
            encodeShort(position.getY());
        } else {
            encodeShort(0);
            encodeShort(0);
        }
    }

    public void encodeRectInt(Rect rect) {
        encodeInt(rect.getLeft());
        encodeInt(rect.getTop());
        encodeInt(rect.getRight());
        encodeInt(rect.getBottom());
    }

    public void encodePositionInt(Position position) {
        encodeInt(position.getX());
        encodeInt(position.getY());
    }

    public void encodeFT(long currentTime) {
        encodeFT(new FileTime(currentTime));
    }

    public void encodeTime(boolean dynamicTerm, int time) {
        encodeByte(dynamicTerm);
        encodeInt(time);
    }

    public void encodeTime(int time) {
        encodeByte(false);
        encodeInt(time);
    }

    public void release() {
//        baos.release();
    }

    public void encodeFT(LocalDateTime localDateTime) {
        encodeFT(FileTime.fromDate(localDateTime));
    }

    public void encodeZigZagVarints(int b) {
        b = (b << 1) ^ (b >> 31);
        encodeVarints(b);
    }

    public void encodeVarints(int b) {
        while (true) {
            if ((b & (~0x7F)) == 0) {
                encodeByte(b);
                break;
            } else {
                encodeByte((b & 0x7F) | 0x80);
                b >>= 7;
            }
        }
    }

    public void encodeReversedVarints(int b) {
        while (true) {
            if ((b & (~0x7F)) == 0) {
                encodeByte(b << 1);
                break;
            } else {
                encodeByte(((b & 0x7F) << 1) | 1);
                b >>= 7;
            }
        }
    }

    public void encode(Encodable encodable) {
        encodable.encode(this);
    }

    public void setLoopback(boolean loopback) {
        this.loopback = loopback;
    }
}
