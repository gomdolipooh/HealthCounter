package doyoung.practice.healthcounternew

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import doyoung.practice.healthcounternew.databinding.ActivityExerciseSelectBinding

class ExerciseSelectActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExerciseSelectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseSelectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goToSetting.setOnClickListener {
            finish()
        }


        val fragmentList =
            listOf(FragmentChest(),FragmentShoulder(),FragmentLeg(),FragmentBack())
        val pagerAdapter = SelectPagerAdapter(fragmentList, this)
        binding.selectViewPager.adapter = pagerAdapter
        val tabList = listOf("가슴", "어깨", "하체", "등")

        TabLayoutMediator(binding.tabLayout, binding.selectViewPager) { tab, position ->
            tab.text = tabList[position]
        }.attach()
    }

    class SelectPagerAdapter(val fragmentList: List<Fragment>, fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount() = fragmentList.size

        override fun createFragment(position: Int) = fragmentList[position]
    }

    fun handleExerciseAddButtonClick(selectedExerciseName: String) {
        Toast.makeText(this, "종목을 추가했습니다.", Toast.LENGTH_SHORT).show()
        intent.putExtra("exerciseName", selectedExerciseName)
        setResult(RESULT_OK, intent)
        finish()
    }
}