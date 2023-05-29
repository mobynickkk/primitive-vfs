package ru.mnk.vfs.serializers;

import ru.mnk.vfs.space.FileDescriptor;

import java.nio.ByteBuffer;

import static ru.mnk.vfs.serializers.SerializationUtils.deserializeString;

public class FileDescriptorDeserializer {

    public FileDescriptor deserialize(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        int offset = 0;
        FileDescriptor fileDescriptor = new FileDescriptor();
        fileDescriptor.setName(deserializeString(byteBuffer, offset, FileDescriptor.getNameAllocLength()));
        offset += FileDescriptor.getNameAllocLength();
        fileDescriptor.setExtension(deserializeString(byteBuffer, offset, FileDescriptor.getExtensionAllocLength()));
        offset += FileDescriptor.getExtensionAllocLength();
        fileDescriptor.setBeginOffset(byteBuffer.getLong(offset));
        offset += Long.BYTES;
        fileDescriptor.setEndOffset(byteBuffer.getLong(offset));
        return fileDescriptor;
    }


}
