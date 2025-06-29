//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Plugin.provider;

import Config.configs.Config;
import Database.DatabaseLoader;
import Plugin.provider.json.JSONFileDataProvider;
import Plugin.provider.nx.NXFileDataProvider;
import Plugin.provider.wz.WzFileDataProvider;
import Plugin.provider.wz.WzFileMapleData;
import Plugin.provider.wz.WzIMGFile;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.data.BufferedRandomAccessFile;
import tools.data.MaplePacketReader;
import tools.data.RandomAccessByteStream;

public final class MapleDataProviderFactory {
    private static final Logger log = LoggerFactory.getLogger(MapleDataProviderFactory.class);
    public static String WZPATH = Config.getProperty("wzpath", "Data");
    private static final Map<File, MapleDataProvider> CACHE = new HashMap();
    public static String HOTFIX_DATA_PATH = Config.getProperty("HotfixDataPath", "Hotfix/Data.wz");
    private static Map<String, MapleData> HotfixDataMap = null;
    private static String HotfixCheck = null;
    private static final Lock hotfixLoadLock = new ReentrantLock(true);

    public MapleDataProviderFactory() {
    }

    public static void init() {
        File in = new File(WZPATH);
        if (!in.exists()) {
            System.err.print("請輸入Wz所在的位置:");
            WZPATH = DatabaseLoader.scanner.next().replace("\\", "\\\\");
            Config.setProperty("wzpath", WZPATH);
            init();
        }

    }

    private static MapleDataProvider getWZ(String name) {
        File in = new File(WZPATH, name);
        if (!in.exists()) {
            in = new File(WZPATH + File.separator + name + ".wz");
            if (!in.exists()) {
                in = new File(WZPATH + File.separator + name + ".nx");
                if (!in.exists()) {
                    throw new RuntimeException("檔案不存在" + in.getPath());
                }
            }
        }

        if (CACHE.containsKey(in)) {
            return (MapleDataProvider)CACHE.get(in);
        } else {
            File checkFile;
            if (!in.getName().endsWith(".wz") && in.isDirectory() && name.matches("^([A-Za-z]+)$")) {
                String var10002 = in.getPath();
                checkFile = new File(var10002 + File.separator + in.getName() + ".wz");
                if (!checkFile.exists()) {
                    var10002 = in.getPath();
                    checkFile = new File(var10002 + File.separator + in.getName() + ".nx");
                    if (!checkFile.exists()) {
                        throw new RuntimeException("檔案不存在" + checkFile.getPath());
                    }
                }
            } else {
                checkFile = in;
            }

            Object fileData;
            if (checkFile.isDirectory()) {
                fileData = new JSONFileDataProvider(in);
            } else {
                try {
                    BufferedRandomAccessFile raf = new BufferedRandomAccessFile(checkFile, "r");

                    try {
                        MaplePacketReader lea = new MaplePacketReader(new RandomAccessByteStream(raf));
                        String magic = lea.readAsciiString(4);
                        raf.close();
                        if (magic.equalsIgnoreCase("PKG1")) {
                            fileData = new WzFileDataProvider(in);
                        } else {
                            if (!magic.equalsIgnoreCase("PKG4")) {
                                throw new RuntimeException("不支援這個" + magic + "格式檔案" + checkFile.getPath());
                            }

                            fileData = new NXFileDataProvider(in);
                        }
                    } catch (Throwable var8) {
                        try {
                            raf.close();
                        } catch (Throwable var7) {
                            var8.addSuppressed(var7);
                        }

                        throw var8;
                    }

                    raf.close();
                } catch (Exception var9) {
                    Exception e = var9;
                    throw new RuntimeException("讀取檔案時出錯", e);
                }
            }

            CACHE.put(in, (MapleDataProvider) fileData);
            return (MapleDataProvider)fileData;
        }
    }

    public static MapleDataProvider getEffect() {
        return getWZ("Effect");
    }

    public static MapleDataProvider getItem() {
        return getWZ("Item");
    }

    public static MapleDataProvider getCharacter() {
        return getWZ("Character");
    }

    public static MapleDataProvider getSkill() {
        return getWZ("Skill");
    }

    public static MapleDataProvider getString() {
        return getWZ("String");
    }

    public static MapleDataProvider getEtc() {
        return getWZ("Etc");
    }

    public static MapleDataProvider getMob() {
        return getWZ("Mob");
    }

    public static MapleDataProvider getNpc() {
        return getWZ("Npc");
    }

    public static MapleDataProvider getMap() {
        return getWZ("Map");
    }

    public static MapleDataProvider getReactor() {
        return getWZ("Reactor");
    }

    public static MapleDataProvider getQuest() {
        return getWZ("Quest");
    }

    public static void loadHotfixData() {
        HotfixDataMap = new LinkedHashMap();
        File file = new File(HOTFIX_DATA_PATH);
        if (!file.exists()) {
            log.info("Hotfix檔案不存在, 忽略Hotfix資料{}", file.getPath());
        } else {
            try {
                FileInputStream in = new FileInputStream(file);

                try {
                    MessageDigest digest = MessageDigest.getInstance("SHA-1");
                    byte[] buffer = new byte[1048576000];

                    while(true) {
                        int len;
                        if ((len = in.read(buffer)) <= 0) {
                            String sha1 = (new BigInteger(1, digest.digest())).toString(16);
                            int length = 40 - sha1.length();
                            if (length > 0) {
                                for(int i = 0; i < length; ++i) {
                                    sha1 = "0" + sha1;
                                }
                            }

                            HotfixCheck = sha1;
                            break;
                        }

                        digest.update(buffer, 0, len);
                    }
                } catch (Throwable var14) {
                    try {
                        in.close();
                    } catch (Throwable var11) {
                        var14.addSuppressed(var11);
                    }

                    throw var14;
                }

                in.close();
            } catch (Exception var15) {
                Exception e = var15;
                e.getStackTrace();
            }

            try {
                BufferedRandomAccessFile raf = new BufferedRandomAccessFile(file, "r");

                try {
                    MaplePacketReader lea = new MaplePacketReader(new RandomAccessByteStream(raf));
                    byte magic = lea.readByte();
                    raf.close();
                    if (magic == 115) {
                        WzIMGFile img = new WzIMGFile(file.getPath(), new MapleDataFileEntry("", (MapleDataEntity)null, ""));

                        String name;
                        MapleData dat;
                        for(Iterator var22 = img.getRoot().getChildren().iterator(); var22.hasNext(); HotfixDataMap.put(name, dat)) {
                            dat = (MapleData)var22.next();
                            String s = dat.getPath();
                            MapleDataEntity dd = dat.getParent();
                            name = dat.getName().replace("\\", "/");
                            if (dat instanceof WzFileMapleData) {
                                ((WzFileMapleData)dat).setName(name.substring(name.lastIndexOf("/") + 1));
                            }
                        }
                    } else {
                        System.err.println("Data.wz檔案不是正確的Hotfix檔案:" + file.getPath());
                    }
                } catch (Throwable var12) {
                    try {
                        raf.close();
                    } catch (Throwable var10) {
                        var12.addSuppressed(var10);
                    }

                    throw var12;
                }

                raf.close();
            } catch (Exception var13) {
                System.err.println("讀取檔案時出錯:" + file.getPath());
            }

        }
    }

    public static Map<String, MapleData> getHotfixDatas() {
        hotfixLoadLock.lock();

        LinkedHashMap var0;
        try {
            if (HotfixDataMap == null) {
                loadHotfixData();
            }

            var0 = new LinkedHashMap(HotfixDataMap);
        } finally {
            hotfixLoadLock.unlock();
        }

        return var0;
    }

    public static String getHotfixCheck() {
        hotfixLoadLock.lock();

        String var0;
        try {
            if (HotfixDataMap == null) {
                loadHotfixData();
            }

            var0 = HotfixCheck;
        } finally {
            hotfixLoadLock.unlock();
        }

        return var0;
    }
}
