package com.md.coronatesting.util

import android.content.Context
import com.md.coronatesting.BaseApp.Companion.instance

/**
 * Created by ogulcan on 07/02/2018.
 */
class Constants {


    companion object {
//        internal lateinit var dbHelper: DBHelper
        const val BASE64 = "MIHNMA0GCSqGSIb3DQEBAQUAA4G7ADCBtwKBrwCzaPWUijeqEXIT/xaoN4GeHhE8IxpfY8rC2f8R4/kOq/dnt2Dai0dgx2/JTMAEFpKXukj1TVpE6lC7akSS4BIOGDjBU1bRr5+ICJd4TUffT/Qx5rWt7z5FtDIclp2J3/hjWd0pT/n9glWr7V2iSZB8h4IN2v9jVCjc+o+xB1mY67/R8rRMm2GBJdGWeAK5TCjW2lX8qNUNITfnAolc/uCj/KpVVuZKBL8XEkoQ/BUCAwEAAQ=="
        const val BASE_URL = "https://corona.lmao.ninja/"
        val warrantyList = listOf("چک","سفته","ضامن بازاری","ضامن کارمند", "سند ملکی")
        val mtWarrantyList = listOf("سند ملکی", "ضامن جواز کسب دار", "ضامن کارمند")
        val garantyGiveList = listOf("ضمانت بانکی","ضمانت دادگاهی")
        val warrantyListReqInvest = listOf("طلا", "سفته", "چک کارمندی", "چک بازاری", "سند خودرو", "سند ملکی")

        fun applicationContext() : Context {
            return instance.applicationContext
        }
    }


}