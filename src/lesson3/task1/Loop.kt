@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.sqrt

// Урок 3: циклы
// Максимальное количество баллов = 9
// Рекомендуемое количество баллов = 7
// Вместе с предыдущими уроками = 16/21

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая (2 балла)
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var number = abs(n)
    var i = 0
    do {
        i += 1
        number /= 10
    } while (number > 0)
    return i

}

/**
 * Простая (2 балла)
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var k = 2
    var n1 = 1
    var n2 = 1
    var sum = 0
    if (n <= 2) return 1
    while (n > k) {
        k += 1
        sum = n1 + n2
        n1 = n2
        n2 = sum
    }
    return sum
}

/**
 * Простая (2 балла)
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var i = 1
    while (i < n) {
        i += 1
        if (n % i == 0) break
    }
    return i
}

/**
 * Простая (2 балла)
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var i = 0
    var maxD = 0
    while (i < n) {
        i += 1
        if (i == n) break
        maxD = if (n % i == 0) i
        else maxD
    }
    return maxD
}

/**
 * Простая (2 балла)
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var i = 0
    var xD = x
    while (xD != 1) {
        i++
        if (xD % 2 == 0) xD /= 2
        else xD = 3 * xD + 1
    }
    return i
}

/**
 * Средняя (3 балла)
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var xM = m
    var xN = n
    while ((xM != 0) && (xN != 0)) {
        if (xM > xN) xM %= xN
        else xN %= xM
    }
    return m / max(xM, xN) * n
}

/**
 * Средняя (3 балла)
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    var i = 1
    var result = true
    val maxMN = Math.max(m, n)
    while (i < maxMN) {
        i += 1
        if ((m % i == 0) && (n % i == 0)) {
            result = false
            break
        } else result = true
    }
    return result
}

/**
 * Средняя (3 балла)
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var revNum = 0
    var number = n
    while (number > 0) {
        revNum = revNum * 10 + number % 10
        number /= 10
    }
    return revNum
}

/**
 * Средняя (3 балла)
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean {
    var number = n
    var revNum = 0
    while (number > 0) {
        revNum = revNum * 10 + number % 10
        number /= 10
    }
    return revNum == n
}

/**
 * Средняя (3 балла)
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var result = false
    var number = n
    val numeral = number % 10
    number /= 10
    while (number > 0) {
        if (number % 10 != numeral) result = true
        number /= 10
    }
    return result
}

/**
 * Средняя (4 балла)
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */
fun sin(x: Double, eps: Double): Double {
    var xC = abs(x) % (2 * PI)
    if (x < 0) xC *= (-1)
    var i = 2.0
    var f = 1.0
    var factorial = 1.0
    var sqr = xC * xC
    var result = 0.0
    var promResult = xC
    while (abs(promResult) >= eps) {
        result += promResult * f
        f *= (-1.0)
        factorial *= (i * (i + 1.0))
        promResult = ((xC * sqr) / factorial)
        sqr *= (xC * xC)
        i += 2.0
    }
    return result
}

/**
 * Средняя (4 балла)
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double {
    var xC = abs(x) % (2 * PI)
    if (x < 0) xC *= (-1)
    var i = 2.0
    var f = 1.0
    var factorial = 1.0
    var sqr = xC * xC
    var result = 0.0
    var promResult = 1.0
    while (abs(promResult) >= eps) {
        result += promResult * f
        f *= (-1.0)
        factorial *= (i * (i - 1.0))
        promResult = (sqr / factorial)
        sqr *= (xC * xC)
        i += 2.0
    }
    return result
}

/**
 * Сложная (4 балла)
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    var result = 0
    var i = 0
    var k = 0
    var d: Int
    var sum = 0
    var preSum = 0
    while (sum < n) {
        i += 1
        k = i * i
        d = k
        var j = 0
        while (d > 0) {
            d /= 10
            j += 1
        }
        preSum = sum
        sum += j
    }
    var range = sum - preSum
    val need = n - preSum
    if (range == need) result = k % 10
    else while (range > need) {
        k /= 10
        result = k % 10
        range -= 1
    }
    return result
}

/**
 * Сложная (5 баллов)
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    var number = n
    var k = 0
    var sum = 0
    var previousSum = 0
    var fibPrevNumber = 1
    var fibPresNumber = 1
    if (number <= 2) {
        sum = 2
        previousSum = 1
        number = 4
        k = 1
    } else {
        while (sum < (number - 2)) {
            k = fibPrevNumber + fibPresNumber
            fibPrevNumber = fibPresNumber
            fibPresNumber = k
            var d = k
            var j = 0
            while (d > 0) {
                d /= 10
                j += 1
            }
            previousSum = sum
            sum += j
        }
    }
    var range = sum - previousSum
    val need = (number - 2) - previousSum
    var result = 0
    if (range == need) result = k % 10
    else while (range > need) {
        k /= 10
        result = k % 10
        range -= 1
    }
    return result
}
