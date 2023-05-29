package ru.mnk.vfs.serializers;

public class ByteArrayBuilder {
    private final byte[] bytes;
    private int currentOffset;

    public ByteArrayBuilder(int allocationLength) {
        this.bytes = new byte[allocationLength];
        this.currentOffset = 0;
    }

    public static ByteArrayBuilder builder(int allocationLength) {
        return new ByteArrayBuilder(allocationLength);
    }

    public ByteArrayBuilder addBytes(byte[] bytes) {
        System.arraycopy(bytes, 0, this.bytes, this.currentOffset, bytes.length);
        this.currentOffset = bytes.length;
        return this;
    }

    public byte[] build() {
        return this.bytes;
    }
}
