package com.ly.exception;

/**
 * @author YiMeng
 * @DateTime: 2022/7/22 18:37
 * @Description: TODO
 */
public class FileUploadException extends RuntimeException {

    private static final String DEFAULTMESSAGE = "文件上传发生错误";

    public FileUploadException() {
        super(FileUploadException.DEFAULTMESSAGE);
    }

    public FileUploadException(String message) {
        super(message);
    }

    public FileUploadException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileUploadException(Throwable cause) {
        super(cause);
    }

    protected FileUploadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
