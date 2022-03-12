@file:Suppress("UNUSED_PARAMETER")

package lesson11.task1

/**
 * Класс "полином с вещественными коэффициентами".
 *
 * Общая сложность задания -- средняя, общая ценность в баллах -- 16.
 * Объект класса -- полином от одной переменной (x) вида 7x^4+3x^3-6x^2+x-8.
 * Количество слагаемых неограничено.
 *
 * Полиномы можно складывать -- (x^2+3x+2) + (x^3-2x^2-x+4) = x^3-x^2+2x+6,
 * вычитать -- (x^3-2x^2-x+4) - (x^2+3x+2) = x^3-3x^2-4x+2,
 * умножать -- (x^2+3x+2) * (x^3-2x^2-x+4) = x^5+x^4-5x^3-3x^2+10x+8,
 * делить с остатком -- (x^3-2x^2-x+4) / (x^2+3x+2) = x-5, остаток 12x+16
 * вычислять значение при заданном x: при x=5 (x^2+3x+2) = 42.
 *
 * В конструктор полинома передаются его коэффициенты, начиная со старшего.
 * Нули в середине и в конце пропускаться не должны, например: x^3+2x+1 --> Polynom(1.0, 2.0, 0.0, 1.0)
 * Старшие коэффициенты, равные нулю, игнорировать, например Polynom(0.0, 0.0, 5.0, 3.0) соответствует 5x+3
 */
class Polynom(vararg coeffs: Double) {
    private val coefficients: MutableList<Double>

    init {
        val startIndex = coeffs.indexOfFirst { it != 0.0 }
        if (startIndex == -1) {
            this.coefficients = mutableListOf()
        } else {
            this.coefficients = mutableListOf()
            for (i in 0 until (coeffs.size - startIndex)) this.coefficients.add(coeffs[coeffs.size - 1 - i])
        }
    }

    /**
     * Геттер: вернуть значение коэффициента при x^i
     */
    fun coeff(i: Int): Double = coefficients.getOrNull(i) ?: throw IndexOutOfBoundsException()

    /**
     * Расчёт значения при заданном x
     */
    fun getValue(x: Double): Double {
        var sum = 0.0
        var currentElement = 1.0
        for (element in coefficients) {
            sum += currentElement * element
            currentElement *= x
        }
        return sum
    }

    /**
     * Степень (максимальная степень x при ненулевом слагаемом, например 2 для x^2+x+1).
     *
     * Степень полинома с нулевыми коэффициентами считать равной 0.
     * Слагаемые с нулевыми коэффициентами игнорировать, т.е.
     * степень 0x^2+0x+2 также равна 0.
     */
    fun degree(): Int {
        val max = coefficients.size - 1
        return Math.max(max, 0)
        //return if (max > 0) max
        //else 0
    }

    /**
     * Сложение
     */
    operator fun plus(other: Polynom): Polynom {
        val resultCoefficients = mutableListOf<Double>()
        val sizeResult = Math.max(coefficients.size, other.coefficients.size)
        for (index in 0 until sizeResult) resultCoefficients.add(0.0)
        for (index in 0 until coefficients.size) {
            resultCoefficients[index] += coefficients[index]
        }
        for (index in 0 until other.coefficients.size) {
            resultCoefficients[index] += other.coefficients[index]
        }
        return Polynom(*resultCoefficients.toDoubleArray().reversedArray())
    }

    /**
     * Смена знака (при всех слагаемых)
     */
    operator fun unaryMinus(): Polynom {
        val resultCoefficients = coefficients.map {
            if (it != 0.0) -it
            else 0.0
        }
        return Polynom(*resultCoefficients.toDoubleArray().reversedArray())
    }

    /**
     * Вычитание
     */
    operator fun minus(other: Polynom): Polynom {
        val sizeResult = Math.max(coefficients.size, other.coefficients.size)
        val resultCoefficients = MutableList(sizeResult) { 0.0 }
        val polynom1 = MutableList(sizeResult) { 0.0 }
        val polynom2 = MutableList(sizeResult) { 0.0 }
        for ((index, element) in coefficients.withIndex()) polynom1[index] = element
        for ((index, element) in other.coefficients.withIndex()) polynom2[index] = element
        for (index in 0 until sizeResult) {
            resultCoefficients[index] = polynom1[index] - polynom2[index]
        }
        return Polynom(*resultCoefficients.toDoubleArray().reversedArray())
    }

    /**
     * Умножение
     */
    operator fun times(other: Polynom): Polynom {
        val resultCoefficients = MutableList(coefficients.size + other.coefficients.size - 1) { 0.0 }
        for (index in 0 until coefficients.size) {
            for (indexOther in 0 until other.coefficients.size) {
                resultCoefficients[index + indexOther] += coefficients[index] * other.coefficients[indexOther]
            }
        }
        return Polynom(*resultCoefficients.toDoubleArray().reversedArray())
    }

    fun divWithRem(other: Polynom): Pair<Polynom, Polynom> {
        if (other.coefficients.size == 0) throw IllegalArgumentException()
        if (other.degree() > this.degree()) return Pair(
            Polynom(),
            Polynom(*this.coefficients.toDoubleArray().reversedArray())
        )
        val currentCoefficients = coefficients.map { it }.toMutableList()
        val result = mutableListOf<Double>()
        while (true) {
            val difIndex = currentCoefficients.size - other.coefficients.size
            if (difIndex < 0) break
            val quotient =
                currentCoefficients[currentCoefficients.size - 1] / other.coefficients[other.coefficients.size - 1]
            result.add(quotient)
            currentCoefficients.removeAt(currentCoefficients.size - 1)
            for (i in 0 until currentCoefficients.size - difIndex) {
                currentCoefficients[difIndex + i] -= quotient * other.coefficients[i]
            }
        }
        return Pair(
            Polynom(*result.toDoubleArray()),
            Polynom(*currentCoefficients.toDoubleArray().reversedArray())
        )
    }

    /**
     * Деление
     *
     * Про операции деления и взятия остатка см. статью Википедии
     * "Деление многочленов столбиком". Основные свойства:
     *
     * Если A / B = C и A % B = D, то A = B * C + D и степень D меньше степени B
     */
    operator fun div(other: Polynom): Polynom = this.divWithRem(other).first

    /**
     * Взятие остатка
     */
    operator fun rem(other: Polynom): Polynom = this.divWithRem(other).second

    /**
     * Сравнение на равенство
     */
    override fun equals(other: Any?): Boolean = other is Polynom && coefficients == other.coefficients

    /**
     * Получение хеш-кода
     */
    override fun hashCode(): Int = coefficients.hashCode()
}
