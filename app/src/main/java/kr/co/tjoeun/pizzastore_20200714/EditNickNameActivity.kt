package kr.co.tjoeun.pizzastore_20200714

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_edit_nick_name.*

class EditNickNameActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_nick_name)


    }
    override fun setupEvents() {

        okBtn.setOnClickListener {
            //입력한 닉네임을
            val inputNickName = newNickNameEdt.text.toString()

            val resultInet = Intent()
            resultInet.putExtra("nickName", inputNickName)

            setResult(Activity.RESULT_OK,resultInet)
            finish()

        }

    }

    override fun setValues() {

    }


}