package org.haxe.extension;


import com.google.android.gms.analytics.*;

import android.util.Log;
import android.app.Activity;
import android.content.res.AssetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

/* 
	You can use the Android Extension class in order to hook
	into the Android activity lifecycle. This is not required
	for standard Java code, this is designed for when you need
	deeper integration.
	
	You can access additional references from the Extension class,
	depending on your needs:
	
	- Extension.assetManager (android.content.res.AssetManager)
	- Extension.callbackHandler (android.os.Handler)
	- Extension.mainActivity (android.app.Activity)
	- Extension.mainContext (android.content.Context)
	- Extension.mainView (android.view.View)
	
	You can also make references to static or instance methods
	and properties on Java classes. These classes can be included 
	as single files using <java path="to/File.java" /> within your
	project, or use the full Android Library Project format (such
	as this example) in order to include your own AndroidManifest
	data, additional dependencies, etc.
	
	These are also optional, though this example shows a static
	function for performing a single task, like returning a value
	back to Haxe from Java.
*/
public class GAnalytics extends Extension {
	
	
	
	
	private static Tracker _gaTracker;

	// -------o constructor

		/**
		* constructor
		*
		* @param
		* @return	void
		*/
		public GAnalytics( ){
			//trace("constructor");
		}

	// -------o public

		/**
		*
		*
		* @public
		* @return	void
		*/
		public static void setDry_run( boolean b ){
			GoogleAnalytics.getInstance( mainContext ).setDryRun( b );
		}

		/**
		*
		*
		* @public
		* @return	void
		*/
		public static void setOpt_out( boolean b ){
			GoogleAnalytics.getInstance( mainContext ).setAppOptOut( b );
		}


		/**
		*
		*
		* @public
		* @return	void
		*/
		public static void startSession( String sUA_code , int iPeriod ) {
			//trace("startSession ::: "+sUA_code+" - "+iPeriod);
			//_gaTracker = GoogleAnalytics.getInstance( mainContext ).getTracker( sUA_code );
			_gaTracker = GoogleAnalytics.getInstance( mainContext ).newTracker( sUA_code );
			
			setDispatch_period( iPeriod );
		}

		/**
		*
		*
		* @public
		* @return	void
		*/
		public static void setDispatch_period( int iPeriod ) {
			GoogleAnalytics.getInstance( mainContext ).setLocalDispatchPeriod(iPeriod);
		}

		/**
		*
		*
		* @public
		* @return	void
		*/
		public static void dispatch( ){
			GoogleAnalytics.getInstance( mainContext ).dispatchLocalHits( );
		}

		/**
		*
		*
		* @public
		* @return	void
		*/
		public static void trackScreen( String sScreen ){
			//trace("trackScreen ::: "+sScreen);
			//_gaTracker.send( MapBuilder.createAppView( ).set( Fields.SCREEN_NAME , sScreen ).build( ) );
			
			Log.d("GoogleAnalytic","Track screen: " + sScreen);
			
			_gaTracker.setScreenName( sScreen );
			_gaTracker.send( new HitBuilders.ScreenViewBuilder().build() );

		}

		/**
		*
		*
		* @public
		* @return	void
		*/
		public static void trackEvent( String sCat , String sAction , String sLabel , int iVal ){
			//_gaTracker.send( MapBuilder.createEvent( sCat , sAction , sLabel , Long.valueOf( iVal ) ).build( ) );
			
			Log.d("GoogleAnalytic","Track event: " + sCat + " : " + sAction + " : " + sLabel);
			
			_gaTracker.send( new HitBuilders.EventBuilder()
            .setCategory(sCat)
            .setAction(sAction)
            .setLabel(sLabel)
            .build() );
		}

		/**
		*
		*
		* @public
		* @return	void
		*/
		public static void trackSocial( String sSocial_network , String sAction , String sTarget ){
			//_gaTracker.send( MapBuilder.createSocial( sSocial_network , sAction , sTarget ).build( ) );
			
			_gaTracker.send( new HitBuilders.SocialBuilder()
            .setNetwork(sSocial_network)
            .setAction(sAction)
            .setTarget(sTarget)
            .build() );
		}

		/**
		*
		*
		* @public
		* @return	void
		*/
		public static void sendTiming( String sCat , int iInterval , String sName , String sLabel ){
			//_gaTracker.send( MapBuilder.createTiming( sCat , Long.valueOf( iInterval ) , sName , sLabel ).build( ) );
			
			_gaTracker.send( new HitBuilders.TimingBuilder()
            .setCategory(sCat)
            .setValue(iInterval)
            .setVariable(sName)
            .setLabel(sLabel)
            .build() );
		}

		public static void sendUncaughtException(String description, boolean fatal) {
			//_gaTracker.send(MapBuilder.createException(description, (Boolean)fatal).build());
			
			_gaTracker.send( new HitBuilders.ExceptionBuilder()
            .setDescription(description)
            .setFatal(fatal)
            .build() );
		}

	// -------o protected



	// -------o misc
	
	
	
	public static int sampleMethod (int inputValue) {
		
		return inputValue * 100;
		
	}
	
	
	/**
	 * Called when an activity you launched exits, giving you the requestCode 
	 * you started it with, the resultCode it returned, and any additional data 
	 * from it.
	 */
	public boolean onActivityResult (int requestCode, int resultCode, Intent data) {
		
		return true;
		
	}
	
	
	/**
	 * Called when the activity is starting.
	 */
	public void onCreate (Bundle savedInstanceState) {
		
		
		
	}
	
	
	/**
	 * Perform any final cleanup before an activity is destroyed.
	 */
	public void onDestroy () {
		
		
		
	}
	
	
	/**
	 * Called as part of the activity lifecycle when an activity is going into
	 * the background, but has not (yet) been killed.
	 */
	public void onPause () {
		
		
		
	}
	
	
	/**
	 * Called after {@link #onStop} when the current activity is being 
	 * re-displayed to the user (the user has navigated back to it).
	 */
	public void onRestart () {
		
		GoogleAnalytics.getInstance( mainContext ).reportActivityStop( mainActivity );
		
	}
	
	
	/**
	 * Called after {@link #onRestart}, or {@link #onPause}, for your activity 
	 * to start interacting with the user.
	 */
	public void onResume () {
		
		
		
	}
	
	
	/**
	 * Called after {@link #onCreate} &mdash; or after {@link #onRestart} when  
	 * the activity had been stopped, but is now again being displayed to the 
	 * user.
	 */
	public void onStart () {
		
		GoogleAnalytics.getInstance( mainContext ).reportActivityStart( mainActivity );
		GoogleAnalytics.getInstance( mainContext ).getLogger( ).setLogLevel( Logger.LogLevel.VERBOSE );
		
	}
	
	
	/**
	 * Called when the activity is no longer visible to the user, because 
	 * another activity has been resumed and is covering this one. 
	 */
	public void onStop () {
		
		
		
	}
	
	
}