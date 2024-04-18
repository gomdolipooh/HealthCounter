package doyoung.practice.healthcounternew

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import doyoung.practice.healthcounternew.databinding.ActivityRoutineSaveBinding

class RoutineSaveActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRoutineSaveBinding
    private lateinit var loadedExerciseAdapter: LoadedExerciseAdapter
    private lateinit var nameToServer: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoutineSaveBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()

        binding.goToSetting.setOnClickListener {
            finish()
        }

        // TODO
        binding.editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // 변경 전
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // 변경 중
                val text = s?.toString() // Editable을 String으로 변환
                nameToServer = text ?: ""
                // 이후 루틴명 관리 작업 필요
            }

            override fun afterTextChanged(s: Editable?) {
                // 변경 후
            }
        })

        binding.saveButton.setOnClickListener {
            // TODO 루틴 데이터 처리
            showToast("루틴을 저장합니다..")
            startActivity(Intent(this, ViewPagerActivity::class.java))
        }

        // Intent에서 운동 리스트를 추출하여 리사이클러뷰 초기화에 영향
        val exerciseNames = intent.getStringArrayListExtra("exerciseNames")
        val exerciseList = exerciseNames?.map { Exercise(it) }
        loadedExerciseAdapter.setData(exerciseList)

    }

    private fun initRecyclerView() {
        loadedExerciseAdapter = LoadedExerciseAdapter(mutableListOf())
        binding.loadedExerciseRecyclerView.apply {
            adapter = loadedExerciseAdapter
            layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
            val dividerItemDecoration = DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL)
            addItemDecoration(dividerItemDecoration)
        }
    }

    // 토스트 메시지 형식
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}