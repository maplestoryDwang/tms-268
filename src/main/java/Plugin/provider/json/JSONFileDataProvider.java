//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Plugin.provider.json;

import Plugin.provider.MapleData;
import Plugin.provider.MapleDataDirectoryEntry;
import Plugin.provider.MapleDataEntity;
import Plugin.provider.MapleDataFileEntry;
import Plugin.provider.MapleDataProvider;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JSONFileDataProvider implements MapleDataProvider {
    private static final Logger log = LoggerFactory.getLogger(JSONFileDataProvider.class);
    private final File root;
    private final MapleDataDirectoryEntry rootForNavigation;
    private List<JSONFileDataProvider> subJsonFiles;
    private String subStr;

    public JSONFileDataProvider(File fileIn) {
        this(fileIn, "");
    }

    public JSONFileDataProvider(File fileIn, String subStr) {
        this.root = fileIn;
        this.rootForNavigation = new MapleDataDirectoryEntry(fileIn.getName(), (MapleDataEntity)null);
        this.subStr = subStr;
        this.fillMapleDataEntitys(this.root, this.rootForNavigation);
        this.subJsonFiles = new ArrayList();
        if (subStr.isEmpty()) {
            File[] var3 = fileIn.getParentFile().listFiles();
            int var4 = var3.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                File file = var3[var5];
                if (file.isDirectory() && !file.getPath().equalsIgnoreCase(fileIn.getPath()) && file.getName().replaceAll("\\d+", "").toLowerCase().equals(fileIn.getName().toLowerCase())) {
                    String sub = file.getName();
                    sub = sub.substring(0, sub.lastIndexOf(".")).replace(fileIn.getName().substring(0, fileIn.getName().lastIndexOf(".")), "");
                    JSONFileDataProvider data = new JSONFileDataProvider(file, sub);
                    this.subJsonFiles.add(data);
                    this.rootForNavigation.addAll(data.getRoot());
                }
            }
        }

    }

    private void fillMapleDataEntitys(File lroot, MapleDataDirectoryEntry wzdir) {
        File[] var3 = lroot.listFiles();
        int var4 = var3.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            File file = var3[var5];
            String fileName = file.getName();
            if (file.isDirectory() && !fileName.endsWith(".img")) {
                MapleDataDirectoryEntry newDir = new MapleDataDirectoryEntry(fileName, wzdir);
                wzdir.addDirectory(newDir);
                this.fillMapleDataEntitys(file, newDir);
            } else if (fileName.endsWith(".json")) {
                wzdir.addFile(new MapleDataFileEntry(fileName.substring(0, fileName.length() - 5), wzdir, this.subStr));
            }
        }

    }

    public MapleData getData(String path) {
        File dataFile = new File(this.root, path + ".json");
        File imageDataDir = new File(this.root.getName().replaceAll("(\\.[Ww][Zz])+$", ""), path);
        List<String> segments = new LinkedList(Arrays.asList(path.split("/")));
        Iterator<String> it = segments.iterator();
        StringBuilder imgPath = new StringBuilder();

        while(it.hasNext()) {
            if (imgPath.length() > 0) {
                imgPath.append("/");
            }

            imgPath.append((String)it.next());
            it.remove();
            dataFile = new File(this.root, String.valueOf(imgPath) + ".json");
            if (dataFile.exists()) {
                imageDataDir = new File(this.root.getName().replaceAll("(\\.[Ww][Zz])+$", ""), imgPath.toString());
                break;
            }
        }

        StringBuilder childPath = new StringBuilder();

        String segment;
        for(Iterator var8 = segments.iterator(); var8.hasNext(); childPath.append(segment)) {
            segment = (String)var8.next();
            if (childPath.length() > 0) {
                childPath.append("/");
            }
        }

        MapleData domMapleData = null;
        if (dataFile.exists()) {
            FileInputStream fis;
            try {
                fis = new FileInputStream(dataFile);
            } catch (FileNotFoundException var19) {
                FileNotFoundException e = var19;
                log.error("Datafile " + path + " does not exist in " + this.root.getAbsolutePath(), e);
                throw new RuntimeException("Datafile " + path + " does not exist in " + this.root.getAbsolutePath());
            }

            try {
                MapleData imgNode = new JsonDomMapleData(fis, imageDataDir.getParentFile());
                domMapleData = (MapleData)imgNode.getChildren().get(0);
            } finally {
                try {
                    fis.close();
                } catch (IOException var17) {
                    IOException e = var17;
                    throw new RuntimeException(e);
                }
            }
        }

        return domMapleData;
    }

    public MapleDataDirectoryEntry getRoot() {
        return this.rootForNavigation;
    }
}
