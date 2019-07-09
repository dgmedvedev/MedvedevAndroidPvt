package by.itacademy.pvt.dz6

object Singleton {

    private var listStudent = arrayListOf(
        Student(1, "https://clck.ru/Gx4jk", "Иванов Иван Иванович", 20),
        Student(2, "https://clck.ru/GskPq", "Петров Петр Петрович", 25),
        Student(3, "https://clck.ru/Gx4Nd", "Сидоров Сидр Сидорович", 30)
    )

    fun getListStudent(): ArrayList<Student> {
        return listStudent
    }

    fun filter(search: String): List<Student> {
        return listStudent.filter { it.name.toUpperCase().contains(search.toUpperCase()) }
    }

    fun getStudentById(id: Long): Student? {
        return listStudent.find { it.id == id }
    }
}