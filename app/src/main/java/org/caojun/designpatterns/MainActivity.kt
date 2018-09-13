package org.caojun.designpatterns

import android.app.Activity
import android.app.KeyguardManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import org.caojun.designpatterns.adapter.SectionsPagerAdapter

class MainActivity : AppCompatActivity() {

    /**
     * The [android.support.v4.view.PagerAdapter] that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * [android.support.v4.app.FragmentStatePagerAdapter].
     */
    private lateinit var mSectionsPagerAdapter: SectionsPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(applicationContext, supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        container.adapter = mSectionsPagerAdapter

//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//        }

        container.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(position: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
//                val title = mSectionsPagerAdapter.getPageTitle(position)
////                toolbar.title = title
//                setTitle(title, position, mSectionsPagerAdapter.count)

                this@MainActivity.position = position
                if (position > 1 && !isLoggedIn) {
                    //查看正文，需先跳转系统锁界面
                    if (!showAuthenticationScreen()) {
                        isLoggedIn = true
                        showViewPager(position)
                    }
                    return
                } else {
                    showViewPager(position)
                }
                positionLast = position
            }
        })
//        toolbar.title = mSectionsPagerAdapter?.getPageTitle(0)
        setTitle(mSectionsPagerAdapter.getPageTitle(0), 0, mSectionsPagerAdapter.count)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onBackPressed() {
        when (container.currentItem) {
            0 -> super.onBackPressed()
            1 -> container.setCurrentItem(0, true)
            else -> container.setCurrentItem(1, true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        if (id == R.id.action_settings) {
            container.setCurrentItem(1, true)
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    fun setCurrentItem(position: Int) {
        container.setCurrentItem(position, true)
    }

    private fun setTitle(title: CharSequence, position: Int, size: Int) {
        toolbar.title = "(${position + 1}/$size)$title"
    }

    //看正文需先跳转系统锁界面
    companion object {
        const val REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS = 1
    }
    private var isLoggedIn = false
    private var position = 0
    private var positionLast = 0
    private fun showAuthenticationScreen(): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return false
        }
        val mKeyguardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
        if (!mKeyguardManager.isKeyguardSecure) {
            return false
        }
        val intent = mKeyguardManager.createConfirmDeviceCredentialIntent(null, null)
        if (intent != null) {
            startActivityForResult(intent, REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS)
            return true
        }
        return false
    }

    private fun showViewPager(position: Int) {
        val title = mSectionsPagerAdapter.getPageTitle(position)
        setTitle(title, position, mSectionsPagerAdapter.count)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS) {
            if (resultCode == Activity.RESULT_OK) {
                isLoggedIn = true
                showViewPager(position)
            } else {
                isLoggedIn = false
                container.setCurrentItem(positionLast, true)
            }
            return
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
