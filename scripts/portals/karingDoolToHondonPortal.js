if (map.getReactorStateId("hondonPt") > 0) {
    player.changeMap(map.getId() + 40, 0)
} else {
    portal.abortWarp()
}