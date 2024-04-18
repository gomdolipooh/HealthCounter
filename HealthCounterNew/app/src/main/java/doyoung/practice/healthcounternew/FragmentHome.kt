package doyoung.practice.healthcounternew

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import doyoung.practice.healthcounternew.databinding.FragmentFoodBinding
import doyoung.practice.healthcounternew.databinding.FragmentHomeBinding

class FragmentHome : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var ViewPagerActivity: ViewPagerActivity


    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is ViewPagerActivity) ViewPagerActivity = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 바인딩 로직
        // 블루투스 레이어 선언
        val bluetoothSwitch = binding.bluetoothSwitchButton
        val bluetoothLayer = binding.bluetoothLayer
        val bluetoothImage1 = binding.bluetoothOffButton
        val bluetoothImage2 = binding.bluetoothOnButton

        // 블루투스 레이어 클릭에 따른 이미지 visibility 설정
        // 초기 상태에 따른 이미지 설정 (Off)
        setBluetoothImageVisibility(bluetoothSwitch.isChecked)

        // TODO 블루투스 로직 작성
        bluetoothSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            setBluetoothImageVisibility(isChecked)
            if(isChecked) {
                // 블루투스 On
            } else {
                // 블루투스 Off
            }
        }

        bluetoothLayer.setOnClickListener {
            // 스위치 상태 변경
            bluetoothSwitch.isChecked = !bluetoothSwitch.isChecked
        }

        // 캘린더 클릭 시, 세부 페이지로의 이동
        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val selectedDate = "${month+1}월 ${dayOfMonth}일"
            val intent = Intent(context, CalendarDetailActivity::class.java)
            intent.putExtra("selectedDate", selectedDate)
            startActivity(intent)
        }
    }

    private fun setBluetoothImageVisibility(isBluetoothOn: Boolean) {
        if(isBluetoothOn) {
            binding.bluetoothOnButton.visibility = View.VISIBLE
            binding.bluetoothOffButton.visibility = View.INVISIBLE
        } else {
            binding.bluetoothOnButton.visibility = View.INVISIBLE
            binding.bluetoothOffButton.visibility = View.VISIBLE
        }
    }

    // 토스트 메시지 형식
    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}