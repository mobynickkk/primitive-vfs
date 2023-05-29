package ru.mnk.vfs.serializers;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class SerializationUtils {

    public SerializationUtils() {
        throw new UnsupportedOperationException();
    }

    public static byte[] serializeString(String s, int allocationLength) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(allocationLength);
        byteBuffer.put(s.getBytes(StandardCharsets.UTF_8));
        return byteBuffer.array();
    }

    public static byte[] serializeLong(long i) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(Long.BYTES);
        byteBuffer.putLong(i);
        return byteBuffer.array();
    }

    public static long deserializeLong(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(Long.BYTES);
        byteBuffer.put(bytes);
        byteBuffer.flip();
        return byteBuffer.getLong();
    }

    public static String deserializeString(ByteBuffer byteBuffer, int offset, int length) {
        byte[] bytes = new byte[length];
        byteBuffer.get(bytes, offset, length);
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
