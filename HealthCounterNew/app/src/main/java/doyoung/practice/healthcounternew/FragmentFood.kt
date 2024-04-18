package doyoung.practice.healthcounternew

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import doyoung.practice.healthcounternew.databinding.FragmentExerciseBinding
import doyoung.practice.healthcounternew.databinding.FragmentFoodBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentFood : Fragment() {

    // TODO down/left ArrowButton 및 각각의 리사이클러뷰 설정
    // TODO 페이지 로드 시 하위에 보일 칼로리 데이터 관리 로직 작성

    lateinit var binding: FragmentFoodBinding
    lateinit var ViewPagerActivity: ViewPagerActivity


    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is ViewPagerActivity) ViewPagerActivity = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFoodBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 바인딩 로직
        // 각각의 추가 버튼 클릭 시의 로직
        binding.breakfastAddButton.setOnClickListener {
            val intent = Intent(context, CalorieActivity::class.java)
            intent.putExtra("whatFood", "아침")
            startActivity(intent)
        }

        binding.lunchAddButton.setOnClickListener{
            val intent = Intent(context, CalorieActivity::class.java)
            intent.putExtra("whatFood", "점심")
            startActivity(intent)
        }

        binding.dinnerAddButton.setOnClickListener {
            val intent = Intent(context, CalorieActivity::class.java)
            intent.putExtra("whatFood", "저녁")
            startActivity(intent)
        }

        binding.downArrowButton1.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val foodDao = AppDatabase.getInstance(requireContext())?.foodDao()

                // kind가 "아침"인 메뉴들을 가져옵니다.
                val breakfastMenus = foodDao?.getFormattedBreakfastMenus()

                // kind가 "아침"인 메뉴들의 총 칼로리를 가져옵니다.
                val totalCaloriesForBreakfast = foodDao?.getTotalCaloriesForBreakfast()

                // 반환 값이 null이 아니라면 계속 진행
                totalCaloriesForBreakfast?.let { totalCalories ->
                    // 메인 스레드에서 UI를 업데이트합니다.
                    withContext(Dispatchers.Main) {
                        val totalCaloriesString = totalCalories.toString()
                        binding.breakfastCalorieTextView.text = "총 섭취량: ${totalCaloriesString} kcal"

                        // breakfastCalorieTextView의 텍스트를 업데이트합니다.
                        binding.breakfastMenuTextView.text = breakfastMenus.orEmpty()

                        if (binding.breakfastCalorieTextView.text != "아직") {
                            with(binding) {
                                downArrowButton1.visibility = View.INVISIBLE
                                leftArrowButton1.visibility = View.VISIBLE
                                breakfastCalorieTextView.visibility = View.VISIBLE
                                breakfastMenuTextView.visibility = View.VISIBLE
                            }
                        }
                    }
                }
            }
        }

        binding.leftArrowButton1.setOnClickListener {
            with(binding) {
                breakfastCalorieTextView.visibility = View.GONE
                breakfastMenuTextView.visibility = View.GONE
                leftArrowButton1.visibility = View.INVISIBLE
                downArrowButton1.visibility = View.VISIBLE
            }
        }

        binding.downArrowButton1.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val foodDao = AppDatabase.getInstance(requireContext())?.foodDao()

                // kind가 "아침"인 메뉴들을 가져옵니다.
                val breakfastMenus = foodDao?.getFormattedBreakfastMenus()

                // kind가 "아침"인 메뉴들의 총 칼로리를 가져옵니다.
                val totalCaloriesForBreakfast = foodDao?.getTotalCaloriesForBreakfast()

                // 반환 값이 null이 아니라면 계속 진행
                totalCaloriesForBreakfast?.let { totalCalories ->
                    // 메인 스레드에서 UI를 업데이트합니다.
                    withContext(Dispatchers.Main) {
                        val totalCaloriesString = totalCalories.toString()
                        binding.breakfastCalorieTextView.text = "총 섭취량: ${totalCaloriesString} kcal"

                        // breakfastCalorieTextView의 텍스트를 업데이트합니다.
                        binding.breakfastMenuTextView.text = breakfastMenus.orEmpty()

                        if (binding.breakfastCalorieTextView.text != "아직") {
                            with(binding) {
                                downArrowButton1.visibility = View.INVISIBLE
                                leftArrowButton1.visibility = View.VISIBLE
                                breakfastCalorieTextView.visibility = View.VISIBLE
                                breakfastMenuTextView.visibility = View.VISIBLE
                            }
                        }
                    }
                }
            }
        }

        binding.leftArrowButton1.setOnClickListener {
            with(binding) {
                breakfastCalorieTextView.visibility = View.GONE
                breakfastMenuTextView.visibility = View.GONE
                leftArrowButton1.visibility = View.INVISIBLE
                downArrowButton1.visibility = View.VISIBLE
            }
        }

        binding.downArrowButton2.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val foodDao = AppDatabase.getInstance(requireContext())?.foodDao()

                // kind가 "점심"인 메뉴들을 가져옵니다.
                val lunchMenus = foodDao?.getFormattedLunchMenus()

                // kind가 "점심"인 메뉴들의 총 칼로리를 가져옵니다.
                val totalCaloriesForLunch = foodDao?.getTotalCaloriesForLunch()

                // 반환 값이 null이 아니라면 계속 진행
                totalCaloriesForLunch?.let { totalCalories ->
                    // 메인 스레드에서 UI를 업데이트합니다.
                    withContext(Dispatchers.Main) {
                        val totalCaloriesString = totalCalories.toString()
                        binding.lunchCalorieTextView.text = "총 섭취량: ${totalCaloriesString} kcal"

                        // breakfastCalorieTextView의 텍스트를 업데이트합니다.
                        binding.lunchMenuTextView.text = lunchMenus.orEmpty()

                        if (binding.lunchCalorieTextView.text != "아직") {
                            with(binding) {
                                downArrowButton2.visibility = View.INVISIBLE
                                leftArrowButton2.visibility = View.VISIBLE
                                lunchCalorieTextView.visibility = View.VISIBLE
                                lunchMenuTextView.visibility = View.VISIBLE
                            }
                        }
                    }
                }
            }
        }

        binding.leftArrowButton2.setOnClickListener {
            with(binding) {
                lunchCalorieTextView.visibility = View.GONE
                lunchMenuTextView.visibility = View.GONE
                leftArrowButton2.visibility = View.INVISIBLE
                downArrowButton2.visibility = View.VISIBLE
            }
        }

        binding.downArrowButton3.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val foodDao = AppDatabase.getInstance(requireContext())?.foodDao()

                // kind가 "저녁"인 메뉴들을 가져옵니다.
                val dinnerMenus = foodDao?.getFormattedDinnerMenus()

                // kind가 "저녁"인 메뉴들의 총 칼로리를 가져옵니다.
                val totalCaloriesForDinner = foodDao?.getTotalCaloriesForDinner()

                // 반환 값이 null이 아니라면 계속 진행
                totalCaloriesForDinner?.let { totalCalories ->
                    // 메인 스레드에서 UI를 업데이트합니다.
                    withContext(Dispatchers.Main) {
                        val totalCaloriesString = totalCalories.toString()
                        binding.dinnerCalorieTextView.text = "총 섭취량: ${totalCaloriesString} kcal"

                        // breakfastCalorieTextView의 텍스트를 업데이트합니다.
                        binding.dinnerMenuTextView.text = dinnerMenus.orEmpty()

                        if (binding.dinnerCalorieTextView.text != "아직") {
                            with(binding) {
                                downArrowButton3.visibility = View.INVISIBLE
                                leftArrowButton3.visibility = View.VISIBLE
                                dinnerCalorieTextView.visibility = View.VISIBLE
                                dinnerMenuTextView.visibility = View.VISIBLE
                            }
                        }
                    }
                }
            }
        }

        binding.leftArrowButton3.setOnClickListener {
            with(binding) {
                dinnerCalorieTextView.visibility = View.GONE
                dinnerMenuTextView.visibility = View.GONE
                leftArrowButton3.visibility = View.INVISIBLE
                downArrowButton3.visibility = View.VISIBLE
            }
        }
    }
}