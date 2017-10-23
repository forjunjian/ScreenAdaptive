package forjun.com.adaptive;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
	
	private View         mViewMainWidth;
	private View         mViewMainHeight;
	private TextView     mMsgMainView;
	private LinearLayout mBtnMain;
	private TextView mMsgMainDimen;
	private View mLineMainMin;
	private View mLineMainMax;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_main );
//		initView();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		initView();
	}
	
	private void initView() {
		mViewMainWidth = ( View ) findViewById( R.id.view_main_width );
		mViewMainHeight = ( View ) findViewById( R.id.view_main_height );
		mLineMainMin = ( View ) findViewById( R.id.line_main_mini );
		mLineMainMax = ( View ) findViewById( R.id.line_main_max );
		
		mMsgMainView = ( TextView ) findViewById( R.id.msg_main_view );
		mMsgMainDimen = ( TextView ) findViewById( R.id.msg_main_dimen );
		
		mBtnMain = ( LinearLayout ) findViewById( R.id.btn_main );
		mBtnMain.setOnClickListener( this );
		
		
		
		
		// 理论上获取最大dp720 应该与屏幕宽度差不多，误差不超过0.06
		int            dimenMaxWidth  = getResources().getDimensionPixelOffset( R.dimen.dp400 ); // dp720的像素
		
		
		WindowManager  wdMgr          = (WindowManager) getSystemService( WINDOW_SERVICE);
		Display        display        = wdMgr.getDefaultDisplay();
		Point          outSize        = new Point();
		display.getSize(outSize);
		int screamWidth = outSize.x;// 获取屏幕宽度
		int screamHeight = outSize.y;// 获取屏幕高度
		
		
		float multiple          = dimenMaxWidth * ( 100f ) / screamWidth;
		
		
		mMsgMainDimen.setText( "屏幕宽度："+screamWidth +"#屏幕高度："+screamHeight
			                       + "\r\n dp400 = "+dimenMaxWidth+"个像素"
			                       +"\r\n 比值为 "+DecimalUtils.getDecimalEndWith2( multiple)+"%");
		
		// 配置View的宽度最小样子和View宽度最大样子
		multiple = 1.06f;
		
		ViewGroup.LayoutParams layoutParamsMax = mLineMainMax.getLayoutParams();
		
		layoutParamsMax.width = Math.round( screamWidth*multiple/2 );
		mLineMainMax.setLayoutParams( layoutParamsMax );
		
		ViewGroup.LayoutParams layoutParamsMin = mLineMainMin.getLayoutParams();
		layoutParamsMin.width =  Math.round( screamWidth/multiple/2 );
		mLineMainMin.setLayoutParams( layoutParamsMin );
	}
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged( hasFocus );
		if(hasFocus){
			// 测试View的宽高 （View 的宽度为Values对应的最大宽度，期待和屏幕宽度差不多，误差不超过0.06）
			//		int unspecified = View.MeasureSpec.makeMeasureSpec( 0, View.MeasureSpec.UNSPECIFIED );
			//		mViewMainWidth.measure( unspecified,unspecified );
			//		int width = mViewMainWidth.getMeasuredWidth();
			//
			//		mViewMainHeight.measure( unspecified,unspecified );
			//		int height = mViewMainHeight.getMeasuredHeight();
			//		mMsgMainView.setText( "宽度为："+width+"个像素# 高度为："+height+"个像素" );
			
			int width = mViewMainWidth.getWidth();
			int height = mViewMainHeight.getHeight();
			mMsgMainView.setText( "宽度为："+width+"个像素# 高度为："+height+"个像素" );
		}
		TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP,4,getResources().getDisplayMetrics() );
	}
	
	@Override
	public void onClick(View view) {
		int width = mViewMainWidth.getWidth();
		int height = mViewMainHeight.getHeight();
		mMsgMainView.setText( "宽度为："+width+"个像素# 高度为："+height+"个像素" );
		
	}
}
