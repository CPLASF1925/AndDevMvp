package com.lishide.gankarms.mvp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jess.arms.base.BaseFragment
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import com.jess.arms.utils.Preconditions.checkNotNull
import com.lishide.gankarms.R
import com.lishide.gankarms.app.constant.CategoryConstant
import com.lishide.gankarms.di.component.DaggerLikeComponent
import com.lishide.gankarms.di.module.LikeModule
import com.lishide.gankarms.mvp.contract.LikeContract
import com.lishide.gankarms.mvp.presenter.LikePresenter
import com.lishide.gankarms.mvp.ui.adapter.LikePagerAdapter
import kotlinx.android.synthetic.main.fragment_like.*

/**
 * 收藏 Fragment
 *
 * @author lishide
 * @date 2018/01/03
 */
class LikeFragment : BaseFragment<LikePresenter>(), LikeContract.View {

    private var mData = mutableListOf(
            CategoryConstant.GIRLS_STR,
            CategoryConstant.ARTICLES_STR
    )
    private var mLikePagerAdapter: LikePagerAdapter? = null

    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerLikeComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .likeModule(LikeModule(this))
                .build()
                .inject(this)
    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_like, container, false)
    }

    override fun initData(savedInstanceState: Bundle?) {
        mLikePagerAdapter = LikePagerAdapter(childFragmentManager, mData)
        viewPager.adapter = mLikePagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }

    /**
     * 此方法是让外部调用使fragment做一些操作的,比如说外部的activity想让fragment对象执行一些方法,
     * 建议在有多个需要让外界调用的方法时,统一传Message,通过what字段,来区分不同的方法,在setData
     * 方法中就可以switch做不同的操作,这样就可以用统一的入口方法做不同的事
     *
     *
     * 使用此方法时请注意调用时fragment的生命周期,如果调用此setData方法时onCreate还没执行
     * setData里却调用了presenter的方法时,是会报空的,因为dagger注入是在onCreated方法中执行的,然后才创建的presenter
     * 如果要做一些初始化操作,可以不必让外部调setData,在initData中初始化就可以了
     *
     * @param data
     */
    override fun setData(data: Any?) {

    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showMessage(message: String) {
        checkNotNull(message)
        ArmsUtils.snackbarText(message)
    }

    override fun launchActivity(intent: Intent) {
        checkNotNull(intent)
        ArmsUtils.startActivity(intent)
    }

    override fun killMyself() {

    }

    companion object {


        fun newInstance(): LikeFragment {
            return LikeFragment()
        }
    }

}
