/*     
 *  
 *  功能：[啾啾島]頂級大廚舔舔的祕製料理
 *  

 */

npc.next().sayX("#ho#！噢！你這麼快就蒐集到材料啦！好的，那現在來完成我的#r特製料理#k吧？！");
npc.ui(1).sayX("我很快就能做好了，你稍後再來找我吧！哈！");
player.loseItem(4034942, 20);
npc.completeQuest();
player.gainExp(300000000);