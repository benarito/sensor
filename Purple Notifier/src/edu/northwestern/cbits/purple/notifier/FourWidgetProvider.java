package edu.northwestern.cbits.purple.notifier;

import java.io.IOException;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.widget.RemoteViews;

public class FourWidgetProvider extends PurpleWidgetProvider
{
	public static final String NAME = "FOUR_WIDGET_UPDATE";
	public static final String WIDGET_LAUNCH = "config_widget_four_launch";

	public static void setupWidget(Context context, int widgetId, Intent intent) 
	{
		Bundle extras = intent.getExtras();

		AppWidgetManager widgets = AppWidgetManager.getInstance(context);

		String image = extras.getString("image");
		String imageTwo = extras.getString("image_two");
		String imageThree = extras.getString("image_three");
		String imageFour = extras.getString("image_four");
		
		try
		{
			if (image == null || "".equals(image.trim()))
			{
				RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.layout_four_widget);
				Bitmap b = PurpleWidgetProvider.bitmapForText(context, "1", 60, 60, "#ffffff", true, false);
				rv.setImageViewBitmap(R.id.widget_four_one, b);
				widgets.updateAppWidget(widgetId, rv);
			}
			else
			{
				RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.layout_four_widget);
				Bitmap b = PurpleWidgetProvider.bitmapForUri(context, Uri.parse(image));
				rv.setImageViewBitmap(R.id.widget_four_one, b);
				widgets.updateAppWidget(widgetId, rv);
			}
	
			if (imageTwo == null || "".equals(imageTwo.trim()))
			{
				RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.layout_four_widget);
				Bitmap b = PurpleWidgetProvider.bitmapForText(context, "2", 60, 60, "#ffffff", true, false);
				rv.setImageViewBitmap(R.id.widget_four_two, b);
				widgets.updateAppWidget(widgetId, rv);
			}
			else
			{
				RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.layout_four_widget);
				Bitmap b = PurpleWidgetProvider.bitmapForUri(context, Uri.parse(imageTwo));
				rv.setImageViewBitmap(R.id.widget_four_two, b);
				widgets.updateAppWidget(widgetId, rv);
			}
			
			if (imageThree == null || "".equals(imageThree.trim()))
			{
				RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.layout_four_widget);
				Bitmap b = PurpleWidgetProvider.bitmapForText(context, "3", 60, 60, "#ffffff", true, false);
				rv.setImageViewBitmap(R.id.widget_four_three, b);
				widgets.updateAppWidget(widgetId, rv);
			}
			else
			{
				RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.layout_four_widget);
				Bitmap b = PurpleWidgetProvider.bitmapForUri(context, Uri.parse(imageThree));
				rv.setImageViewBitmap(R.id.widget_four_three, b);
				widgets.updateAppWidget(widgetId, rv);
			}
			
			if (imageFour == null || "".equals(imageFour.trim()))
			{
				RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.layout_four_widget);
				Bitmap b = PurpleWidgetProvider.bitmapForText(context, "4", 60, 60, "#ffffff", true, false);
				rv.setImageViewBitmap(R.id.widget_four_four, b);
				widgets.updateAppWidget(widgetId, rv);
			}
			else
			{
				RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.layout_four_widget);
				Bitmap b = PurpleWidgetProvider.bitmapForUri(context, Uri.parse(imageFour));
				rv.setImageViewBitmap(R.id.widget_four_four, b);
				widgets.updateAppWidget(widgetId, rv);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.layout_four_widget);


		Intent tapIntent = new Intent(WidgetIntentService.WIDGET_ACTION);
		tapIntent.putExtra("widget_action", "tap_one");

		if (intent.hasExtra("action_one"))
			tapIntent.putExtra("action_one", intent.getStringExtra("action_one"));

		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, (widgetId * 100) + 1, tapIntent, PendingIntent.FLAG_CANCEL_CURRENT);
		remoteViews.setOnClickPendingIntent(R.id.widget_four_one, pendingIntent);
		
		tapIntent = new Intent(WidgetIntentService.WIDGET_ACTION);
		tapIntent.putExtra("widget_action", "tap_two");

		if (intent.hasExtra("action_two"))
			tapIntent.putExtra("action_two", intent.getStringExtra("action_two"));

		pendingIntent = PendingIntent.getBroadcast(context, (widgetId * 100) + 2, tapIntent, PendingIntent.FLAG_CANCEL_CURRENT);
		remoteViews.setOnClickPendingIntent(R.id.widget_four_two, pendingIntent);

		tapIntent = new Intent(WidgetIntentService.WIDGET_ACTION);
		tapIntent.putExtra("widget_action", "tap_three");
	
		if (intent.hasExtra("action_three"))
			tapIntent.putExtra("action_three", intent.getStringExtra("action_three"));
		
		pendingIntent = PendingIntent.getBroadcast(context, (widgetId * 100) + 3, tapIntent, PendingIntent.FLAG_CANCEL_CURRENT);
		remoteViews.setOnClickPendingIntent(R.id.widget_four_three, pendingIntent);

		tapIntent = new Intent(WidgetIntentService.WIDGET_ACTION);
		tapIntent.putExtra("widget_action", "tap_four");

		if (intent.hasExtra("action_four"))
			tapIntent.putExtra("action_four", intent.getStringExtra("action_four"));

		pendingIntent = PendingIntent.getBroadcast(context, (widgetId * 100) + 4, tapIntent, PendingIntent.FLAG_CANCEL_CURRENT);
		remoteViews.setOnClickPendingIntent(R.id.widget_four_four, pendingIntent);
		
		widgets.updateAppWidget(widgetId, remoteViews);
	}
}
