// 
// Quest ID:17650
// [凱梅爾茲共和國] 報告&憂慮


if (npc.askYesNo("反正我們兩人在這裡怎麼說也說不出答案來。我整理一下掃尾工作,你可以先去向首領大人報告嗎?")) {
    npc.startQuest();
    npc.next().sayX("那麼就拜託你了。我也馬上就跟去。");
} else {
    npc.ui(1).sayX("掃尾工作我一個人就能搞定了,你趕緊去報告吧..");
}