package com.czt.temprxb;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.czt.temprxb.dagger2.DaggerDaggerCompcoent;
import com.czt.temprxb.dagger2.PresenterMobule;
import com.czt.temprxb.framework.base.BaseMVPActivity;

import javax.inject.Inject;

import cn.droidlover.xdroidmvp.mvp.IView;
import cn.droidlover.xdroidmvp.router.Router;

public class MySendActivity extends BaseMVPActivity<IView.IMySendActivityView,MySendActivityPresenter> implements IView.IMySendActivityView{
    private ImageView mImageViewBack;//返回按钮
    private Button mButtonSendCode;//发送验证码按钮
    private EditText mEditTextPhone;//手机号输入框
    private EditText mEditTextCode;//验证码
    private EditText mEditTextpas;//密码
    private EditText mEditTextpass;//确认密码
    private CheckBox mCheckBox;
    public static final String REGISTENER_PHONE = "phone";
    public static final String REGISTER_PASS = "password";
    @Inject
    MySendActivityPresenter mPresenter;

    @Override
    public void getSuccess(Object string) {
        Router.newIntent(this).to(RegistActivity.class).launch();
    }

    @Override
    public void getError(Object string) {

    }

    @Override
    protected MySendActivityPresenter mPresenterCreate() {
        DaggerDaggerCompcoent.builder()
                .presenterMobule(new PresenterMobule())
                .appCompcoent(((MyApplication) getApplication()).getAppCompcoent())
                .build().inject(this);
        return mPresenter;
    }

    @Override
    protected int getStateLayoutID() {
        return 0;
    }

    @Override
    protected int getPullRefreshLayoutID() {
        return 0;
    }

    @Override
    protected void setListener() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mImageViewBack = optionView(R.id.register_back);
        mButtonSendCode = optionView(R.id.register_sendcode);
        mButtonSendCode.setText("我来之MySendActivity");
        mEditTextPhone = optionView(R.id.register_etphone);
        mEditTextCode = optionView(R.id.register_code);
        mEditTextpas = optionView(R.id.register_pas);
        mEditTextpass = optionView(R.id.register_pas1);
        mCheckBox = optionView(R.id.register_box);
        startRefresh(false);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public Object newP() {
        return null;
    }

    @Override
    public void startRefresh(boolean isShowLoadingView) {
        String phone = getIntent().getStringExtra(REGISTENER_PHONE);
        if (!TextUtils.isEmpty(phone)) {
            mEditTextPhone.setText(phone);
        }
        hideStatusBar();
    }

    /**
     * 点击注册
     *
     * @param view
     */
    public void register(View view) {
        String phone = mEditTextPhone.getText().toString().trim();
        //        if (TextUtils.isEmpty(phone)) {
        //            To.oo("电话号码不能为空");
        //            return;
        //        }
        String code = mEditTextCode.getText().toString().trim();
        //        if (TextUtils.isEmpty(code)) {
        //            To.oo("验证码不能为空");
        //            return;
        //        }
        String pas = mEditTextpas.getText().toString().trim();
        //        if (TextUtils.isEmpty(pas) ||
        //                !ZhengZebiaodashiUtils.isPassword(pas) || pas.length() < 6) {
        //            To.oo("请输入字母+数字的不少于六位的组合密码");
        //            return;
        //        }
        String pass = mEditTextpass.getText().toString().trim();
        //        if (TextUtils.isEmpty(pass)) {
        //            To.oo("确认密码框不能为空");
        //            return;
        //        }
        //        if (!TextUtils.equals(pas, pass)) {
        //            To.oo("两次密码不一致");
        //            return;
        //        }
        //        if (!mCheckBox.isChecked()) {
        //            To.oo("请阅读协议后，勾选同意艺科协议");
        //            return;
        //        }
        mPresenter.getMySendMessage(phone, pas, pass);
    }
}
