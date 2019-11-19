package com.tjoeun.a20191119_01_banklistpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tjoeun.a20191119_01_banklistpractice.adapters.DeliveryCompanyAdapter
import com.tjoeun.a20191119_01_banklistpractice.datas.DeliveryCompany
import com.tjoeun.a20191119_01_banklistpractice.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_delivery_list.*
import org.json.JSONObject

class DeliveryListActivity : BaseActivity() {

    var dcList = ArrayList<DeliveryCompany>()
    var dcAdapter:DeliveryCompanyAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery_list)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

        getDeliveryCompanyFromServer()

        dcAdapter = DeliveryCompanyAdapter(mContext, dcList)
        deliveryCompanyListView.adapter = dcAdapter


    }

    fun getDeliveryCompanyFromServer() {

        ServerUtil.getRequestDeliveryCompanyList(mContext, object : ServerUtil.JsonResponseHandler {
            override fun onResponse(json: JSONObject) {
                Log.d("택배회사목록응답", json.toString())

                val code = json.getInt("code")

                if (code == 200) {
                    val data = json.getJSONObject("data")
                    val company = data.getJSONArray("company")

                    for (i in 0..company.length() - 1) {
                        val companyObject = company.getJSONObject(i)

                        val companyData = DeliveryCompany.getDeliveryCompanyFromJson(companyObject)
                        dcList.add(companyData)

                    }

                    runOnUiThread {
                        dcAdapter?.notifyDataSetChanged()
                    }

                }


            }


        })

    }

}
