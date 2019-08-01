package by.itacademy.pvt.dz12

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import by.itacademy.pvt.R
import by.itacademy.pvt.dz12.fragments.Dz12StudentDetails
import by.itacademy.pvt.dz12.fragments.Dz12StudentEdit
import by.itacademy.pvt.dz12.fragments.Dz12StudentList

class Dz12FragmentActivity : FragmentActivity(),
    Dz12StudentList.Listener,
    Dz12StudentDetails.Listener,
    Dz12StudentEdit.Listener {

    private val containerOne = R.id.containerOne
    private val containerTwo = R.id.containerTwo

    private var isPhone = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_dz8)

        isPhone = (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)

        if (savedInstanceState == null && isPhone) {

            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

            startDz12StudentList()
        } else if (savedInstanceState == null && !isPhone) {

            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

            startDz12StudentList()
        }
    }

    private fun containerAddFragment(containerId: Int, fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()

        transaction.replace(containerId, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun chooseContainer(fragment: Fragment) {
        if (isPhone)
            containerAddFragment(containerOne, fragment)
        else
            containerAddFragment(containerTwo, fragment)
    }

    override fun startDz12StudentList() {
        containerAddFragment(containerOne, Dz12StudentList())

        val transaction = supportFragmentManager.beginTransaction()

        if (supportFragmentManager.findFragmentById(R.id.containerTwo) is Dz12StudentEdit) {
            transaction.remove(supportFragmentManager.findFragmentById(R.id.containerTwo) as Dz12StudentEdit)
            transaction.commit()
        }
    }

    override fun startDz12StudentEdit() {
        chooseContainer(Dz12StudentEdit.getInstance())
    }

    override fun onEditStudentClick(id: Long) {
        chooseContainer(Dz12StudentEdit.getInstance(id))
    }

    override fun onStudentClick(id: Long) {
        chooseContainer(Dz12StudentDetails.getInstance(id))
    }
}