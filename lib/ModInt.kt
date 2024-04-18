package lib

class ModInt(private var value: Long = 0L,
             private val mod: Long = 998277353L) {
  private fun check() {
    value %= mod
  }

  init {
    check()
  }

  fun compareTo(other: ModInt) = value.compareTo(other.value)

  override fun toString() = value.toString()

  override fun equals(other: Any?) = value == other

  override fun hashCode() = (value.hashCode() xor mod.hashCode()).hashCode()

  operator fun unaryPlus() = ModInt(+value, mod)

  operator fun unaryMinus() = ModInt(-value, mod)

  operator fun not() = !toBool()

  operator fun plus(other: ModInt) = ModInt((value + other.value) % mod % other.mod, mod)

  operator fun minus(other: ModInt) = ModInt((value - other.value) % mod % other.mod, mod)

  operator fun times(other: ModInt) = ModInt((value * other.value) % mod % other.mod)

  operator fun div(other: ModInt) = ModInt((value / other.value) % mod / other.mod, mod)

  operator fun rem(other: ModInt) = ModInt((value % other.value) % mod % other.mod, mod)

  operator fun inc(it: ModInt): ModInt {
    it.value = (it.value + 1) % mod
    return it
  }

  operator fun inc(): ModInt {
    val int = this
    value = (value + 1) % mod
    return int
  }

  operator fun dec(it: ModInt): ModInt {
    it.value = (it.value - 1) % mod
    return it
  }

  operator fun dec(): ModInt {
    val int = this
    value = (value - 1) % mod
    return int
  }

  operator fun rangeTo(other: ModInt) = value..other.value

  operator fun rangeUntil(other: ModInt) = value..<other.value

  operator fun plusAssign(other: ModInt) {
    value = (value + other.value) % mod
  }

  operator fun plusAssign(other: Long) {
    value = (value + other) % mod
  }

  operator fun minusAssign(other: ModInt) {
    value = (value - other.value) % mod
  }

  operator fun minusAssign(other: Long) {
    value = (value - other) % mod
  }

  operator fun timesAssign(other: ModInt) {
    value = (value * other.value) % mod
  }

  operator fun timesAssign(other: Long) {
    value = (value * other) % mod
  }

  operator fun divAssign(other: ModInt) {
    value = (value / other.value) % mod
  }

  operator fun divAssign(other: Long) {
    value = (value / other) % mod
  }

  fun toBool() = value != 0L

  fun toByte() = value.toByte()

  fun toUByte() = value.toUByte()

  fun toShort() = value.toShort()

  fun toUShort() = value.toUShort()

  fun toInt() = value.toInt()

  fun toUInt() = value.toUInt()

  fun toLong() = value

  fun toULong() = value.toULong()

  fun toFloat() = value.toFloat()

  fun toDouble() = value.toDouble()

  fun getValue() = value
}