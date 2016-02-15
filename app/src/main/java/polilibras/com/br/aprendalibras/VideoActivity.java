package polilibras.com.br.aprendalibras;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.VideoView;

/**
 * Created by marce on 15/02/2016.
 */
public class VideoActivity extends AppCompatActivity {

    protected VideoActivity activity = this;

    protected VideoView mVideoView;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        initVideoView();
    }

    public void initVideoView() {

        mVideoView = (VideoView) findViewById(R.id.video_view);
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mVideoView.start();
            }
        });
        findViewById(R.id.video_repeat_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVideoView.seekTo(0);
                mVideoView.start();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        mVideoView.stopPlayback();
    }
}