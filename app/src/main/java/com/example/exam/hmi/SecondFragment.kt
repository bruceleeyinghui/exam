package com.example.exam.hmi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exam.R
import com.example.exam.databinding.FragmentSecondBinding
import com.example.exam.model.MyViewModel

class SecondFragment : Fragment() {

    companion object {
        fun newInstance() = SecondFragment()
    }

    private lateinit var viewModel: MyViewModel
    private lateinit var mResultAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this.requireActivity()).get(MyViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentSecondBinding>(
            inflater,
            R.layout.fragment_second,
            container,
            false
        )
        binding.lifecycleOwner = this
        initView(binding)
        return binding.root
    }

    private fun initView(binding: FragmentSecondBinding) {
        binding.rvList.layoutManager = LinearLayoutManager(context)
        mResultAdapter = MyAdapter(viewModel.sumList)
        binding.rvList.adapter = mResultAdapter
    }

}