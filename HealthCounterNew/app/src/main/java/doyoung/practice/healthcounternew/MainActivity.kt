package doyoung.practice.healthcounternew

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kakao.sdk.common.util.Utility
import doyoung.practice.healthcounternew.databinding.ActivityMainBinding
import doyoung.practice.healthcounternew.databinding.ActivityViewPagerBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)


        val keyHash = Utility.getKeyHash(this)
        Log.e("keyhash", keyHash.toString())

        startActivity(Intent(this, LoginActivity::class.java))
    }
}