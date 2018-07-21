import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CamelCase {
	
	public static List<String> convertCamelCase(String original){
		List<String> resultado = new ArrayList<String>();
		resultado = criaLista(original);
		resultado = transformaPalavrasLista(resultado);
		return resultado;
	}

	protected static List<String> criaLista(String original) {
		List<String> listaSplitada = new ArrayList<>();
		if (contemNumerosNoInicio(original))
			throw new IniciouComNumeroException("Palavra começou com número!!");
		else if (contemCaracteresEspeciais(original))
			throw new ContemCaracteresEspeciais("Palavra contém caracteres especiais!!");
		else {
			String[] transforma = original.split(
					("(?<!(^|[A-Z0-9]))(?=[A-Z0-9])|(?<!(^|[^A-Z]))(?=[0-9])|(?<!(^|[^0-9]))(?=[A-Za-z])|(?<!^)(?=[A-Z][a-z])"));
			for (int i = 0; i < transforma.length; i++) {
				listaSplitada.add(transforma[i]);
			}
		}
		return listaSplitada;
	}
	
	protected static List<String> transformaPalavrasLista(List<String> listasplitada) {
		for (int i = 0; i < listasplitada.size(); i++) {
			String palavraparatransformar = listasplitada.get(i);
			if (ehTodasMaiusculas(palavraparatransformar))
				continue;
			else if (ehNumero(palavraparatransformar))
				continue;
			else
				listasplitada.set(i, palavraparatransformar.toLowerCase());
		}
		return listasplitada;
	}

	protected static boolean contemNumerosNoInicio(String original) {
		if (original.matches("^[0-9][0-9a-zA-Z]*"))
			return true;
		return false;
	}

	protected static boolean contemCaracteresEspeciais(String original) {
		Pattern pattern = Pattern.compile("[^A-Za-z0-9]*[$#()$%^&-]");
		Matcher matcher = pattern.matcher(original);
		if (matcher.find())
			return true;
		return false;
	}

	protected static boolean ehNumero(String palavraparatransformar) {
		boolean retorno;
		retorno = palavraparatransformar.matches("\\d+") ? true : false;
		return retorno;
	}

	protected static boolean ehTodasMaiusculas(String palavraparatransformar) {
		boolean palavratodamaiuscula;
		palavratodamaiuscula = palavraparatransformar.matches("[A-Z]+") ? true : false;
		return palavratodamaiuscula;
	}

}
