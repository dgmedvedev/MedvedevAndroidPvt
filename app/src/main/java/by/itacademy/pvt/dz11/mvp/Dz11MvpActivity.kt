package by.itacademy.pvt.dz11.mvp

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import by.itacademy.pvt.R

class Dz11MvpActivity : FragmentActivity(),
    Dz11StudentListFragment.Listener,
    Dz11StudentDetailsFragment.Listener,
    Dz11StudentEditFragment.Listener {

    private val containerOne = R.id.containerOne
    private val containerTwo = R.id.containerTwo

    private var isPhone = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_dz8)

        isPhone = (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)

        if (savedInstanceState == null && isPhone) {

            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

            startDz11StudentListFragment()
        } else if (savedInstanceState == null && !isPhone) {

            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

            startDz11StudentListFragment()
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

    override fun startDz11StudentListFragment() {
        containerAddFragment(containerOne, Dz11StudentListFragment())

        val transaction = supportFragmentManager.beginTransaction()

        if (supportFragmentManager.findFragmentById(R.id.containerTwo) is Dz11StudentEditFragment) {
            transaction.remove(supportFragmentManager.findFragmentById(R.id.containerTwo) as Dz11StudentEditFragment)
            transaction.commit()
        }
    }

    override fun startDz11StudentEditFragment() {
        chooseContainer(Dz11StudentEditFragment.getInstance())
    }

    override fun onEditStudentClick(id: Long) {
        chooseContainer(Dz11StudentEditFragment.getInstance(id))
    }

    override fun onStudentClick(id: Long) {
        chooseContainer(Dz11StudentDetailsFragment.getInstance(id))
    }
}