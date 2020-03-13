
public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Requisicao requisicaoGet = new Requisicao("https://jsonplaceholder.typicode.com/users/4");
		requisicaoGet.requestGet();
		
		Requisicao requisicaoPost = new Requisicao("https://jsonplaceholder.typicode.com/posts");
//		requisicaoPost.requestPost(4);

	}

}
