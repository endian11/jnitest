package cppt.phone.lzw.com.cpptes.util;

import com.orhanobut.logger.Logger;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by lizhanwei on 17/8/29.
 */

public class HttpConnectionUtil {

    /**
     * 上传多个文件
     * @param actionUrl http url
     * @param uploadFilePaths 文件路径
     * @return server返回的json字符串
     */
    public static String uploadFile(String actionUrl,String[] uploadFilePaths){
        String end = "\r\n";
        String twoHyphens = "--";
        String boundary = "---------------------------7df2882560ede";

        DataOutputStream dataOutputStream = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        StringBuffer stringBuffer = new StringBuffer();
        String tempLine = null;

        try {
            //统一资源
            URL url = new URL(actionUrl);
        //连接类的父类 抽象类
            URLConnection urlConnection = url.openConnection();
            //http的连接类
            HttpURLConnection httpURLConnection = (HttpURLConnection)urlConnection;

            //设置是否从httpurlConnection读入
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            //post request 不能使用cache
            httpURLConnection.setUseCaches(false);
            //设定请求的方法，默认是GET
            httpURLConnection.setRequestMethod("POST");

            //设置字符编码连接参数
            httpURLConnection.setRequestProperty("Connection","Keep-Alive");

            //set 字符编码
            httpURLConnection.setRequestProperty("Charset","UTF-8");

            //设置请求内容类型
            httpURLConnection.setRequestProperty("Content-Type","multipart/form-data;boundary="+boundary);

            //设置DataOutputStream
            dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            for (int i=0; i<uploadFilePaths.length; i++){
                String uploadFile = uploadFilePaths[i];
                String fileName = uploadFile.substring(uploadFile.lastIndexOf("//")+1);
                dataOutputStream.writeBytes(twoHyphens+boundary+end);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("username","test");
                jsonObject.put("passWord","123456");
                jsonObject.put("reqId","1234569");
                Logger.i("json:"+jsonObject.toString());
                dataOutputStream.writeBytes(jsonObject.toString());
                dataOutputStream.writeBytes(end);
                dataOutputStream.writeBytes(twoHyphens+boundary+end);
                dataOutputStream.writeBytes("Content-Disposition:form-data; " + "name=\"photo\""+";" +
                        "filename="+i+1+"\".jpg\"");
                dataOutputStream.writeBytes(end);
                dataOutputStream.writeBytes("Content-Type:image/jpeg");
                dataOutputStream.writeBytes(end);
                FileInputStream fileInputStream = new FileInputStream(uploadFile);

                int bufferSize = 1024;
                byte[] buffer = new byte[bufferSize];
                int length = -1;
                while((length=fileInputStream.read(buffer)) != -1){
                    dataOutputStream.write(buffer,0,length);
                }
                dataOutputStream.writeBytes(end);
                fileInputStream.close();

            }
            dataOutputStream.writeBytes(twoHyphens+boundary+twoHyphens+end);
            dataOutputStream.flush();

            if (httpURLConnection.getResponseCode() >= 300){
                throw new Exception("HTTP Request is not success, Response code is "+httpURLConnection.getRequestMethod());
            }
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                inputStream = httpURLConnection.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream);
                bufferedReader = new BufferedReader(inputStreamReader);
                while ((tempLine = bufferedReader.readLine()) != null) {
                    stringBuffer.append(tempLine);
                    stringBuffer.append("\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (dataOutputStream != null){
                try {
                    dataOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStreamReader != null){
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return stringBuffer.toString();
    }
}
