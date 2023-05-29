package ru.mnk.vfs;

import ru.mnk.vfs.serializers.FileDescriptorDeserializer;
import ru.mnk.vfs.space.FileDescriptor;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static ru.mnk.vfs.serializers.SerializationUtils.deserializeLong;

public class FileSystem {
    private final FileDescriptorDeserializer fileDescriptorDeserializer = new FileDescriptorDeserializer();
    private final Map<String, FileDescriptor> fileDescriptors = new HashMap<>();
    private final FileInputStream inputStream;
    private long fileDescriptorsCount;
    private int metaspaceOffset;

    public FileSystem(String file) {
        try (FileInputStream inputStream = new FileInputStream(file)) {
            this.inputStream = inputStream;
            fileDescriptorsCount = getFileDescriptorsCount(inputStream);
            int currentOffset = Long.BYTES;
            for (int i = 0; i < fileDescriptorsCount; i++) {
                FileDescriptor fileDescriptor = getFileDescriptorFromInputStream(inputStream, currentOffset);
                fileDescriptors.put(fileDescriptor.getPath(), fileDescriptor);
                currentOffset += FileDescriptor.getWholeByteArrayLength();
            }
            metaspaceOffset = currentOffset;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public File readFile(String path) throws IOException {
        TransferByteChannel byteChannel = new TransferByteChannel();
        FileDescriptor descriptor = fileDescriptors.get(path);
        inputStream.getChannel().transferTo(descriptor.getBeginOffset(), descriptor.getLength(), byteChannel);
        return new File(descriptor, byteChannel);
    }

    private long getFileDescriptorsCount(FileInputStream inputStream) throws IOException {
        byte[] bytes = new byte[Long.BYTES];
        inputStream.read(bytes);
        return deserializeLong(bytes);
    }

    private FileDescriptor getFileDescriptorFromInputStream(FileInputStream inputStream, int offset)
            throws IOException {
        byte[] bytes = new byte[FileDescriptor.getWholeByteArrayLength()];
        inputStream.read(bytes, offset, FileDescriptor.getWholeByteArrayLength());
        return fileDescriptorDeserializer.deserialize(bytes);
    }
}
