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

        MessageProducerTest test = new MessageProducerTest();
        //test.createCustomers(10);
     //   test.createAccounts(10,10);
        test.createTxn(10,10);

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

    public void createCustomers(int numOfCusts) {

        try {
            System.out.println("Publishing customer info " +numOfCusts );
            HttpClient client = null;
            HttpPost post = null;

            JSONObject jsonObject = null;
            for (int i=1;i<=numOfCusts;i++) {
                client = new DefaultHttpClient();
                post = new HttpPost("http://localhost:8080/mymoney/createCustomer");
                jsonObject= new JSONObject();
                jsonObject.put("customerFirstName", "fName"+i);
                jsonObject.put("customerLastName", "lName"+i);
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

    public void createAccounts(int numAcct, int startDigit){
        try {
            System.out.println("Publishing customer info " +numAcct );
            HttpClient client = null;
            HttpPost post = null;

            JSONObject jsonObject = null;
            int bankId = 1;
            for (int i=1;i<=numAcct;i++) {
                client = new DefaultHttpClient();
                post = new HttpPost("http://localhost:8080/mymoney/createAccount");
                jsonObject= new JSONObject();
                jsonObject.put("accountNumber", +startDigit+i);
                if(bankId ==1 )
                    bankId=2;
                else
                    bankId=1;
                jsonObject.put("bankId",bankId);
                jsonObject.put("customerId", i);
                jsonObject.put("accountType", "SB");
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


    public void createTxn(int numAcct, int startDigit){
        try {
            System.out.println("Publishing Txn info " +numAcct );
            HttpClient client = null;
            HttpPost post = null;

            JSONObject jsonObject = null;
            int bankId = 1;
            for (int i=1;i<=numAcct;i++) {
                client = new DefaultHttpClient();
                post = new HttpPost("http://localhost:8080/mymoney/createTxn");
                jsonObject= new JSONObject();
                jsonObject.put("accountNumber", +startDigit+i);
                jsonObject.put("txnType","CR");
                jsonObject.put("amount", i+"00.00");
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


}
