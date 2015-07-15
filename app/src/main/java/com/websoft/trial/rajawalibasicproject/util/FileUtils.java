package com.websoft.trial.rajawalibasicproject.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by vaibhav.jani on 4/22/2015.
 */
public class FileUtils {

    private static final String SDCARD_FOLDER = "ThreeDDemo";

    public void copyFolderFromAssets(String folderName, String destinationFileUri, Context context){

        AssetManager assetManager = context.getAssets();

        try {


        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static void copyFolder(String name, Context context) {

        AssetManager assetManager = context.getAssets();

        String[] files = null;

        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state)) {

            try {

                files = assetManager.list(name);

            } catch (IOException e) {

                Log.e("ERROR", "Failed to get asset file list.", e);

                e.printStackTrace();
            }

            for(String filename : files) {

                InputStream in = null;
                OutputStream out = null;

                // First: checking if there is already a target folder
                File folder = new File(Environment.getExternalStorageDirectory() + "/" + SDCARD_FOLDER + "/" + name);

                boolean success = true;

                if (!folder.exists()) {

                    success = folder.mkdirs();
                }

                if (success) {

                    // Moving all the files on external SD
                    try {

                        in = assetManager.open(name + "/" +filename);

                        out = new FileOutputStream(Environment.getExternalStorageDirectory() + "/" + SDCARD_FOLDER + "/" + name + "/" + filename);

                        Log.i("path", Environment.getExternalStorageDirectory() + "/" + SDCARD_FOLDER + "/" + name + "/" + filename);

                        copyFile(in, out);

                        in.close();

                        in = null;

                        out.flush();

                        out.close();

                        out = null;

                    } catch(IOException e) {

                        Log.e("Error", "Failed to copy asset file: " + filename, e);

                        e.printStackTrace();

                    } finally {


                    }
                }

                else {
                    // Do something else on failure
                }
            }

        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {

            // We can only read the media

        } else {

            // Something else is wrong. It may be one of many other states, but all we need
            // is to know is we can neither read nor write
        }
    }

    public static String getFilePathForFile(String name, String folder) {

        String file = Environment.getExternalStorageDirectory() + "/" + SDCARD_FOLDER + "/" + folder + "/" + name;

        return file;
    }

    // Method used by copyAssets() on purpose to copy a file.
    public static void copyFile(InputStream in, OutputStream out) throws IOException {

        byte[] buffer = new byte[1024];

        int read;

        while((read = in.read(buffer)) != -1) {

            out.write(buffer, 0, read);
        }

    }

}
