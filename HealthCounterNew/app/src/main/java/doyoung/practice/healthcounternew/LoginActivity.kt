package doyoung.practice.healthcounternew

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import doyoung.practice.healthcounternew.databinding.ActivityLoginBinding
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

class LoginActivity: AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val callback:(OAuthToken?, Throwable?) -> Unit = { token, error->
        if(error != null) {
            //로그인 실패
            Log.e("loginActivity", "error $error")
            error.printStackTrace()
        } else if(token != null) {
            // 로그인 성공
            Log.e("loginActivity","login with kakao account token: $token")
            sendTokenToServer(token.accessToken)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        KakaoSdk.init(this, "9b1657c07261e9d6039539f5b47404b6")

        binding.testButton.setOnClickListener{
            startActivity(Intent(this, PersonalInfoActivity::class.java))
        }

        binding.kakaoTalkLoginButton.setOnClickListener{
            if(UserApiClient.instance.isKakaoTalkLoginAvailable(this)){
                // 카카오톡 로그인
                UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                    if(error != null) {
                        // 카카오톡 로그인 실패
                        if(error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                            return@loginWithKakaoTalk
                        }

                        UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
                    } else if(token != null) {
                        // 로그인 성공
                        Log.e("loginActivity", "token == $token")
                    }

                }
            } else {
                //카카오계정 로그인
                UserApiClient.instance.loginWithKakaoAccount(this,callback = callback)
            }
        }
    }

    private fun sendTokenToServer(accessToken: String) {
        val client = OkHttpClient()
        val mediaType = "application/json; charset=utf-8".toMediaType()
        val requestBody = "{\"token\": \"$accessToken\"}".toRequestBody(mediaType)


        // TODO
        val request = Request.Builder()
            .url("YOUR_SERVER_URL/authenticate/kakao")
            .post(requestBody)
            .addHeader("Accept", "application/json")
            .build()

        // 요청에 대한 콜백
        val callback = object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("LoginActivity", "Failed to send token to server: ${e.message}")
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    Log.e("Client", "${response.body?.string()}")
                    // TODO 로그인 or 회원 가입 시 상황에 따라 처리
                    val test = response.body?.toString()
                    if(test == "로그인") {
                        // PersonalInfoActivity를 보이지 않고 바로 ViewPagerActivity를 실행
                        Toast.makeText(this@LoginActivity, "오늘도 목표를 향해 열심히 달려보아요!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@LoginActivity, ViewPagerActivity::class.java))
                    } else {
                        // 회원 가입인 경우, PersonalInfoActivity 실행
                        startActivity(Intent(this@LoginActivity, PersonalInfoActivity::class.java))
                    }
                }
            }
        }

        // 요청 보내기
        client.newCall(request).enqueue(callback)
    }
}