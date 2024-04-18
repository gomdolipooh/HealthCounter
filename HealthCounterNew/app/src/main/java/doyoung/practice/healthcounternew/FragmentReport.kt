package doyoung.practice.healthcounternew

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import doyoung.practice.healthcounternew.databinding.FragmentHomeBinding
import doyoung.practice.healthcounternew.databinding.FragmentReportBinding

class FragmentReport : Fragment() {
    lateinit var binding: FragmentReportBinding
    lateinit var ViewPagerActivity: ViewPagerActivity


    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is ViewPagerActivity) ViewPagerActivity = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReportBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 바인딩 로직
    }
}