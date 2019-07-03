package by.itacademy.pvt.dz6

object Singleton {
    private var listStudent = arrayListOf(
        Student(1, "https://clck.ru/GskPq", "Иванов Иван", 20),
        Student(2, "https://clck.ru/GskPq", "Петров Петр", 21),
        Student(3, "https://clck.ru/GskPq", "Сидоров Сидр", 22)
    )

    fun getListStudent(): ArrayList<Student> {
        return listStudent
    }

    /*
    private var listStudent: ArrayList<Student>? = null

    fun getListStudent(): ArrayList<Student> {
        if (listStudent == null) {
            listStudent = arrayListOf()
        }
        return listStudent as ArrayList<Student>
    }
    */
}