

package com.gmail2548sov.primenumbersrecyclerolehsuprun

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.annotation.MainThread
import com.gmail2548sov.primenumbersrecyclerolehsuprun.SingltonPrime.running
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicBoolean

object SingltonPrime {

    private var thread: Thread? = null



    //

    private var last = 3

    private var n: Int = 2
    private var mmm: Int = 2

    private var isEnd: Boolean = false


    //private var ozn: Int = 0

    var running: AtomicBoolean = AtomicBoolean(false)

    var callback: Callback? = null

    interface Callback {
        @MainThread
        fun lastPrimeNumber(number: Int, isEnd: Boolean)

    }


    fun findPrimNumber(m: Int, isClear: Boolean) {
        n = m
        if (isClear) last = 3


        Log.d("find", "find")

        val handler = Handler(Looper.getMainLooper())

        // HARD TASK/////////////////////////////////////////////////////////////////////////

        val primeTask = Runnable {
            running.set(true)
            while (running.get()) {
                try {


                    Log.d ("start", "start")

                    if (last == 3) {
                        handler.post { callback?.lastPrimeNumber(2, false) }
                        Thread.sleep(50)
                    }

                    for (i in last..n step 2) {

                        var oznaka = 0

                        for (j in 3..i step 2) {

                            if (j * j - 1 < i) {
                                if (i % j == 0) {
                                    oznaka = 1
                                    break
                                }
                            }
                        }

                        if (oznaka == 0) {
                            Log.d("Us1", i.toString())
                            last = i + 2

                            handler.post { callback?.lastPrimeNumber(i, false) }
                            Log.d ("done", "${isEnd.toString()} 555")

                             Thread.sleep(20)

                        }
                        Log.d ("runpotok", "${SingltonPrime.running.get()} start2")
                    }



                    isEnd = true
                    handler.post {  callback?.lastPrimeNumber(0,true)}
                    Log.d ("done", "${isEnd.toString()} 55775")
                    return@Runnable



                } catch (e: InterruptedException) {
                    break
                }




            }

        }
        thread = Thread(primeTask)
        thread?.start()



       }


        fun stop() {
            running.set(false)
            if (thread != null) {
                val dummy = thread
                thread = null
                dummy?.interrupt()



            }
        }
    }









