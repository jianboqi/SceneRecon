package com.orobator.android.scenerecon.view.customviews;import android.graphics.Canvas;import android.graphics.Paint;/** * A YawCircle represents a circle indicating the angle off North a user is * facing. */public class YawCircle extends AlignmentCircle {    public YawCircle(int brethrenCount, float circleAngle, Paint paint, float radius) {        mBrethrenCount = brethrenCount;        mCircleAngle = circleAngle;        mPaint = paint;        mRadius = radius;    }    @Override    public void drawSelf(Canvas canvas, float rotationAngle) {        if (!isOnScreen(rotationAngle, canvas)) {            return;        }        if (rotationAngle < 0) {            rotationAngle += 360;        }        float cy = canvas.getHeight() / 2;        float offsetFromCenterCanvas = (float) (rotationAngle - mCircleAngle); // in degrees        offsetFromCenterCanvas = (float) getCanvasLengthFromDegrees(offsetFromCenterCanvas, canvas); // in pixels        float cx = canvas.getWidth() / 2 + offsetFromCenterCanvas;        canvas.drawCircle(cx, cy, mRadius, mPaint);        // Draw left line        float leftStartX = cx - canvas.getWidth() / 2 + mRadius;        float leftStartY = cy;        float leftStopX = cx - mRadius;        float leftStopY = cy;        canvas.drawLine(leftStartX, leftStartY, leftStopX, leftStopY, mPaint);        // Draw right line        float rightStartX = cx + mRadius;        float rightStartY = cy;        float rightStopX = cx + canvas.getWidth() / 2 - mRadius;        float rightStopY = cy;        canvas.drawLine(rightStartX, rightStartY, rightStopX, rightStopY, mPaint);        if (debug) {            mPaint.setTextSize(45);            canvas.drawText(String.valueOf(mCircleAngle), cx - 3 * mRadius / 4, cy + mRadius / 5, mPaint);        }    }}