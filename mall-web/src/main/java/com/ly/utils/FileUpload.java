package com.ly.utils;


import com.ly.exception.FileUploadException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.ibatis.io.Resources;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class FileUpload {

    /**
     * 允许上传的文件后缀名
     */
    public static final List<String> ALLOWED_UPLOAD_TYPE = Arrays.asList("png", "jpg", "jpeg");
    /**
     * 允许上传的文件大小5m
     */
    public static final int ALLOWED_UPLOAD_SIZE = 5 * 1024 * 1024;
    /**
     * 允许上传的文件大小5g
     */
    public static final int APP_ALLOWED_UPLOAD_SIZE = 5 * 1024 * 1024 * 1024;

    /**
     * 文件为空时抛出的异常信息
     */
    public static final String FILE_EMPTY_EXCEPTION = "文件为空或文件不存在";

    /**
     * 文件上传的类型不匹配抛出的异常信息
     */
    public static final String FILE_EXTENSION_EXCEPTION = "上传的文件类型不匹配";

    /**
     * 上传的图片过大抛出的异常信息
     */
    public static final String IMGFILE_SIZE_EXCEPTION = "上传的文件大小超过允许的类型" + ALLOWED_UPLOAD_SIZE / 1024 / 1024 + "(MB)";
    /**
     * 上传的app过大抛出的异常信息
     */
    public static final String APP_FILE_SIZE_EXCEPTION = "上传的文件大小超过允许的类型" + APP_ALLOWED_UPLOAD_SIZE / 1024 / 1024 / 1024 + "(GB)";

    /**
     * 要上传文件的起始位置
     */
    public static final String FILE_START_LOCATION = "res/images/item";

    /**
     * 判断MultipartFile动态数组是否为空
     *
     * @param attachs
     * @return
     */
    public static boolean fileIsEmpty(MultipartFile... attachs) {
        if (attachs == null || attachs.length == 0) {

            return true;
        }
        return false;
    }

    /**
     * 为true表示文件大小不合法
     * 为false表示文件大小合法
     *
     * @param size
     * @return
     */
    public static boolean fileSizeVerify(long size, int alllowedUploadSize) {
        if (size > alllowedUploadSize) {
            return true;
        }
        return false;
    }

    /**
     * 为true表示文件大小不合法
     * 为false表示文件大小合法
     *
     * @param extension 后缀名
     * @return
     */
    public static boolean imgExtensionVerify(String extension, MultipartFile attach) {
        if (ALLOWED_UPLOAD_TYPE.contains(extension)) {
            try (InputStream inputStream = attach.getInputStream()) {
                BufferedImage read = ImageIO.read(inputStream);
                if (read == null) {
                    return false;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }

        return false;
    }

    /**
     * 获取文件的MD5
     *
     * @param attach
     * @return
     */
    public static String getFileMd5(MultipartFile attach) {
        String md5 = null;
        InputStream inputStream = null;
        try {
            inputStream = attach.getInputStream();
            md5 = DigestUtils.md5Hex(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return md5;
    }

    /**
     * 上传APP文件
     *
     * @param session
     * @param targetPath
     * @param attachs
     * @return
     * @throws FileUploadException
     */
    public static List<String> saveAppUpload(HttpSession session, String targetPath, MultipartFile... attachs) throws FileUploadException {
        List<String> list = new ArrayList<>();

        if (FileUpload.fileIsEmpty(attachs)) {
            throw new FileUploadException(FILE_EMPTY_EXCEPTION);
        }

        for (int i = 0; i < attachs.length; i++) {

            MultipartFile attach = attachs[i];
            if (attach.isEmpty()) {
                throw new FileUploadException(FILE_EMPTY_EXCEPTION);
            }
            //获取目标上传的目录路径
            String path = session.getServletContext().getRealPath(FILE_START_LOCATION + File.separator + targetPath);
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            //获取文件大小(单位:B)
            long size = attach.getSize();
            //获取文件名
            String originalFilename = attach.getOriginalFilename();
            //获取后缀名

            String extension = Objects.requireNonNull(FilenameUtils.getExtension(originalFilename)).toLowerCase();

            if (fileSizeVerify(size, APP_ALLOWED_UPLOAD_SIZE)) {
                throw new FileUploadException(APP_FILE_SIZE_EXCEPTION);
            }


            if (!"apk".equalsIgnoreCase(extension)) {
                throw new FileUploadException(FILE_EXTENSION_EXCEPTION);
            }


            String newFileName = getFileMd5(attach) + "." + extension;

            try {
                attach.transferTo(new File(file, newFileName));
            } catch (IOException e) {
                throw new FileUploadException(e.getMessage());
            }

            list.add(newFileName);
        }
        return list;
    }

    /**
     * 上传图片
     *
     * @param session
     * @param targetPath
     * @param attachs
     * @return
     * @throws FileUploadException
     */
    public static List<String> saveImgUpload(HttpSession session, String targetPath, MultipartFile... attachs) throws FileUploadException {
        List<String> list = new ArrayList<>();
        if (FileUpload.fileIsEmpty(attachs)) {
            throw new FileUploadException(FILE_EMPTY_EXCEPTION);
        }

        for (int i = 0; i < attachs.length; i++) {
            MultipartFile attach = attachs[i];
            //判断文件是否为空
            if (attach == null || attach.isEmpty()) {
                throw new FileUploadException(FILE_EMPTY_EXCEPTION);
            }
            //获取目标上传的目录路径
            String path = session.getServletContext().getRealPath(FILE_START_LOCATION + File.separator + targetPath);
            File file = new File(path);


            if (!file.exists()) {
                file.mkdirs();
            }
            //获取文件大小(单位:B)
            long size = attach.getSize();
            //获取文件名
            String originalFilename = attach.getOriginalFilename();
            //获取后缀名
            String extension = Objects.requireNonNull(FilenameUtils.getExtension(originalFilename)).toLowerCase();

            if (fileSizeVerify(size, ALLOWED_UPLOAD_SIZE)) {
                throw new FileUploadException(IMGFILE_SIZE_EXCEPTION);
            }

            if (!imgExtensionVerify(extension, attach)) {
                throw new FileUploadException(FILE_EXTENSION_EXCEPTION);
            }


            //生成UUID
            String uuid = String.valueOf(UUID.randomUUID()).replace("-", "").toUpperCase(Locale.ROOT);
            //文件的新名称

            String newFileName = getFileMd5(attach) + "." + extension;

            try {
                attach.transferTo(new File(file, newFileName));
            } catch (IOException e) {
                throw new FileUploadException(e.getMessage());
            }

            list.add(newFileName);
        }
        return list;
    }


}
