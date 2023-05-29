package ru.mnk.vfs;

import lombok.AllArgsConstructor;

import ru.mnk.vfs.space.FileDescriptor;

@AllArgsConstructor
public class File {
    private FileDescriptor fileDescriptor;
    private TransferByteChannel data;

    public String getName() {
        return fileDescriptor.getPath();
    }

    public byte[] getData() {
        return data.getData();
    }
}
