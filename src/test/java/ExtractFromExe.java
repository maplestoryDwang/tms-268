import org.springframework.boot.loader.archive.Archive;
import org.springframework.boot.loader.archive.JarFileArchive;
import org.springframework.boot.loader.jar.JarFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.jar.JarEntry;

/**
 * @author 橘子
 * @version 1.0.0
 * @Title
 * @ClassName ExtractFromExe.java
 * @Description TODO
 * @createTime 2024-06-28 18:26
 */


public class ExtractFromExe {
    public static void main(String[] args) throws Exception {
        File exe = new File("E:/oldmxd/tms268/268/MS/twms.exe");
        Archive archive = new JarFileArchive(exe);
        // 使用反射获取内部的 java.util.jar.JarFile 对象
        Field jarFileField = JarFileArchive.class.getDeclaredField("jarFile");
        jarFileField.setAccessible(true);
        JarFile jarFile = (JarFile) jarFileField.get(archive);

        Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            if (entry.getName().endsWith("Net/NetRun.class")) {
                System.out.println("✅ 找到类: " + entry.getName());
                try (InputStream in = jarFile.getInputStream(entry);
                     FileOutputStream out = new FileOutputStream("NetRun.class")) {
                    in.transferTo(out);
                }
                System.out.println("🎉 提取成功，输出到 NetRun.class");
                return;
            }
        }

        System.out.println("❌ 未找到 Net/NetRun.class");
    }
}