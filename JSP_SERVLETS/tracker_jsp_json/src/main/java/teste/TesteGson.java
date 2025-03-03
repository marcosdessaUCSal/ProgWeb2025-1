package teste;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class TesteGson {
	
	public static void main(String[] args) {
		List<Pessoa> pessoas = new ArrayList();
		pessoas.add(new Pessoa("Marcos", 25));
		pessoas.add(new Pessoa("João", 32));
		
		Gson gson = new Gson();
		String json = gson.toJson(pessoas);
		
		System.out.println(json);
		
	}
}
