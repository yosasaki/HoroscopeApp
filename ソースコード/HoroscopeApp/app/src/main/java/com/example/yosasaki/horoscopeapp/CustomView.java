package com.example.yosasaki.horoscopeapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.ContextMenu;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * Created by yosasaki on 2015/09/22.
 */
public class CustomView extends View{

    public CustomView( Context context) {
        super(context);
        CustomView.this.setBackgroundColor(Color.LTGRAY);
    }

    @Override
    protected ContextMenu.ContextMenuInfo getContextMenuInfo() {
        return super.getContextMenuInfo();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();

        paint.setAntiAlias(true);
        int pnt1 = 96;
        int pnt2 = pnt1;
        int pnt3 = 72;
        int pnt4 = 0;
        paint.setTextSize(pnt1);
        paint.setColor(Color.BLUE);
        canvas.drawText("今日の運勢", 0, pnt1, paint);

        JSONObject jsonObjectTmp = IntentServiceHttp.jsonObject[MainActivity.selectHoro];
        try {
            paint.setColor(Color.MAGENTA);
            canvas.drawText(jsonObjectTmp.getString("sign"), 0, pnt2 += pnt1, paint);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy'年'MM'月'dd'日'");
            sdf.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
            paint.setColor(Color.GREEN);
            canvas.drawText(sdf.format(MainActivity.dateHoro) + "の運勢", 0, pnt2 += pnt1, paint);

            paint.setColor(Color.YELLOW);
            paint.setTextSize(pnt3);
            pnt4 = pnt2 + pnt3 * 2;
            canvas.drawText("運勢RANK：" + jsonObjectTmp.getString("rank") + "位",0,pnt4,paint);
            canvas.drawText("総合運：" + jsonObjectTmp.getString("total") + "位",0,pnt4 += pnt3,paint);
            canvas.drawText("恋愛運：" + jsonObjectTmp.getString("love") + "位",0,pnt4 += pnt3,paint);
            canvas.drawText("仕事運：" + jsonObjectTmp.getString("job") + "位",0,pnt4 += pnt3,paint);
            canvas.drawText("金運：" + jsonObjectTmp.getString("money") + "位",0,pnt4 += pnt3,paint);
            canvas.drawText("ラッキーアイテム：" + jsonObjectTmp.getString("item"),0,pnt4 += pnt3,paint);
            canvas.drawText("ラッキーカラー：" + jsonObjectTmp.getString("color"),0,pnt4 += pnt3,paint);
            String content = jsonObjectTmp.getString("content");

            int textSize = pnt4 += pnt3 * 2;
            int maxWidth = canvas.getWidth();
            int lineBreakPoint = Integer.MAX_VALUE;
            int currentIndex = 0;//現在、原文の何文字目まで改行が入るか確認したかを保持する
            int linePointY = textSize;//文字を描画するY位置。改行の旅にインクリメントする。

            while(lineBreakPoint!=0){
                String mesureString=content.substring(currentIndex);
                lineBreakPoint=paint.breakText(mesureString, true, maxWidth, null);
                if(lineBreakPoint!=0){
                    String line = content.substring(currentIndex, currentIndex + lineBreakPoint);
                    canvas.drawText(line, 0, linePointY, paint);
                    linePointY += pnt3;
                    currentIndex += lineBreakPoint;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
