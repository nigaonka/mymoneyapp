package ng.mymoney.test;

import ng.mymoney.model.AccountTxn;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;



public class MessageProducerTest {


    public static void main(String[] args) {

        try {
            System.out.println("Starting messaging");
            HttpClient client = null;
            HttpPost post = null;

            JSONObject jsonObject = null;
            for (int i=1;i<=5;i++) {
                 client = new DefaultHttpClient();
                 post = new HttpPost("http://localhost:8080/kafka/publishMessage");
                jsonObject= new JSONObject();
                jsonObject.put("txnId", "100"+i);
                jsonObject.put("userId", "100"+1);
                jsonObject.put("accountId", "34567"+i);
                jsonObject.put("txnType", "DR");
                jsonObject.put("amount", "1234"+i);

                //String payload = new AccountTxn(1, 2, 989, "DR", 23453);
                System.out.println("Entity ready");
                StringEntity entity = new StringEntity(jsonObject.toString());
                entity.setContentType("application/json");
                post.setEntity(entity);
                System.out.println("Message posted");
                HttpResponse response = client.execute(post);


            }


            System.out.println("Finished ");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        }


   /*     try {
            URL url = new URL("http://localhost:8080/kafka/publishMessage");
            int txnCount = Integer.parseInt(args[0]);

            //make connection
            URLConnection urlc = url.openConnection();

            //use post mode
            urlc.setDoOutput(true);
            urlc.setAllowUserInteraction(false);

            //send query
            PrintStream ps = new PrintStream(urlc.getOutputStream());
            for (int i = 1; i <= txnCount; i++) {
              String  payload = "txnId:" + i + "\n accountId:" + "100" + i + "\n txnType:" + "CR\n" + "amount:" + 14512;
                ps.print(payload);

            ps.close();

            //get result

        BufferedReader br = new BufferedReader(new InputStreamReader(urlc
                .getInputStream()));
        String l = null;
        while ((l=br.readLine())!=null) {
            System.out.println(l);
        }
        br.close();
}
        }catch (Exception e){
            e.printStackTrace();
        }
    }

*/



}
