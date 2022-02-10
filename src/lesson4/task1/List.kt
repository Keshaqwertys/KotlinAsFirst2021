@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import kotlin.math.sqrt

// Урок 4: списки
// Максимальное количество баллов = 12
// Рекомендуемое количество баллов = 8
// Вместе с предыдущими уроками = 24/33

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.lowercase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая (2 балла)
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var result = 0.0
    for (n in v) {
        val number = Math.pow(n, 2.0)
        result += number
    }
    result = sqrt(result)
    return result
}

/**
 * Простая (2 балла)
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    var sum = 0.0
    for (n in list) {
        sum += n
    }
    return if (list.isEmpty()) 0.0
    else sum / list.size
}

/**
 * Средняя (3 балла)
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    var i = 0
    var number: Double
    var sum = 0.0
    val average: Double
    return if (!list.isEmpty()) {
        i = list.size
        sum = list.sum()
        average = sum / i
        i = 0
        while (i in list.indices) {
            number = list[i]
            list[i] = number - average
            i++
        }
        list
    } else list
}

/**
 * Средняя (3 балла)
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    var i = 0
    val c = mutableListOf<Int>()
    var result = 0
    var ci: Int
    return if (!a.isEmpty()) {
        while (i in a.indices) {
            ci = a[i] * b[i]
            c.add(ci)
            i++
        }
        result = c.sum()
        result
    } else result
}

/**
 * Средняя (3 балла)
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    var i = 1
    var result = 0
    var number: Int
    var nX: Int = x
    return if (!p.isEmpty()) {
        result = p[0]
        while (i in p.indices) {
            number = nX * p[i]
            result += number
            nX *= x
            i++
        }
        result
    } else result
}

/**
 * Средняя (3 балла)
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    var i = 1
    var number: Int
    var n: Int
    return if (!list.isEmpty()) {
        var sum = list[0]
        while (i in list.indices) {
            n = list[i]
            number = n + sum
            sum += n
            list[i] = number
            i++
        }
        list
    } else list
}

/**
 * Средняя (3 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val list = mutableListOf<Int>()
    var n = n
    var d = 2
    while (n > 1) {
        while (n % d == 0) {
            list.add(d)
            n /= d
        }
        d++
    }
    return list
}

/**
 * Сложная (4 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")

/**
 * Средняя (3 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var number = n
    val list = mutableListOf<Int>()
    do {
        list.add(0, number % base)
        number /= base
    } while (number > 0)
    return list
}

/**
 * Сложная (4 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
val alphabet = "abcdefghijklmnopqrstuvwxyz"
fun convertToString(n: Int, base: Int): String {
    var n = n
    var counter = 0
    val list = mutableListOf<Any>()
    val slist = convert(n, base)
    for (k in slist) {
        if (k < 10) list.add(k)
        else list.add(alphabet[k - 10])
        counter += 1
    }
    return list.joinToString(separator = "")
}

/**
 * Средняя (3 балла)
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var result = 0
    var number = 1
    for (i in digits.size - 1 downTo 0) {
        result += (number * digits[i])
        number *= base
    }
    return result
}

/**
 * Сложная (4 балла)
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int {
    val alphabet = "abcdefghijklmnopqrstuvwxyz"
    val list = mutableListOf<Int>()
    for (char in str) {
        if (char in alphabet) list.add(char - 'a' + 10)
        else list.add(char - '0')
    }
    return decimal(list, base)
}

/**
 * Сложная (5 баллов)
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    var n = n
    var number: Int
    val result: String
    var i = 0
    val list = mutableListOf<String>()
    var M: List<String>
    while (n > 0) {
        M = when (i) {
            0 -> listOf("I", "V", "X")
            1 -> listOf("X", "L", "C")
            2 -> listOf("C", "D", "M")
            else -> listOf("M")
        }
        number = n % 10
        n /= 10
        i++
        when (number) {
            in 1..3 -> list.add(0, M[0].repeat(number))
            4 -> list.add(0, M[0] + M[1])
            in 5..8 -> list.add(0, M[1] + M[0].repeat(number - 5))
            9 -> list.add(0, M[0].repeat(10 - number) + M[2])
        }
    }
    result = list.joinToString(separator = "")
    return result
}

/**
 * Очень сложная (7 баллов)
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
val number = listOf("один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять")
val numberDec = listOf("одна", "две", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять")
val tensDec = listOf(
    "одиннадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать",
    "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать"
)
val tens = listOf(
    "десять", "двадцать", "тридцать", "сорок", "пятьдесят",
    "шестьдесят", "семьдесят", "восемьдесят", "девяносто"
)
val hundred = listOf(
    "сто",
    "двести",
    "триста",
    "четыреста",
    "пятьсот",
    "шестьсот",
    "семьсот",
    "восемьсот",
    "девятьсот"
)
val conditionCHI = listOf("две", "три", "четыре")
fun determination(a: Int, b: Int, list: MutableList<String>): String {
    val answer = when {
        (list[a] == "одна") -> "тысяча"
        (list[a] in conditionCHI) -> "тысячи"
        else -> "тысяч"
    }
    return answer
}

fun russian(n: Int): String {
    var num = n
    var step = 0
    var curNum: Int
    var switch = 0
    val list = mutableListOf<String>()
    var rank = listOf<String>()
    var prevNum = 0
    while (num > 0) {
        when (step) {
            0 -> rank = number
            1 -> rank = tens
            2 -> rank = hundred
            3 -> rank = numberDec
            4 -> rank = tens
            5 -> rank = hundred
        }
        curNum = num % 10
        num /= 10
        if (step == 3) list.add(0, "plug")
        when (curNum) {
            in 1..9 -> list.add(0, rank[curNum - 1])
        }
        when (curNum) {
            0 -> list.add(0, "")
        }
        when {
            (step == 1) -> {
                if ((list[1] in number) && (list[0] == tens[0])) {
                    list[0] = tensDec[prevNum - 1]
                    list.removeAt(1)
                }
            }
        }
        when {
            (step == 4) -> {
                if ((list[1] in numberDec) && (list[0] == tens[0])) {
                    list[1] = tensDec[prevNum - 1]
                    list.removeAt(0)
                    switch = 1
                }
            }
        }
        step++
        prevNum = curNum
    }
    if (step == 4) list[1] = determination(0, 1, list)
    if (step == 5) {
        when (switch) {
            0 -> list[2] = determination(1, 2, list)
            1 -> list[1] = "тысяч"
        }
    }
    if (step == 6) {
        when (switch) {
            0 -> list[3] = determination(2, 3, list)
            1 -> list[2] = "тысяч"
        }
    }
    list.removeAll(listOf(""))
    return list.joinToString(separator = " ")
}