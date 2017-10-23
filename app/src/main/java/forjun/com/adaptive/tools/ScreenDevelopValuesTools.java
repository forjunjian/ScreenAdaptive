package forjun.com.adaptive.tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import forjun.com.adaptive.DecimalUtils;

/**
 * Created by Administrator on 2017/10/23.
 */

public class ScreenDevelopValuesTools {
	
	private final static String Template = "<dimen name=\"dp{0}\">{1}dp</dimen>\n";
	
	/**
	 * 参考的宽度
	 */
	public final int baseWidth;
	
	/**
	 * 两个Values之间的比例
	 */
	public final float distance;
	
	private File BuildDirector;
	
	/**
	 * @param baseWith 参考的基础宽度
	 * @param distance 两个Values之间的间距
	 */
	public ScreenDevelopValuesTools(int baseWith, float distance) {
		this.baseWidth = baseWith;
		this.distance = distance;
		
		if ( baseWith <= 100 ) {
			throw new RuntimeException( "请输入正确的参考宽度" );
		}
		
		if ( distance <= 1 ) {
			throw new RuntimeException( "请输入正确的比例值" );
		}
		
		BuildDirector = new File( "outputResource" );
		
		if ( BuildDirector.exists() ) {
			System.out.println( "clean：文件存在，先删除再重新生成" );
			FileUtils.deleteDirectory( BuildDirector );
		}
		
		System.out.println( "初始化：目录" );
		BuildDirector.mkdirs();
		
	}
	
	/**
	 * 生成各种Values
	 *
	 * @param start 生成范围的开始
	 * @param end   生成范围结束
	 */
	private void generate(int start, int end) {
		if ( !BuildDirector.exists() ) {
			throw new RuntimeException( "输出目录不存在！" );
		}
		
		if ( start <= 0 || end <= 0 ) {
			throw new RuntimeException( "请输入正确的开始或者结束值" );
		}
		
		for ( int currentWidth = start; currentWidth < end; ) {
			generateValuesFile( currentWidth );
			
			currentWidth = Math.round( currentWidth * distance );
			
		}
		
	}
	
	private void generateValuesFile(int widthPixel) { // 生成相应像素的values文件。
		
		float maxWidth = widthPixel * distance; // 取当前区间的中间值 然后得到比例，把最大误差减半
		
		float multiple = ( maxWidth + widthPixel ) / 2 / baseWidth;
		System.out.println( "minWidth = " + widthPixel + "#maxWidth = " + maxWidth + "#multiple= " + multiple );
		
		StringBuffer sbForWidth = new StringBuffer();
		sbForWidth.append( "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" );
		sbForWidth.append( "<resources>\n" );
		
		for ( int i = 1; i <= baseWidth; i++ ) {
			
			sbForWidth.append( "\t" + Template.replace( "{0}", i + "" ).replace( "{1}", DecimalUtils.getDecimalEndWith2( i * multiple ) ) );
		}
		
		sbForWidth.append( "</resources>" );
		// 输出到文件里面
		File currentDirector = new File( BuildDirector, "values-sw" + widthPixel + "dp" );
		currentDirector.mkdir();
		File currentFile = new File( currentDirector, "dimens.xml" );
		System.out.println( currentFile.getAbsolutePath() );
		try {
			FileWriter fileWriter = new FileWriter( currentFile );
			fileWriter.write( sbForWidth.toString() );
			fileWriter.close();
			
		} catch ( IOException e ) {
			e.printStackTrace();
		}
	}
	
	public static final void main(String[] arg) {
		ScreenDevelopValuesTools dimenDevelop = new ScreenDevelopValuesTools( 400, 1.08f );
		dimenDevelop.generate( 200, 1000 );
	}
	
}
