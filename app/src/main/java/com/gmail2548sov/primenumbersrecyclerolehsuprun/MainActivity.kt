package com.gmail2548sov.primenumbersrecyclerolehsuprun

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var primeNumbers: ArrayList<Int> = ArrayList()
    private lateinit var pnAdapter: PNAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        primeNumbers = savedInstanceState?.getIntegerArrayList("keyprim") ?: ArrayList()

        Log.d("Us4",primeNumbers.toString())
        pnAdapter = PNAdapter(primeNumbers)
        recycler.adapter = pnAdapter
        recycler.layoutManager = LinearLayoutManager(this)

        SingltonPrime.callback = object : SingltonPrime.Callback {
            override fun lastPrimeNumber(number: Int) {
                primeNumbers.add(number)
                pnAdapter.notifyDataSetChanged()
            }
        }

        btnStart.setOnClickListener {
            if (!SingltonPrime.running.get())
                SingltonPrime.findPrimNumber()
        }


        btnStop.setOnClickListener {
            SingltonPrime.stop()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putIntegerArrayList("keyprim", primeNumbers)
    }

    override fun onDestroy() {
        SingltonPrime.callback = null
        super.onDestroy()
    }

    }

