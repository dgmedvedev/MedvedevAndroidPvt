package by.itacademy.pvt.dz6

class Student(val id: Int, val imageUrl: String, val name: String, val age: Int)

fun getFakeItems(): List<Student> {
    return listOf(
            Student(1, "", "Макс", 20),
            Student(2, "", "Дима", 21),
            Student(3, "", "Макс", 22),
            Student(4, "", "Дима", 23),
            Student(5, "", "Макс", 24),
            Student(6, "", "Дима", 25),
            Student(7, "", "Макс", 23),
            Student(8, "", "Дима", 24),
            Student(9, "", "Макс", 25)
    )
}