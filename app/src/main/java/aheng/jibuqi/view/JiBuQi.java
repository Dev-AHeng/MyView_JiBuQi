package aheng.jibuqi.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.media.DrmInitData;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

/**
 * @author AHeng
 * @date 2022/12/08 17:31
 */
public class JiBuQi extends View {
    private static final String TAG = "日志";
    
    private Paint xiaHu, shangHu;
    
    private int startAngle = -200;
    private int sweepAngle = 220;
    
    // 设置wrap_content的默认宽 / 高值
    // 默认宽/高的设定并无固定依据,根据需要灵活设置
    // 类似TextView,ImageView等针对wrap_content均在onMeasure()对设置默认宽 / 高值有特殊处理,具体读者可以自行查看
    private int mWidth = 400;
    private int mHeight = (int) 弧形Y轴直径(sweepAngle, mWidth) + 25;
    
    private int shangSweepAngle = 0;
    
    // 笔画宽度
    private int strokeWidth = 50;
    
    public JiBuQi(Context context) {
        this(context, null);
    }
    
    public JiBuQi(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }
    
    public JiBuQi(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        
        // 下弧形
        xiaHu = new Paint();
        xiaHu.setAntiAlias(true);
        xiaHu.setColor(Color.RED);
        // 笔画大小
        xiaHu.setStrokeWidth(strokeWidth);
        // 设置不填充
        xiaHu.setStyle(Paint.Style.STROKE);
        // 边角样式
        xiaHu.setStrokeCap(Paint.Cap.ROUND);
        
        
        // 上弧形
        shangHu = new Paint();
        shangHu.setAntiAlias(true);
        shangHu.setColor(Color.BLUE);
        // 笔画大小
        shangHu.setStrokeWidth(strokeWidth);
        // 设置不填充
        shangHu.setStyle(Paint.Style.STROKE);
        // 边角样式
        shangHu.setStrokeCap(Paint.Cap.ROUND);
    }
    
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 屏幕宽高
        
        // 获取宽-测量规则的模式和大小
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        
        // 获取高-测量规则的模式和大小
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        
        // 当布局参数设置为wrap_content时，设置默认值
        if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT && getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(mWidth, mHeight);
            // 宽 / 高任意一个布局参数为= wrap_content时，都设置默认值
        } else if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(mWidth, heightSize);
        } else if (getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(widthSize, mHeight);
        }
        
    }
    
    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // strokeWidth半径
        int strokeWidthRadius = strokeWidth / 2;
        RectF rectF = new RectF(strokeWidthRadius, strokeWidthRadius, getWidth() - strokeWidthRadius, getWidth() - strokeWidthRadius);
        canvas.drawArc(rectF, startAngle, sweepAngle, false, xiaHu);
        
        if (shangSweepAngle > 0 && shangSweepAngle <= sweepAngle) {
            canvas.drawArc(rectF, startAngle, shangSweepAngle, false, shangHu);
        }
    }
    
    private double 弧形Y轴直径(double sweepAngle, double width) {
        double toRadians = Math.toRadians((360.0 - sweepAngle) / 2.0);
        return Math.cos(toRadians) * width / 2.0 + width / 2.0;
    }
    
    public void setShangSweepAngle(int sweepAnglePercent) {
        this.shangSweepAngle = sweepAngle * sweepAnglePercent / 100;
        // 刷新onDraw方法
        invalidate();
    }
    
}
