package by.itacademy.pvt.dz11.mvp

import by.itacademy.pvt.dz6.Student

interface Dz11StudentDetailsView {
    fun backStack() {}
    fun showStudent(student: Student) {}
    fun onError() {}
}