package doyoung.practice.healthcounternew

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import doyoung.practice.healthcounternew.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 스피너 초기화
        val spinner = binding.exerciseAmountSpinner
        val exerciseAmountChoices = resources.getStringArray(R.array.activity_choices)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, exerciseAmountChoices)
        spinner.adapter = adapter

        // 저장 플로팅 버튼 동작
        binding.saveButton.setOnClickListener {
            saveData()
            finish()
        }


    }

    // 성별/체중/신장/운동 목표/평소 운동량 저장
    private fun saveData() {
        with(getSharedPreferences(USER_INFORMATION, Context.MODE_PRIVATE).edit()) {
            putString(SEX, getSexType())
            putString(WEIGHT, getNumericValue(binding.weightEditText.text.toString()))
            putString(HEIGHT, getNumericValue(binding.heightEditText.text.toString()))
            putString(GOAL, getGoal())
            putString(EXERCISEAMOUNT, getExerciseAmount())
            apply()
        }
        Toast.makeText(this, "저장을 완료했습니다.", Toast.LENGTH_SHORT).show()
    }

    private fun getNumericValue(input: String): String {
        val regex = Regex("[0-9]+")
        val matchResult = regex.find(input)
        return matchResult?.value ?: ""
    }

    private fun getSexType(): String {
        val selectedSexId = binding.sexRadioGroup.checkedRadioButtonId
        val selectedSex = findViewById<RadioButton>(selectedSexId)
        return selectedSex.text.toString()
    }

    private fun getExerciseAmount(): String {
        val exerciseAmount = binding.exerciseAmountSpinner.selectedItem.toString()
        val index = exerciseAmount.indexOf("(")
        return if (index != -1) {
            exerciseAmount.substring(0, index)
        } else {
            exerciseAmount
        }
    }

    private fun getGoal(): String {
        val selectedGoalId = binding.goalRadioGroup.checkedRadioButtonId
        val selectedGoal = findViewById<RadioButton>(selectedGoalId)
        return selectedGoal.text.toString()
    }

}