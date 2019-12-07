package com.gmail2548sov.primenumbersrecyclerolehsuprun

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.Validators.or
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var primeNumbers: ArrayList<Int> = ArrayList()
    private lateinit var pnAdapter: PNAdapter
    private var n = 1
    private var isReset = false
    private var reset = 0
    private var fillNumber = 1
    private var startMain = 0
    private var misEnd: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        primeNumbers = savedInstanceState?.getIntegerArrayList("keyprim") ?: ArrayList()
        n = savedInstanceState?.getInt("n")?:1
        isReset = savedInstanceState?.getBoolean("isReset")?:false
        reset = savedInstanceState?.getInt("reset")?:0
        fillNumber = savedInstanceState?.getInt("fillNumber")?:1
        startMain = savedInstanceState?.getInt("startMain")?:0
        misEnd = savedInstanceState?.getBoolean("isEnd")?:false

        if (primeNumbers.isEmpty()) startMain = 0
        fillingSingltonPrime()
        Log.d ("wrong2", percent1.text.toString())
        startMain = 1





        Log.d("Us4",primeNumbers.toString())
        pnAdapter = PNAdapter(primeNumbers)
        recycler.adapter = pnAdapter
        recycler.layoutManager = LinearLayoutManager(this)



        SingltonPrime.callback = object : SingltonPrime.Callback {

            override fun lastPrimeNumber(number: Int, isEnd:Boolean) {

                fillNumber = number
                misEnd = isEnd




                if (!isEnd) {primeNumbers.add(number)
                Log.d("done","${isEnd.toString()}")} else {
                    progressBar.setProgress(100)
                    percent1.text = "100%"

                    Log.d ("done", "${isEnd.toString()} 100%")
                    return}

                pnAdapter.notifyDataSetChanged()

                Log.d ("End", "${isEnd.toString()} $number")


                Log.d ("prs", primeNumbers.size.toString())

                recycler.scrollToPosition(pnAdapter.itemCount-1)

                quality1.text = primeNumbers.size.toString()

                max1.text = primeNumbers[primeNumbers.size-1].toString()
                percent1.text = "${100*number/n}%"
                Log.d ("wrong1", "wrong")
                progressBar.setProgress(100*number/n)

                }
            }




        btnStart.setOnClickListener {
            //if ((input1.text.toString() == "") or ())
               if (input1.text.toString() == "")
                {
                Toast.makeText(
                    this,
                    "Enter a positive number greater than one",
                    Toast.LENGTH_SHORT
                )
                    .show()


            } else
            if (input1.text.toString().toInt() < 2)  {
            Toast.makeText(
                this,
                "Enter a positive number greater than one",
                Toast.LENGTH_SHORT
            )
                .show()



        }else { n = input1.text.toString().toInt()

                Log.d ("runpotok", "${SingltonPrime.running.get()} start")

            if (!SingltonPrime.running.get())
                SingltonPrime.findPrimNumber(n,isReset)}
            isReset = false
            reset = 0
        }




        btnStop.setOnClickListener {

            SingltonPrime.stop()
            Log.d ("runpotok", "${SingltonPrime.running.get()} stop")
            reset +=1



            if ((reset==2) or (misEnd)) {isReset = true
                clearSingltonPrime()
                reset = 0}

        }
    }

    fun fillingSingltonPrime() {

        when (startMain){
            0 -> { quality1.text = ""
                max1.text = ""
                percent1.text = ""
                progressBar.setProgress(0)}
            1-> {quality1.text = primeNumbers.size.toString()
                max1.text = primeNumbers[primeNumbers.size-1].toString()
                percent1.text = "${100*fillNumber/n}%"
                Log.d ("wrong2", "${100*fillNumber/n}% 555")
                progressBar.setProgress(100*fillNumber/n)}


        }

        if (misEnd) percent1.text = "100%"




    }

    fun clearSingltonPrime() {
        n = 1
        primeNumbers.clear()
        quality1.text = ""
        max1.text = ""
        percent1.text = ""
        progressBar.setProgress(0)
        recycler.clearOnScrollListeners()
        input1.setText("")
        input1.hint = "Input value:"
        Log.d ("hint", input1.hint.toString())


    }



    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putIntegerArrayList("keyprim", primeNumbers)
        outState.putInt("n", n)
        outState.putBoolean("isReset", isReset)
        outState.putInt("reset", reset)
        outState.putInt("fillNumber", fillNumber)
        outState.putInt("startMain", startMain)
        outState.putBoolean("isEnd", misEnd)
    }

    override fun onDestroy() {

        SingltonPrime.callback = null
        super.onDestroy()
    }
}


