package by.itacademy.pvt.dz12.mvp

import by.itacademy.pvt.dz12.Student

interface Dz12StudentDetailsView {
    fun backStack() {}
    fun showStudent(student: Student) {}
    fun onError() {}
}