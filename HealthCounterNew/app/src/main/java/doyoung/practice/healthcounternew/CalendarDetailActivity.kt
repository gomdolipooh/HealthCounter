package doyoung.practice.healthcounternew

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.lifecycle.lifecycleScope
import doyoung.practice.healthcounternew.databinding.ActivityCalendarDetailBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CalendarDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalendarDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalendarDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 상단의 날짜 변경
        val selectedDate = intent.getStringExtra("selectedDate")
        binding.dateTextView.text = selectedDate

        // 상단의 뒤로가기 버튼을 누를 경우, 이전 페이지로 되돌아가기
        binding.goToHome.setOnClickListener {
            finish()
        }

        // 드롭다운 <운동 일지>
        binding.dropDownLayer.setOnClickListener {
            if(binding.downArrowButton.visibility == View.INVISIBLE) {
                binding.rightArrowButton.visibility = View.INVISIBLE
                binding.downArrowButton.visibility = View.VISIBLE
                binding.exerciseLogLayer.visibility = View.VISIBLE
                // TODO 서버와의 통신을 통한 exerciseLogLayer 내부 요소 초기화 (ex) Retrofit 통신)
            } else {
                binding.rightArrowButton.visibility = View.VISIBLE
                binding.downArrowButton.visibility = View.INVISIBLE
                binding.exerciseLogLayer.visibility = View.GONE
            }
        }

        // 드롭다운 <식단 일지>
        binding.dropDownLayer2.setOnClickListener {
            if(binding.downArrowButton2.visibility == View.INVISIBLE) {
                binding.rightArrowButton2.visibility = View.INVISIBLE
                binding.downArrowButton2.visibility = View.VISIBLE
                binding.foodLogLayer.visibility = View.VISIBLE
                // TODO 서버와의 통신을 통한 exerciseLogLayer 내부 요소 초기화 (ex) Retrofit 통신)
            } else {
                binding.rightArrowButton2.visibility = View.VISIBLE
                binding.downArrowButton2.visibility = View.INVISIBLE
                binding.foodLogLayer.visibility = View.GONE
            }
        }
    }
}