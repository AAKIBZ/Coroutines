package com.example.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.system.measureNanoTime

class MainActivity : AppCompatActivity() {
    val TAG = "Main Activity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch {

            val time = measureNanoTime {
                ///async is used to call two or more network operations simaltaneously
                val answer1 = async { doNetworkCall1() }
                val answer2 = async { doNetworkCall2() }
                   ///await blocks the current thread(here its is GlobalScope thread) to complete the async code
                Log.d(TAG, "answer 1 is ${answer1.await()}")
                Log.d(TAG, "answer 12is ${answer2.await()}")
            }
            Log.d(TAG, "Time Taken is $time")

        }


//        val job = GlobalScope.launch(Dispatchers.Default) {
//            /// the function will automatically cancel coroutine after the time specified
//            withTimeout(2000) {
//                repeat(2) {
//                    Log.d(TAG, "Repeated ......")
//                }
//                Log.d(TAG, "Ended ......")
//            }
//
//
//        }


        /// blocks the main thread
//        runBlocking {
//            job.join()
//            Log.d(TAG, "Main Thread is continuing.......")
//        }

//        GlobalScope.launch(Dispatchers.IO) {
//            Log.v(TAG, "Starting from the thread  ${Thread.currentThread().name}")
//            val networkcall1 = doNetworkCall()
//            Log.d(TAG, networkcall1)
//            withContext(Dispatchers.Main) {
//                Log.v(TAG, "Inside Thread from the thread ${Thread.currentThread().name}")
//                val data = doNetworkCall()
//                tvDummy.text = data
//            }
//        }
    }

    suspend fun doNetworkCall1(): String {
        delay(2000)

        return " answer 1 is here"
    }

    suspend fun doNetworkCall2(): String {
        delay(2000)
        return " answer 2 is here"
    }


}