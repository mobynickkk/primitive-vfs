package ru.mnk.vfs;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;

public class TransferByteChannel implements ByteChannel {
    private byte[] data;

    @Override
    public int read(ByteBuffer dst) throws IOException {
        dst.put(data);
        return 0;
    }

    @Override
    public int write(ByteBuffer src) throws IOException {
        data = src.array();
        return 0;
    }

    public byte[] getData() {
        return data;
    }

    @Override
    public boolean isOpen() {
        return false;
    }

    @Override
    public void close() throws IOException {}
}
