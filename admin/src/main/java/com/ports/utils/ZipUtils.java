package com.ports.utils;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipUtils {

    public static void unzip(File zipFile, File destPath) throws IOException {
        if (!destPath.exists()) {
            destPath.mkdirs();
        }
        ZipInputStream in = null;
        try {
            in = getZipInputStream(zipFile);
            ZipEntry entry;
            while ((entry = in.getNextEntry()) != null) {
                File entryFile = new File(destPath, entry.getName());
                //Files.writeFile(entryFile, in);
                in.closeEntry();
            }
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }
    }

    public static void zip(File srcPath, File zipFile) throws IOException {
        ZipOutputStream out = null;
        try {
            out = getZipOutputStream(zipFile);
            compressFile(srcPath, zipFile, srcPath, out);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (Exception e) {
            }
        }
    }

    public static ZipInputStream getZipInputStream(File zipFile) throws IOException {
        InputStream in = new BufferedInputStream(new FileInputStream(zipFile));
        return new ZipInputStream(in);
    }

    public static ZipOutputStream getZipOutputStream(File zipFile) throws IOException {
        OutputStream out = new FileOutputStream(zipFile);
        return new ZipOutputStream(out);
    }

    private static void compressFile(File srcPath, File zipFile, File file, ZipOutputStream out) throws IOException {
        if (file == null || !file.exists()) {
            return;
        }
        if (file.isFile() && !file.equals(zipFile)) {
            byte buffer[] = new byte[8 * 1024];
            ZipEntry entry = new ZipEntry(getName(file, srcPath.getPath()));
            entry.setTime(file.lastModified());
            out.putNextEntry(entry);
            FileInputStream in = null;
            try {
                in = new FileInputStream(file);
                int data;
                while ((data = in.read(buffer, 0, buffer.length)) > 0) {
                    out.write(buffer, 0, data);
                }
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException e) {
                }
            }
        } else if (file.isDirectory()) {
            File files[] = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                compressFile(srcPath, zipFile, files[i], out);
            }
        }
    }

    private static String getName(File file, String prefix) {
        String path = file.getPath();
        if (path.startsWith(prefix)) {
            path = path.substring(prefix.length());
            if (path.startsWith("/")) {
                path = path.substring(1);
            }
            if (path.length() == 0) {
                path = file.getName();
            }
        } else {
            path = file.getName();
        }
        return path;
    }

}