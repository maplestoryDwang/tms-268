//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Plugin.provider.json;

import Plugin.provider.MapleData;
import Plugin.provider.MapleDataEntity;
import Plugin.provider.MapleDataType;
import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import lombok.Generated;
import org.json.JSONObject;

public class JsonDomMapleData implements MapleData {
    private JSONObject node;
    private MapleDataEntity parent;
    private String key;
    private String imageDataDir;

    private JsonDomMapleData(JSONObject node, MapleDataEntity parent, String file, String key) {
        this.node = node;
        this.parent = parent;
        this.imageDataDir = file;
        this.key = key;
    }

    public JsonDomMapleData(FileInputStream fis, File imageDataDir) {
        try {
            this.node = new JSONObject(new String(fis.readAllBytes()));
        } catch (IOException var4) {
            IOException e = var4;
            throw new RuntimeException(e);
        }

        this.imageDataDir = imageDataDir.getName();
    }

    public MapleData getChildByPath(String path) {
        String[] keys = path.split("/");
        JsonDomMapleData current = this;
        String[] var4 = keys;
        int var5 = keys.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            String k = var4[var6];
            if (!current.node.has(k)) {
                return null;
            }

            Object obj = current.node.get(k);
            if (!(obj instanceof JSONObject)) {
                return null;
            }

            current = new JsonDomMapleData((JSONObject)obj, current, this.imageDataDir + "/" + k, k);
            if (current.getType() != MapleDataType.PROPERTY) {
                return current;
            }
        }

        return current;
    }

    public List<MapleData> getChildren() {
        List<MapleData> ret = new ArrayList();
        Iterator var2 = this.node.keySet().iterator();

        while(var2.hasNext()) {
            String i = (String)var2.next();
            if (this.node.get(i) instanceof JSONObject) {
                ret.add(new JsonDomMapleData((JSONObject)this.node.get(i), this, this.imageDataDir + "/" + i, i));
            }
        }

        return ret;
    }

    public Object getData() {
        MapleDataType type = this.getType();
        Object data = null;
        switch (type) {
            case PROPERTY:
                return null;
            case DOUBLE:
                data = Double.parseDouble(this.node.getString("_value"));
                break;
            case FLOAT:
                data = Float.parseFloat(this.node.getString("_value"));
                break;
            case INT:
                try {
                    data = Integer.parseInt(this.node.getString("_value"));
                } catch (Exception var5) {
                    data = Long.parseLong(this.node.getString("_value"));
                }
                break;
            case LONG:
                data = Long.parseLong(this.node.getString("_value"));
                break;
            case SHORT:
                data = Short.parseShort(this.node.getString("_value"));
                break;
            case VECTOR:
                data = new Point(this.node.getInt("_x"), this.node.getInt("_y"));
            case SOUND:
            case CANVAS:
                break;
            default:
                try {
                    data = this.node.getString("_value");
                } catch (Exception var4) {
                    Exception e = var4;
                    System.out.println("JSON err");
                    e.printStackTrace();
                }
        }

        return data;
    }

    public MapleDataType getType() {
        if (!this.node.has("_dirType")) {
            return MapleDataType.PROPERTY;
        } else {
            switch (this.node.getString("_dirType")) {
                case "sub" -> {
                    return MapleDataType.PROPERTY;
                }
                case "canvas" -> {
                    return MapleDataType.CANVAS;
                }
                case "convex" -> {
                    return MapleDataType.CONVEX;
                }
                case "sound" -> {
                    return MapleDataType.SOUND;
                }
                case "uol" -> {
                    return MapleDataType.UOL;
                }
                case "double" -> {
                    return MapleDataType.DOUBLE;
                }
                case "float" -> {
                    return MapleDataType.FLOAT;
                }
                case "long" -> {
                    return MapleDataType.LONG;
                }
                case "int" -> {
                    return MapleDataType.INT;
                }
                case "short" -> {
                    return MapleDataType.SHORT;
                }
                case "string" -> {
                    return MapleDataType.STRING;
                }
                case "vector" -> {
                    return MapleDataType.VECTOR;
                }
                case "null" -> {
                    return MapleDataType.IMG_0x00;
                }
                default -> {
                    return null;
                }
            }
        }
    }

    public MapleDataEntity getParent() {
        return this.parent;
    }

    public String getPath() {
        String var10000 = this.imageDataDir.replaceAll("\\\\", "/");
        return var10000 + "/" + this.getName();
    }

    public String getName() {
        return this.key;
    }

    public Iterator<MapleData> iterator() {
        return this.getChildren().iterator();
    }

    @Generated
    public JSONObject getNode() {
        return this.node;
    }
}
