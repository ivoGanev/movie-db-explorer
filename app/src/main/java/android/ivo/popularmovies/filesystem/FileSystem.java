package android.ivo.popularmovies.filesystem;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileSystem {
    public void saveBitmap(Bitmap bitmap, File directory, String fileName) {
        FileOutputStream outputStream = null;
        File file = new File(directory, fileName);

        try {
            outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null)
                    outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Bitmap loadBitmap(String path, String fileName) {
        Bitmap bitmap = null;
        FileInputStream inputStream = null;

        try {
            File file = new File(path, fileName);
            inputStream = new FileInputStream(file);
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return bitmap;
    }

    public Boolean deleteFile(String absolutePath)
    {
        File file = new File(absolutePath);
        return file.delete();
    }
}
