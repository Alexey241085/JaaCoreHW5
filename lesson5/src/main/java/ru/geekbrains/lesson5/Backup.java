package ru.geekbrains.lesson5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Backup {

    public static void main(String[] args) {
        String sourceDir = "./";
        String backupDir = "./backup2";

        try {
            createBackup(sourceDir, backupDir);
            System.out.println("backup2 успешно создана");
        } catch (IOException e) {
            System.out.println("Ошибка создания backup2: " + e.getMessage());
        }
    }

    public static void createBackup(String sourceDir, String backupDir) throws IOException {
        File sourceDirectory = new File(sourceDir);
        File backupDirectory = new File(backupDir);

        if (!sourceDirectory.isDirectory()) {
            throw new IllegalArgumentException("Не является директорией");
        }

        if (!backupDirectory.exists()) {
            backupDirectory.mkdirs();
        }

        File[] files = sourceDirectory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    File backupFile = new File(backupDir + "/" + file.getName());
                    copyFile(file, backupFile);
                }
            }
        }
    }

    public static void copyFile(File sourceFile, File destinationFile) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(sourceFile);
             FileOutputStream outputStream = new FileOutputStream(destinationFile)) {
            byte[] buffer = new byte[10000];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
        }
    }
}
