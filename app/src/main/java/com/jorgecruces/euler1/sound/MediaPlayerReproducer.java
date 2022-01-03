package com.jorgecruces.euler1.sound;

import android.content.Context;
import android.media.MediaPlayer;
import android.provider.MediaStore;

import com.jorgecruces.euler1.R;

public class MediaPlayerReproducer {

    private static final MediaPlayerReproducer mp = new MediaPlayerReproducer();

    private MediaPlayerReproducer()
    {

    }

    public static MediaPlayerReproducer getInstance()
    {
        return mp;
    }

    /**
     * Reproduce click Sound
     * @param context
     */
    public void reproduceClickSound(Context context)
    {
        reproduceSound(context, R.raw.click);
    }

    /**
     * Reproduce Win Sound
     * @param context
     */
    public void reproduceWinSound(Context context)
    {
        reproduceSound(context, R.raw.win_sound);
    }

    /**
     * Reproduce sound
     */
    private void reproduceSound(Context context, int R)
    {
        MediaPlayer mp = MediaPlayer.create(context, R);
        try
        {
            mp.start();
        } catch (IllegalStateException e)
        {
            e.printStackTrace();
        }


    }

}
