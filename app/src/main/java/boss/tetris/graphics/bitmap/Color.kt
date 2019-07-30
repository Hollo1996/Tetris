package boss.tetris.graphics.bitmap

@ExperimentalUnsignedTypes
fun UInt.toColor():ColorH= ColorH(this)

@ExperimentalUnsignedTypes
data class ColorH (var value:UInt){
    enum standards(var value: ColorH) {
        TRANSPARENT(0x00000000u.toColor()),
        BLACK(0xFF000000u.toColor()),
        D_GRAY(0xFF333333u.toColor()),
        GRAY(0xFF777777u.toColor()),
        L_GRAY(0xFFBBBBBBu.toColor()),
        WHITE(0xFFFFFFFFu.toColor()),
        RED(0xFFFF0000u.toColor()),
        BLUE(0xFF00FF00u.toColor()),
        GREEN(0xFF0000FFu.toColor()),
        YELLOW(0xFFFFFF00u.toColor()),
        ORANGE(0xFFFF7700u.toColor()),
        PURPLE(0xFFFF00FFu.toColor()),
        PINK(0xFFFF7777u.toColor()),
        BROWN(0xFFFF7733u.toColor())
    }
    var rgb:ColorH
        get() = (value and 0x00FFFFFFu).toColor()
        set(c) { value= a.value + c.rgb.value }

    var a:ColorH
        get() = (value and 0xFF000000u).toColor()
        set(c) { value=(rgb.value + c.rgb.value) }

    var r:ColorH
        get() = (value and 0x00FF0000u).toColor()
        set(c) { value=(value and 0xFF00FFFFu + c.r.value) }

    var g:ColorH
        get() = (value and 0x0000FF00u).toColor()
        set(c) { value=(value and 0xFFFF00FFu + c.g.value) }

    var b:ColorH
        get() = (value and 0x000000FFu).toColor()
        set(c) { value=(value and 0xFFFFFF00u + c.b.value) }

    operator fun  times(ui:UInt) : ColorH = ColorH(value*ui)
    operator fun  times(c:ColorH) : ColorH = ColorH(value*c.value)

    operator fun div(ui:UInt) : ColorH = ColorH(value/ui)
    operator fun div(c:ColorH) : ColorH = ColorH(value/c.value)

    operator fun plus(ui:UInt) : ColorH = ColorH(value+ui)
    operator fun plus(c:ColorH) : ColorH = ColorH(value+c.value)

    operator fun minus(ui:UInt) : ColorH = ColorH(value-ui)
    operator fun minus(c:ColorH) : ColorH = ColorH(value-c.value)

    fun multiplyA(c: ColorH) : ColorH {
        val ca = c.a.value.shr(24)
        val tmp = this
        tmp.r = (this.r * ca)/0xFFu
        tmp.g = (this.g * ca)/0xFFu
        tmp.b = (this.b * ca)/0xFFu
        return tmp
    }

    fun plusColor(other: ColorH): ColorH{
        val result=this
        result.rgb = (this.multiplyA(0xFF000000u.toColor() - other.a.value) + other.multiplyA(other.a))
        return result
    }
}