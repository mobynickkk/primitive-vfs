package ru.mnk.vfs.space;

import java.nio.charset.StandardCharsets;

public class FileDescriptor {
    private static final int NAME_ALLOC_LENGTH = new String(new char[100]).getBytes(StandardCharsets.UTF_8).length;
    private static final int EXTENSION_ALLOC_LENGTH = new String(new char[5]).getBytes(StandardCharsets.UTF_8).length;
    private static final int WHOLE_BYTE_ARRAY_LENGTH = NAME_ALLOC_LENGTH + EXTENSION_ALLOC_LENGTH + Integer.BYTES * 2;
    private static final char DOT = '.';

    public static int getNameAllocLength() {
        return NAME_ALLOC_LENGTH;
    }

    public static int getExtensionAllocLength() {
        return EXTENSION_ALLOC_LENGTH;
    }

    public static int getWholeByteArrayLength() {
        return WHOLE_BYTE_ARRAY_LENGTH;
    }

    private String name;
    private String extension;
    private long beginOffset;
    private long endOffset;

    public String getPath() {
        return name + DOT + extension;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.length() > 100) {
            throw new IllegalArgumentException();
        }
        this.name = name;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        if (extension.length() > 5) {
            throw new IllegalArgumentException();
        }
        this.extension = extension;
    }

    public long getBeginOffset() {
        return beginOffset;
    }

    public void setBeginOffset(long beginOffset) {
        this.beginOffset = beginOffset;
    }

    public long getEndOffset() {
        return endOffset;
    }

    public void setEndOffset(long endOffset) {
        this.endOffset = endOffset;
    }

    public long getLength() {
        return endOffset - beginOffset;
    }
}
