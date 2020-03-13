import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Requisicao {
	private String url;
	private String resposta;
	
	public Requisicao(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getResposta() {
		return resposta;
	}
	public void setResposta(String resposta) {
		this.resposta = resposta;
	}
	
	public void requestGet() {
		try {
            String linha;

            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() != 200) {
                System.out.println("Erro " + conn.getResponseCode() + " ao obter dados da URL " + url);
                return;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            while ((linha = br.readLine()) != null) {
                resposta += linha;
            }

            System.out.println("Status 200 - OK");
            System.out.println(resposta);
            
            conn.disconnect();
        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
	}
	
	public void requestPost(int numero) {
		try {
//			String ehPrimo = "Não";
//			if (ehPrimo(numero)) {
//				ehPrimo = "Sim";
//			}

	        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Context-type", "application/json");
	        conn.setRequestProperty("Accept", "application/json");
	        conn.setDoOutput(true);
	        
	        //String jsonInputString = "{\"Name\": \"Requisição Metodo POST\","
	        //						+ " \"ValorInserido\": " + numero + ","
	        //						+ " \"NumeroPrimo\": " + ehPrimo + "}";
	        
	        String json = "{userId: 3,"
	        		   	 + "title: Teste Metodo POST,"
	        		   	 + "body: Teste XPTO}";

//	        try(OutputStream os = conn.getOutputStream()) {
//                byte[] input = jsonInputString.getBytes("utf-8");
//                os.write(input, 0, input.length);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
	        try(OutputStream os = conn.getOutputStream()) {
                byte[] input = json.getBytes("utf-8");
                os.write(input, 0, input.length);
            }catch (Exception e){
                e.printStackTrace();
            }

            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());
            } catch(Exception e) {
            	e.printStackTrace();
            }
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro no método POST");
		}
	}
	
	public boolean ehPrimo(int numero) {
	    for (int i = 2; i < numero; i++) {
	        if (numero % i == 0)
	            return false;   
	    }
	    return true;
	}
}
