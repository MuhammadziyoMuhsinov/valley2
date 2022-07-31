package uz.muhammadziyo.homework312

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.VolleyLog
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import uz.muhammadziyo.homework312.adapter.RvAdapter
import uz.muhammadziyo.homework312.adapter.RvClick
import uz.muhammadziyo.homework312.databinding.ActivityMainBinding
import uz.muhammadziyo.homework312.databinding.ItemDialogBinding
import uz.muhammadziyo.homework312.models.User

class MainActivity : AppCompatActivity() {
    private lateinit var requestQueue: RequestQueue
    private lateinit var binding: ActivityMainBinding
    private lateinit var userAdapter: RvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        requestQueue = Volley.newRequestQueue(this)

        loadList()
    }
    private fun loadList() {
        VolleyLog.DEBUG = true
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET,
            "http://cbu.uz/uzc/arkhiv-kursov-valyut/json/",
            null,
            {
                val type = object : TypeToken<ArrayList<User>>() {}.type
                val list = Gson().fromJson<ArrayList<User>>(it.toString(),type)
                userAdapter = RvAdapter(list, object : RvClick{
                    @SuppressLint("SetTextI18n")
                    override fun onClick(user: User) {

                        val button = BottomSheetDialog(this@MainActivity)
                        val itemDialogBinding = ItemDialogBinding.inflate(layoutInflater)
                        button.setContentView(itemDialogBinding.root)
                        button.show()
                        itemDialogBinding.ccynmru.text = "CcyNm_RU: \n  ${user.CcyNm_RU} "
                        itemDialogBinding.ccynmuz.text = "CcyNm_UZ: \n ${user.CcyNm_UZ} "
                        itemDialogBinding.ccynmuzc.text = "CcyNm_UZC: \n ${user.CcyNm_UZC}"
                        itemDialogBinding.code.text = "Code: \n ${user.Code}"
                        itemDialogBinding.date.text = "Date: \n ${user.Date}"
                        itemDialogBinding.diff.text = "Diff: \n ${user.Diff}"
                        itemDialogBinding.nomilan.text = "Nominal: \n ${user.Nominal}"
                        itemDialogBinding.rate.text = "Rate: \n ${user.Rate}"
                        itemDialogBinding.id.text = "id: \n ${user.id}"

                    }
                })
                binding.rv.adapter = userAdapter
            },
            {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }


        )
        requestQueue.add(jsonArrayRequest)

    }
}