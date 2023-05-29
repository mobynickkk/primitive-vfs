package ru.mnk.vfs.serializers;

import ru.mnk.vfs.space.FileDescriptor;

import static ru.mnk.vfs.serializers.SerializationUtils.*;

public class FileDescriptorSerializer {

    public byte[] serialize(FileDescriptor fileDescriptor) {
        return ByteArrayBuilder.builder(FileDescriptor.getWholeByteArrayLength())
                .addBytes(serializeString(fileDescriptor.getName(), FileDescriptor.getNameAllocLength()))
                .addBytes(serializeString(fileDescriptor.getExtension(), FileDescriptor.getExtensionAllocLength()))
                .addBytes(serializeLong(fileDescriptor.getBeginOffset()))
                .addBytes(serializeLong(fileDescriptor.getEndOffset()))
                .build();
    }
}
