package se.chalmers.student.aviato;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends Activity {
    private static boolean splashLoaded = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!splashLoaded) {
            setContentView(R.layout.activity_splash);
            final ImageView image = (ImageView)findViewById(R.id.splashscreen);
            AnimationSet s = new AnimationSet(false);
            Animation animation1 =
                    AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
            //image.startAnimation(animation1);
            //image = (ImageView)findViewById(R.id.splashscreen);
            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                   R.anim.fade);
            s.addAnimation(animation1);
            s.addAnimation(animation);
            image.setAnimation(s);
            image.startAnimation(s);
            s.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    Log.d("animation", "end");
                    image.clearAnimation();
                    AnimatorSet shrinkSet = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.shrink_to_middle);
                    shrinkSet.setTarget(image);
                    shrinkSet.start();

                    image.setImageResource(R.drawable.logo);

                    AnimatorSet growSet = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.grow_from_middle);
                    growSet.setTarget(image);
                    growSet.start();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

           // ImageView logoimage = (ImageView)findViewById(R.id.splashlogo);
           // logoimage.startAnimation(animation1);
            int secondsDelayed = 1;
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                    finish();
                }
            }, secondsDelayed * 2000);

            splashLoaded = false;
        }
        else {
            Intent goToMainActivity = new Intent(SplashActivity.this, HomeActivity.class);
            goToMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(goToMainActivity);
            finish();
        }
    }
}
