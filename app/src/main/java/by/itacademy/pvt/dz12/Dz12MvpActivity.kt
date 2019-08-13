package by.itacademy.pvt.dz12

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import by.itacademy.pvt.R
import by.itacademy.pvt.dz12.mvp.Dz12StudentDetailsFragment
import by.itacademy.pvt.dz12.mvp.Dz12StudentEditFragment
import by.itacademy.pvt.dz12.mvp.Dz12StudentListFragment

class Dz12MvpActivity : FragmentActivity(),
    Dz12StudentListFragment.Listener,
    Dz12StudentDetailsFragment.Listener,
    Dz12StudentEditFragment.Listener {

    private val containerOne = R.id.containerOne
    private val containerTwo = R.id.containerTwo

    private var isPhone = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_dz8)

        isPhone = (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)

        if (savedInstanceState == null && isPhone) {

            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

            startDz12StudentListFragment()
        } else if (savedInstanceState == null && !isPhone) {

            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

            startDz12StudentListFragment()
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

    override fun startDz12StudentListFragment() {
        containerAddFragment(containerOne, Dz12StudentListFragment())

        val transaction = supportFragmentManager.beginTransaction()

        if (supportFragmentManager.findFragmentById(R.id.containerTwo) is Dz12StudentEditFragment) {
            transaction.remove(supportFragmentManager.findFragmentById(R.id.containerTwo) as Dz12StudentEditFragment)
            transaction.commit()
        }
    }

    override fun startDz12StudentEditFragment() {
        chooseContainer(Dz12StudentEditFragment.getInstance())
    }

    override fun onEditStudentClick(id: String) {
        chooseContainer(Dz12StudentEditFragment.getInstance(id))
    }

    override fun onStudentClick(id: String) {
        chooseContainer(Dz12StudentDetailsFragment.getInstance(id))
    }
}