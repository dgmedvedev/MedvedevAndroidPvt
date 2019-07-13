package by.itacademy.pvt.dz8

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import by.itacademy.pvt.R

class Dz8FragmentActivity : FragmentActivity(),
    Dz8StudentListFragment.Listener,
    Dz8StudentDetailsFragment.Listener,
    Dz8StudentEditFragment.Listener {

    private val screenSizeCalculator = ScreenSizeCalculator()
    private var screenDiagonal: Double = 0.0
    private var isPhone = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_dz8)

        val screenSize = screenSizeCalculator.findSize(this)
        screenDiagonal = resources.getString(R.string.screen_diagonal_in_inches).toDouble()

        if (screenSize >= screenDiagonal) isPhone = false

        if (savedInstanceState == null) {

            val studentListFragment = Dz8StudentListFragment()
            val container = R.id.containerActivity

            changeFragment(container, studentListFragment)
        }
    }

    private fun changeFragment(containerId: Int, fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()

        transaction.replace(containerId, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun startDz8StudentListFragment() {
        changeFragment(R.id.containerActivity, Dz8StudentListFragment())
    }

    override fun startDz8StudentEditFragment() {
        changeFragment(R.id.containerActivity, Dz8StudentEditFragment.getInstance())
    }

    override fun onEditStudentClick(id: Long) {
        changeFragment(R.id.containerActivity, Dz8StudentEditFragment.getInstance(id))
    }

    override fun onStudentClick(id: Long) {
        changeFragment(R.id.containerActivity, Dz8StudentDetailsFragment.getInstance(id))
    }
}