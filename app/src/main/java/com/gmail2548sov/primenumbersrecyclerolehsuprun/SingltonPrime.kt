

package com.gmail2548sov.primenumbersrecyclerolehsuprun

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.annotation.MainThread
import java.util.concurrent.atomic.AtomicBoolean

object SingltonPrime {

    private var thread: Thread? = null
    //

    private var last = 3

    private val n: Int = 500000

    //private var ozn: Int = 0

    var running: AtomicBoolean = AtomicBoolean(false)

    var callback: Callback? = null

    interface Callback {
        @MainThread
        fun lastPrimeNumber(number: Int)

    }


    fun findPrimNumber() {

        val handler = Handler(Looper.getMainLooper())
        val runnable = Runnable {
            running.set(true)
            while (running.get()) {
                try {


                    /*for (i in lastPrimeNumber + 1..to) {
                        var isPrimal = false
                        for (j in 2 until i) {
                            isPrimal = i % j != 0
                            if (!isPrimal) break
                        }
                        if (isPrimal) {
                            lastPrimeNumber = i
                            Log.d("Us2", i.toString())
                            handler.post {callback?.lastPrimeNumber(i)  }
                            Thread.sleep(500)
                        }
                    }*/


                    if (last ==3) {handler.post {callback?.lastPrimeNumber(2) }
                        Thread.sleep(500)}


                    for (i in last..n step 2) {

                        var ozn = 0

                        for (j in 3..i step 2) {

                            if (j * j - 1 < i) {
                                if (i % j == 0) {
                                    ozn = 1
                                    break
                                }
                            }
                        }

                            if (ozn == 0) {
                                Log.d("Us1", i.toString())
                                last=i+2

                                handler.post {callback?.lastPrimeNumber(i) }

                                Thread.sleep(500)
                            }
                        }

                } catch (e: InterruptedException) {
                    break
                }

            }
        }
        thread = Thread(runnable)
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







