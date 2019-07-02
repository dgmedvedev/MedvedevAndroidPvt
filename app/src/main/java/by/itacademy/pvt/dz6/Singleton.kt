package by.itacademy.pvt.dz6

object Singleton {
    private var listStudent = arrayListOf(
        Student(1, "", "Иванов Иван", 20),
        Student(2, "", "Петров Петр", 20),
        Student(3, "", "Сидоров Сидр", 20)
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