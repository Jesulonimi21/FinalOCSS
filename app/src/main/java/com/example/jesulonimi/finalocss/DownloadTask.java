package com.example.jesulonimi.finalocss;

import android.os.AsyncTask;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class DownloadTask extends AsyncTask<String,Void,List<model>> {
    ArticlesAdapter adapter;
    DateFormat dateFormat=new SimpleDateFormat("EEE,dd MMM yyy HH:mm:ss zzz", Locale.ENGLISH);

    public DownloadTask(ArticlesAdapter articlesAdapter) {
        adapter=articlesAdapter;
    }

    @Override
    protected List<model> doInBackground(String... strings) {
        try {
            String u=strings[0];
            URL url=new URL(u);
            HttpURLConnection urlConnection=(HttpURLConnection) url.openConnection();

            InputStream inputStream=urlConnection.getInputStream();
            DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder=documentBuilderFactory.newDocumentBuilder();
            Document document=documentBuilder.parse(inputStream);
            NodeList item =  document.getElementsByTagName("item");
            List<model> myList=new ArrayList<>(item.getLength());
            for(int i=0;i<item.getLength();i++){
                    Element itemChild= (Element)item.item(i);
                    String title=itemChild.getElementsByTagName("title").item(0).getTextContent();
                    String link=itemChild.getElementsByTagName("link").item(0).getTextContent();
                    String dateString=itemChild.getElementsByTagName("pubDate").item(0).getTextContent();
                    Date date=dateFormat.parse(dateString);
                    myList.add(new model(title,link,date));
            }
            return  myList;

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            return null;
        } catch (SAXException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }


    }

    @Override
    protected void onPostExecute(List<model> models) {
        super.onPostExecute(models);
        if(models!=null){
        adapter.addAarticles(models);
    }}
}
