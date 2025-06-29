/**
 * @author 橘子
 * @version 1.0.0
 * @Title
 * @ClassName ExtractJarFromExe.java
 * @Description TODO
 * @createTime 2025-06-28 21:26
 */

import org.springframework.boot.loader.jar.JarFile;

import java.io.*;
import java.nio.file.*;

public class ExtractJarFromExe {

        public static void main(String[] args) throws Exception {
            File rootJar = new File("twms_real.jar");
            searchJar(rootJar, "NetRun.class");
        }

        static void searchJar(File jarFile, String target) throws IOException {
            try (JarFile jar = new JarFile(jarFile)) {
                jar.stream().forEach(entry -> {
                    if (entry.getName().equals(target)) {
                        System.out.println("✅ 找到类: " + jarFile.getName() + " -> " + entry.getName());
                    } else if (entry.getName().endsWith(".jar")) {
                        try {
                            // 提取内部 jar 到临时文件再递归
                            InputStream in = jar.getInputStream(entry);
                            File temp = File.createTempFile("inner-", ".jar");
                            try (OutputStream out = new FileOutputStream(temp)) {
                                in.transferTo(out);
                            }
                            searchJar(temp, target);
                            temp.deleteOnExit();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }

}
