package kr.co.tjoeun.pizzastore_20200714

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import kotlinx.android.synthetic.main.activity_view_pizza_store_detail.*
import kr.co.tjoeun.pizzastore_20200714.datas.PizzaStore
import java.security.Permission
import java.util.jar.Manifest

class ViewPizzaStoreDetailActivity : BaseActivity() {

    // 받아오는 피자가게 정보를 담아주기 위한 맴버변수
    //setupEvent / setVaLues

    lateinit var mPizzaStore: PizzaStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pizza_store_detail)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        //전화 주문 버튼을 누르면 실제로 전화 연결하기
        callBtn.setOnClickListener {

            //안드로이드가 제공하는 화면으로 Intent
            // Call은 권한 처리도 필요한 (TedPermission사용)
            //권한 허가 여부에 따른 행동 요령 가이드
            val pl = object : PermissionListener {
                override fun onPermissionGranted() {
                    val myUri = Uri.parse("tel:${mPizzaStore.phoneNum}")

                    val myIntent = Intent(Intent.ACTION_CALL, myUri)
                    startActivity(myIntent)
                }

                override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                    Toast.makeText(mContext,"권한 승인이 되지 않아 전화 주문을 할 수 없습니다.",Toast.LENGTH_SHORT).show()
                }

            }

            TedPermission.with(mContext)
                .setPermissions(android.Manifest.permission.CALL_PHONE)
                .setDeniedMessage("[설정]에서 전화 권한을 승인해줘야 사용 가능 합니다.")
                .setPermissionListener(pl)
                .check()


        }

    }

    override fun setValues() {
        //맴버변수로 있는 피자가게 변수를 intent 변수에서 반아온 가게로 대입
        mPizzaStore = intent.getSerializableExtra("pizzaStore") as PizzaStore

        //피자가게변술르 통해 화면 데이터 세팅
        storeNameTxt.text = mPizzaStore.name
        phoneNumTxt.text = mPizzaStore.phoneNum

        Glide.with(mContext).load(mPizzaStore.logoUrl).into(logoImg)

    }


}