package com.annis.utils;


import java.io.File;

public class FileUtils {

    static FileUtils utils;

    public static void main(String[] args) {
        utils = new FileUtils();

        String oldName = "【更多IT资源 www.cong0sousuo.top】";
        String newName = "";
        utils.changeFileName(new File("/Volumes/MAC_DATA/移动端架构师"), true, oldName, newName);
    }

    public void changeFileName(File file, boolean changeDirectory, String oldChar, String newChar) {
        if (file == null) {
            return;
        }
        if (file.isFile()) {
            String name = file.getName();
            String newName = name.replace(oldChar, newChar);
            file.renameTo(new File(file.getParentFile(), newName));
            return;
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files == null || files.length == 0) {
                return;
            }
            for (File item : files) {
                changeFileName(item, changeDirectory, oldChar, newChar);
            }
        }
    }
}
