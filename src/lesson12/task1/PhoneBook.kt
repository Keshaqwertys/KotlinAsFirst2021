@file:Suppress("UNUSED_PARAMETER")

package lesson12.task1

/**
 * Класс "Телефонная книга".
 *
 * Общая сложность задания -- средняя, общая ценность в баллах -- 14.
 * Объект класса хранит список людей и номеров их телефонов,
 * при чём у каждого человека может быть более одного номера телефона.
 * Человек задаётся строкой вида "Фамилия Имя".
 * Телефон задаётся строкой из цифр, +, *, #, -.
 * Поддерживаемые методы: добавление / удаление человека,
 * добавление / удаление телефона для заданного человека,
 * поиск номера(ов) телефона по заданному имени человека,
 * поиск человека по заданному номеру телефона.
 *
 * Класс должен иметь конструктор по умолчанию (без параметров).
 */
class PhoneBook {
    private var start: Node? = null

    private class Node(
        val name: String,
        var phones: Set<String>,
        var next: Node?
    )

    private fun searchName(name: String): Node? {
        var curSegment = start
        while (curSegment != null) {
            if (curSegment.name == name) return curSegment
            curSegment = curSegment.next
        }
        return null
    }

    private fun searchPhone(phone: String): Node? {
        var curSegment = start
        while (curSegment != null) {
            if (phone in curSegment.phones) return curSegment
            curSegment = curSegment.next
        }
        return null
    }

    /**
     * Добавить человека.
     * Возвращает true, если человек был успешно добавлен,
     * и false, если человек с таким именем уже был в телефонной книге
     * (во втором случае телефонная книга не должна меняться).
     */
    fun addHuman(name: String): Boolean {
        return if (searchName(name) == null) {
            start = Node(name, setOf(), start)
            true
        } else false
    }

    /**
     * Убрать человека.
     * Возвращает true, если человек был успешно удалён,
     * и false, если человек с таким именем отсутствовал в телефонной книге
     * (во втором случае телефонная книга не должна меняться).
     */

    fun removeHuman(name: String): Boolean {
        return if (searchName(name) == null) false
        else {
            var currentElement = start!!
            while (currentElement.next != null) {
                if (currentElement.next!!.name == name) {
                    currentElement.next = currentElement.next!!.next
                    break
                }
                currentElement = currentElement.next!!
            }
            true
        }
    }


    /**
     * Добавить номер телефона.
     * Возвращает true, если номер был успешно добавлен,
     * и false, если человек с таким именем отсутствовал в телефонной книге,
     * либо у него уже был такой номер телефона,
     * либо такой номер телефона зарегистрирован за другим человеком.
     */
    fun addPhone(name: String, phone: String): Boolean {
        return if ((searchName(name) == null) || (searchPhone(phone) != null)) {
            false
        } else {
            searchName(name)!!.phones += phone
            true
        }
    }

    /**
     * Убрать номер телефона.
     * Возвращает true, если номер был успешно удалён,
     * и false, если человек с таким именем отсутствовал в телефонной книге
     * либо у него не было такого номера телефона.
     */
    fun removePhone(name: String, phone: String): Boolean {
        return if ((searchName(name) == null) || (phone !in searchName(name)!!.phones)) {
            false
        } else {
            searchName(name)!!.phones -= phone
            true
        }
    }

    /**
     * Вернуть все номера телефона заданного человека.
     * Если этого человека нет в книге, вернуть пустой список
     */
    fun phones(name: String): Set<String> = searchName(name)?.phones ?: setOf<String>()

    /**
     * Вернуть имя человека по заданному номеру телефона.
     * Если такого номера нет в книге, вернуть null.
     */
    fun humanByPhone(phone: String): String? = searchPhone(phone)?.name

    fun getLength(): Int {
        var curent = start
        var count = 0
        if (curent != null) {
            while (curent!!.next != null) {
                curent = curent.next
                count++
            }
            count += 1
        }
        return count
    }

    /**
     * Две телефонные книги равны, если в них хранится одинаковый набор людей,
     * и каждому человеку соответствует одинаковый набор телефонов.
     * Порядок людей / порядок телефонов в книге не должен иметь значения.
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PhoneBook) return false
        var current = other.start
        while (current != null) {
            if (current.phones != searchName(current.name)?.phones) return false
            current = current.next
        }
        return this.getLength() == other.getLength()
    }
}