/* MapleShark JavaScript tools 生成器 - msb */

npc.next().sayX("你知道消失的邊際事件嗎？就是楓之谷世界與格蘭蒂斯的邊界消失的大事件。真的很驚人。");
npc.next().sayX("但事件並沒有這樣結束。消失的邊際讓遙遠東方大海那邊發生了詭異的事。兩個島嶼變成一個...");
npc.next().sayX("你問是什麼意思嗎？楓之谷世界的島和格蘭蒂斯的島合而為一了，就像是兩個不同的蛋糕的一半貼在一起！這件事真的很有趣吧?");
npc.next().sayX("而且島上竟然還有尚未發現的古代遺物…這是讓考古學家非常興奮的一件事！對你這種人來說也一樣吧？");
npc.next().sayX("那你就和我一起去吧。夏天與冬天之島，伊甸提斯克！");
if (npc.askYesNo("那就立刻出發吧！前往伊甸提斯克的船已經準備好了！\r\n#r#e※ 同意時立刻前往伊甸提斯克。")) {
    player.changeMap(993228000);
    npc.completeQuest();
} else { npc.sayX("如果覺得還是很好奇,記得再次與我對話。"); }
