/*  
 *  
 *  功能：另一種力量，神祕力量
 *  
 *
 */

npc.next().sayX("請稍等下。在你出發前往<神祕河畔>之前，我有話要對你說。");
npc.askMenuX("你還記得這裡的一位名叫#b卡奧#k的神官嗎？\r\n\r\n#b#L0#記得#l");
npc.next().sayX("#fNpc/3003131.img/stand/0#\r\n那個孩子最終還是沒能查清楚自己的真實身份。之前為了查出自己的真實身份，他付出了很多努力，不惜做任何事情。");
npc.askMenuX("我們神官隨著艾爾達的異常流動，前往現在之門另一邊時，那個孩子也一起消失了。\r\n我試圖想挽留那個孩子，可是已經太遲了。\r\n\r\n#b#L0#我這就去現在之門的另一邊找他#l");
npc.askMenuX("請等一下。神祕河畔的怪物出生在艾爾達密度極高的河水中...\r\n\r\n你必須擁有#e<神祕力量>#n，才能發揮出所有的力量。\r\n\r\n#b#L0#<神祕力量>？#l");
npc.ui(1).sayX("百聞不如一見，請你先去試著狩獵那個地方的怪物吧。在那之後，我會重新去找你的。\r\n\r\n#b(前往現在之門另一邊的神祕河畔，試著狩獵第一個見到的怪物，然後再獲得旁觀者的幫助吧。)#k");
npc.startQuest();
player.scriptProgressMessage("請前往神祕湖畔消滅10個喜悅艾爾達。");
