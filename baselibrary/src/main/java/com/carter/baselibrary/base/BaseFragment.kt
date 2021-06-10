package com.carter.baselibrary.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.carter.baselibrary.utils.ParamUtil

abstract class BaseFragment : Fragment() {

    lateinit var mActivity: AppCompatActivity
    lateinit var mContext: Context
    private var mActivityProvider: ViewModelProvider? = null
    private var mFragmentProvider: ViewModelProvider? = null
    private var mDataBindingConfig: DataBindingConfig? = null
    private var mBinding: ViewDataBinding? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
        mActivity = context as AppCompatActivity
        //必须在onCreateView之前初始化viewModel，因为onCreateView中需要通过ViewModel和DataBinding绑定
        //在Attach之后初始化ViewModel，以为Fragment获取的是Activity中的ViewModel
        initViewModel()
        ParamUtil.initParam(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getLayoutId()?.let {
            setStatusBarColor()
            setSystemInvadeBlack()
            //获取viewDateBinding
            val viewDataBinding: ViewDataBinding =
                DataBindingUtil.inflate(inflater, it, container, false)
            //viewDataBinding生命周期与Fragment绑定
            viewDataBinding.lifecycleOwner = viewLifecycleOwner
            mDataBindingConfig = getDataBindingConfig()
            mDataBindingConfig?.apply {
                val bindingParams = bindingParams
                //将bindingParams逐个加入到viewDataBinding中的variable
                for (i in 0 until bindingParams.size()) {
                    viewDataBinding.setVariable(bindingParams.keyAt(i), bindingParams.valueAt(i))
                }
            }
            mBinding = viewDataBinding
            return viewDataBinding.root
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBase(savedInstanceState)
        //observer在init后，observer会收到粘性事件，对UI处理
        initObserver()
        initOnClick()
    }

    open fun initView() {}

    open fun initData() {}

    abstract fun initBase(savedInstanceState: Bundle?)

    abstract fun getLayoutId(): Int?

    open fun initViewModel() {}

    open fun initObserver() {}

    open fun initOnClick() {}

    /**
     * 状态栏背景颜色
     */
    open fun setStatusBarColor() {}

    /**
     *沉浸式状态
     */
    open fun setSystemInvadeBlack() {}

    abstract fun getDataBindingConfig(): DataBindingConfig?

    protected fun <T : ViewModel?> getActivityViewModel(modelClass: Class<T>): T {
        if (mActivityProvider == null) {
            mActivityProvider = ViewModelProvider(this)
        }
        return mActivityProvider!!.get(modelClass)
    }

    protected fun <T : ViewModel?> getFragmentViewModel(modelClass: Class<T>): T {
        if (mFragmentProvider == null) {
            mFragmentProvider = ViewModelProvider(this)
        }
        return mFragmentProvider!!.get(modelClass)
    }

    protected fun navigation(): NavController {
        return NavHostFragment.findNavController(this)
    }

}