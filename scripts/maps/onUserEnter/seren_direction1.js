player.setInGameCurNodeEventEnd(true);
player.setInGameDirectionMode(true, false, false, false);
player.setLayerBlind(true, 255, 0, 0, 0, 0, 0);
player.setInGameCurNodeEventEnd(true);
player.showSpineScreen(0, "Effect/Direction20.img/bossSeren/1pahse_spine/skeleton", "animation", "intro");
player.soundEffect("Sound/SoundEff.img/seren/1phase");
npc.setDelay(11500);
player.offSpineScreen("intro", 100);
player.inGameDirection22(700)
player.setInGameDirectionMode(false, true, false, false);
player.changeMap(map.getId() + 20, 0);