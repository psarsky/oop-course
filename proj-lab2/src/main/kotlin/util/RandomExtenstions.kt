package util

fun <T> Map<Vector2d, T>.randomPosition(): Vector2d? = keys.toList().randomOrNull()

fun <T> Map<Vector2d, T>.randomFreePosition(mapSize: Vector2d): Vector2d? {
    val emptyPos: ArrayList<Vector2d> = ArrayList()
    for (i: Int in 0 ..< mapSize.x) {
        for (j: Int in 0 ..< mapSize.y) {
            if (!this.containsKey(Vector2d(i, j))) {
                emptyPos.add(Vector2d(i, j))
            }
        }
    }
    return emptyPos.randomOrNull()
}