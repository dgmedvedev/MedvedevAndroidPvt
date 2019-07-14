package by.itacademy.pvt.dz8

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import by.itacademy.pvt.R

class Dz8FragmentActivity : FragmentActivity(),
    Dz8StudentListFragment.Listener,
    Dz8StudentDetailsFragment.Listener,
    Dz8StudentEditFragment.Listener {

    private val containerOne = R.id.containerOne
    private val containerTwo = R.id.containerTwo

    private val screenSizeCalculator = ScreenSizeCalculator()
    private var screenDiagonal: Double = 0.0

    companion object {
        var isPhone = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_dz8)

        val screenSize = screenSizeCalculator.findSize(this)
        screenDiagonal = resources.getString(R.string.screen_diagonal_in_inches).toDouble()

        if (screenSize >= screenDiagonal) isPhone = false

        if (savedInstanceState == null && isPhone) {

            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

            startDz8StudentListFragment()
        } else if (savedInstanceState == null && !isPhone) {

            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

            startDz8StudentListFragment()
        }
    }

    private fun containerAddFragment(containerId: Int, fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()

        transaction.replace(containerId, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun choiceContainer(fragment: Fragment) {
        if (isPhone)
            containerAddFragment(containerOne, fragment)
        else
            containerAddFragment(containerTwo, fragment)
    }

    override fun startDz8StudentListFragment() {
        containerAddFragment(containerOne, Dz8StudentListFragment())

        val transaction = supportFragmentManager.beginTransaction()

        if (supportFragmentManager.findFragmentById(R.id.containerTwo) is Dz8StudentEditFragment) {
            transaction.remove(supportFragmentManager.findFragmentById(R.id.containerTwo) as Dz8StudentEditFragment)
            transaction.commit()
        }
    }

    override fun startDz8StudentEditFragment() {
        choiceContainer(Dz8StudentEditFragment.getInstance())
    }

    override fun onEditStudentClick(id: Long) {
        choiceContainer(Dz8StudentEditFragment.getInstance(id))
    }

    override fun onStudentClick(id: Long) {
        choiceContainer(Dz8StudentDetailsFragment.getInstance(id))
    }
}