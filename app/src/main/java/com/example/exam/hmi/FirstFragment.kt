package com.example.exam.hmi

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.exam.R
import com.example.exam.bean.Data
import com.example.exam.databinding.FragmentFirstBinding
import com.example.exam.model.MyViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import java.text.DecimalFormat

class FirstFragment : Fragment() {

    companion object {
        fun newInstance() = FirstFragment()
    }

    private lateinit var viewModel: MyViewModel
    private lateinit var bean: Data
    private var mAmount: Double = 0.0
    private var mTime: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this.requireActivity()).get(MyViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentFirstBinding>(
            inflater,
            R.layout.fragment_first,
            container,
            false
        )
        binding.lifecycleOwner = this
        bean = Data()
        binding.databean = bean
        initView(binding)
        return binding.root
    }

    private fun initView(binding: FragmentFirstBinding) {
        binding.etAmount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                runBlocking {
                    p0.let {
                        flowOf(p0.toString()).map {
                            mAmount = it.toDouble()
                        }.map {
                            val dec = DecimalFormat("#,###,###.##")
                            dec.format(mAmount)
                        }.catch {
                            bean.amount.set("")
                        }.collect {
                            bean.amount.set(it)
                        }
                    }
                }
            }
        })
        binding.etTime.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                runBlocking {
                    p0.let {
                        flowOf(p0.toString()).map {
                            mTime = if(it.isEmpty()){
                                -1
                            }else{
                                it.toInt()
                            }
                            handleTime(mTime)
                        }.catch {
                        }.collect {
                            bean.time.set(it)
                        }
                    }

                }

            }
        })
        binding.btSubmit.setOnClickListener(object : OnClickListener {
            override fun onClick(p0: View?) {
                viewModel.sumList.add(mAmount * mTime)

                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.container, SecondFragment.newInstance())?.addToBackStack(null)
                    ?.commit()
            }
        })
    }

    private fun handleTime(seconds: Int): String {
        if (seconds < 0) {
            return "";
        }
        return if (seconds < 60) {
            seconds.toString() + "s";
        } else {
            val second = seconds % 60
            val minutes = seconds / 60
            String.format("%dm%ds", minutes, second)
        }
    }
}