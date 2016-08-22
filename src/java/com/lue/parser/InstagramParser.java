/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lue.parser;

import com.lue.model.DataBank;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class InstagramParser {

    List id = new ArrayList();
    List date = new ArrayList();
    List commentCount = new ArrayList();
    List likeCount = new ArrayList();
    List imageSrc = new ArrayList();
    List displaySrc = new ArrayList();
    List postDesc = new ArrayList();
    DataBank dataBank = new DataBank();
    CloseableHttpResponse response2;
    String max_Id = "";

    public DataBank connectUrl(String name, int NumberOfPost) {
        try {
            int n = (NumberOfPost / 24) + 1;
            // Reading Proxy Info from properties file
            Properties props = new Properties();
            InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("proxyconfig.properties");
            props.load(in);
            System.out.println("--------" + props.getProperty("proxy.ip") + Integer.parseInt(props.getProperty("proxy.port")));
            // Creating Http Proxy Instance below
            HttpHost proxy = new HttpHost(props.getProperty("proxy.ip"), +Integer.parseInt(props.getProperty("proxy.port")));
            DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
            CloseableHttpClient httpclient = HttpClients.custom()
                    .setRoutePlanner(routePlanner)
                    .build();
            // CloseableHttpClient httpclient = HttpClients.createDefault();
            StringBuffer bufferResponse;
            for (int no = 0; no < n; no++) {
                HttpGet httpGet = new HttpGet("https://www.instagram.com/" + name + "/?max_id=" + max_Id);
                response2 = httpclient.execute(httpGet);

                System.out.println(response2.getStatusLine());
                if (response2.getStatusLine().getStatusCode() != 200) {
                    dataBank.setErrorMessage("error");
                    return dataBank;
                }
                System.out.println("POST Response Status:: " + response2.getStatusLine().getStatusCode());
                BufferedReader reader = new BufferedReader(new InputStreamReader(response2.getEntity().getContent()));
                String inputLine;
                bufferResponse = new StringBuffer();

                while ((inputLine = reader.readLine()) != null) {
                    bufferResponse.append(inputLine);
                }
                reader.close();
                Document doc = Jsoup.parse(bufferResponse.toString());
                dataBank = getListOfElements(doc);
            }
            HttpEntity entity2 = response2.getEntity();
            EntityUtils.consume(entity2);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataBank;
    }

    public DataBank getListOfElements(Document doc) {

        try {
            Elements scriptElements = doc.getElementsByTag("script");
            int i = 0;
            String res = null;
            for (Element element : scriptElements) {
                for (DataNode node : element.dataNodes()) {
                    if (i == 3) {
                        res = node.getWholeData();
                    }
                    i++;
                }
            }
            StringTokenizer token = new StringTokenizer(res, "[");

            token.nextToken();
            token.nextToken();
            String se = token.nextToken();
            String as = "{\"nodes\":[" + se;
            JSONObject obj = new JSONObject(as);
            JSONArray geodata = obj.getJSONArray("nodes");
            int n = geodata.length();

            for (int j = 0; j < n; ++j) {
                final JSONObject person = geodata.getJSONObject(j);
                max_Id = (String) person.getString("id");
                id.add(person.getJSONObject("owner").get("id"));
                Date postDate = new Date(person.getLong("date"));
                SimpleDateFormat df2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                date.add(df2.format(postDate));
                commentCount.add(person.getJSONObject("comments").get("count"));
                likeCount.add(person.getJSONObject("likes").get("count"));
                imageSrc.add(person.getString("display_src"));
                postDesc.add(person.get("caption"));
                displaySrc.add("https://www.instagram.com/p/" + person.getString("code"));
            }
            dataBank.setId(id);
            dataBank.setDate(date);
            dataBank.setImageUrl(imageSrc);
            dataBank.setNumberOfComments(commentCount);
            dataBank.setNumberOfLikes(likeCount);
            dataBank.setLinkToFullPost(displaySrc);
            dataBank.setPostDescription(postDesc);
            return dataBank;
        } catch (Exception e) {
            e.printStackTrace();
            dataBank.setErrorMessage(e.getMessage());
        }
        return null;
    }
}
