import { pic } from "scripts/expands/pic.js";

let first = 30000;
let last = 68537;

let selection = npc.askMenuA("#fc0xFF87CEEB#"+pic.聖誕鈴鐺+"歡迎來到自選機器人髮型功能NPC ：\r\n\r\n"+pic.驚嘆號金色+"#r.如兌換馬上斷線請立即回報機器人髮型代碼。\r\n\r\n\r\n#L0##b〔 開始兌換 〕#l\r\n\t\r\n");

switch (selection) {
    case 0:
        var number = npc.askNumber("#b請輸入想變更的機器人髮型代碼#r(單次使用花費樂豆點數:150)：", first, first, last);
        if (player.getCash() >= 150) {
            if (!player.hasAndroid()) {
                npc.askMenuE("#r" + pic.楷體 + "很抱歉,您未裝備機器人。");
            } else {
                player.setAndroidHair(number);
                player.modifyCashShopCurrency(1, -150);
                npc.askMenuE("變更機器人髮型完成。")
            }
        }
        break;
}